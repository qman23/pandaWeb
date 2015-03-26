/*
IBM Confidential
 * (C) Copyright IBM Corp. 2011.
 * All Rights Reserved - Licensed Materials - Property of IBM
 */
package com.ibm.healthchecktool.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.ibm.healthchecktool.config.ConfigProperty;
import com.ibm.healthchecktool.exception.ExceptionStacktraceHandler;
import com.ibm.healthchecktool.log.Logger;

public class UrlChecker {

    private static ArrayList<String> acceptStatus = new ArrayList<String>();
    
    static{
    	initStatusList();
    }
    
    public static boolean isActiveUrl(String urlStr) throws Exception {

        try{
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int ret = connection.getResponseCode();
            connection.disconnect();
            return isAcceptStatus(ret);
        }catch(Exception e){
            String exceptionMessage = ExceptionStacktraceHandler.stackTraceToString(e);
            Logger.getLogger().error("Checking " + urlStr + " failed \n" + exceptionMessage);
            return false;
        }
    }
    
    private static boolean isAcceptStatus(int ret){
    	return acceptStatus.contains(String.valueOf(ret));
    }
    
	private static void initStatusList() {
		String[] codes = ConfigProperty.getPropertyByKey("url.match.status")
				.split(",");
		for (String code : codes) {
			acceptStatus.add(code);
		}
	}

}
