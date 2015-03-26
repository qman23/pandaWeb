/*
IBM Confidential
 * (C) Copyright IBM Corp. 2011.
 * All Rights Reserved - Licensed Materials - Property of IBM
 */

package com.ibm.healthchecktool.exception;

/**
 * ExceptionStacktraceHandler class
 * 
 * A java utility to get messages from exception stacktrace
 * 
 */
public class ExceptionStacktraceHandler {
    
    private static final String TAB = "        ";
    
    /**
     * stackTraceToString
     * 
     * @param Exception e
     * 
     * @return String
     *             stacktrace messages
     */
    public static String stackTraceToString(Exception e){
        
        StringBuffer sb = new StringBuffer();
        
        sb.append(e + "\n");
        
        for(int i=0;i<e.getStackTrace().length;i++){
            sb.append(TAB + "at " + e.getStackTrace()[i] + "\n");
        }
        
        return sb.toString();
    }
    
}
