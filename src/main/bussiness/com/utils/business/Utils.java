package com.utils.business;

import java.sql.Timestamp;

public class Utils {
		public static Timestamp getCurrentTimes(){
			return new Timestamp(System.currentTimeMillis());
		}
		
		public static boolean isStringNull(String str){
			return str==null||str=="";
		}
}
