package com.eon.applypayment.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilz {

	public static String getCurrentDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:Ss");
		return sdf.format(new Date());
	}
}
