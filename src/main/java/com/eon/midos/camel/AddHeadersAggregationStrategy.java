package com.eon.applypayment.camel;

import java.util.Set;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class AddHeadersAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange original, Exchange resource) {
         // use body from getIn() or getOut() depending on the exchange pattern...
//    	if(original!=null){
    		System.out.println("original In- "+original.getIn().getBody());
    		System.out.println("original out "+original.getOut());
//    	}
    	System.out.println("resource In- "+resource.getIn().getBody());
    	System.out.println("resource Out- "+resource.getOut().getBody());
    	resource.getIn().getHeaders().entrySet().forEach(a -> System.out.println(a));
//        original.getIn().setBody(resource.getIn().getBody());
        Set<String> set = original.getIn().getHeaders().keySet();;
        for(String key : set){
        	System.out.println("key "+key);
        	System.out.println("value - "+original.getOut().getHeaders().get(key));
        }
        resource.getIn().getHeaders().forEach((k, v) -> original.getIn().getHeaders().put("resource_"+k, v));
//        original.getIn().getHeaders().put("CamelHttpResponseCode", resource.getIn().getHeader("CamelHttpResponseCode"));
        System.out.println("jlkjlkjlkj    "+original.getIn().getHeader("resource_CamelHttpResponseCode"));
        original.getOut().setBody(original.getIn().getBody());
        original.getOut().setHeaders(original.getIn().getHeaders());
        return original;
    }
}