package com.eon.applypayment.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilz {

	public static String getCurrentDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		return sdf.format(new Date());
	}
}
