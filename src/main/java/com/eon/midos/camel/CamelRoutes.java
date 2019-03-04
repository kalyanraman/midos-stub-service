package com.eon.applypayment.camel;

import javax.annotation.PostConstruct;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaComponent;
import org.apache.camel.component.properties.PropertiesComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CamelRoutes extends RouteBuilder {

	@Autowired
	CamelContext camelContext;
	@Autowired
	Mapper mapper;

	@PostConstruct
	public void setup() {
		PropertiesComponent pc = getContext().getComponent("properties",
				PropertiesComponent.class);
		pc.setLocation("classpath:application.properties");

		// setup kafka component with the brokers
		KafkaComponent kafka = new KafkaComponent();
		kafka.setBrokers("{{kafka.host}}:{{kafka.port}}");
		camelContext.addComponent("kafka", kafka);
	}

	@Override
	public void configure() throws Exception {
		from("direct:applyPayment").to("direct:getUtrn");
		configureGetUtrn();
		configureHandleUtrnPosted();
		configureHandleUtrnApplied();
		configureHandleMeterBalancePosted();
	}
	
	public void configureHandleUtrnApplied(){
		from("direct:handleUtrnApplied")
		.bean(mapper, "mapUtrnApplied")
		.log("create kafka event for utrn applied")
		.to("kafka:{{kafka.applyPaymentStatus.topic}}");
	}
	
	public void configureHandleMeterBalancePosted(){
		from("direct:handleMeterBalancePosted")
		.bean(mapper, "mapPostMeterBalance")
		.log("create kafka event for meter balance posted")
		.to("kafka:{{kafka.applyPaymentStatus.topic}}");
	}

	public void configureHandleUtrnPosted() {
		from("direct:handleUtrnPosted")
				.wireTap("direct:utrnReceivedEvent")
				.enrich("direct:invokeApplyUtrn",
						new AddHeadersAggregationStrategy()).choice().when()
				.simple("${header.resource_CamelHttpResponseCode} == '200'")
				.log("apply utrn requested successfully ${body}")
				.bean(mapper, "mapApplyPaymentUtrnRequested")
				.log("kafka event ready to be created - ${body}")
				.to("kafka:{{kafka.applyPaymentStatus.topic}}");

		from("direct:utrnReceivedEvent").bean(mapper, "mapUtrnReceived")
				.log("create kafka event for utrn received")
				.to("kafka:{{kafka.applyPaymentStatus.topic}}");

		from("direct:invokeApplyUtrn")
				.log("invoke apply utrn")
				.setHeader(
						Exchange.HTTP_URI,
						simple("{{midos.applyUtrn.url}}?paygProductId=${body.paygProductId}&value=${body.value}"
								+ "&utrn=${body.utrn}&action=apply"))
				.transform().simple("${null}").to("http://ignored");
	}

	public void configureGetUtrn() {
		from("direct:getUtrn")
				.log("request MIDOS to get UTRN")
				.enrich("direct:invokeGetUtrn",
						new AddHeadersAggregationStrategy()).choice().when()
				.simple("${header.resource_CamelHttpResponseCode} == '200'")
				.log("utrn requested successfully ${body}")
				.bean(mapper, "mapPaymentUtrnRequested")
				.log("kafka event ready to be created - ${body}")
				.to("kafka:{{kafka.applyPaymentStatus.topic}}");

		from("direct:invokeGetUtrn")
				.setHeader(
						Exchange.HTTP_URI,
						simple("{{midos.getUtrn.url}}?paygProductId=${body.paygProductId}&value=${body.value}"))
				.transform().simple("${null}").to("http://ignored");
	}

}
