/*
IBM Confidential
 * (C) Copyright IBM Corp. 2011.
 * All Rights Reserved - Licensed Materials - Property of IBM
 */
package com.ibm.healthchecktool.log;

import org.apache.log4j.xml.DOMConfigurator;

/**
 * Logger
 * 
 * Logger class
 * 
 */
public class Logger {
    
    
    private static final String LOG_CLASS = "log";
    private static final String LOG_CONFIG_PATH = "log4j.xml";
    
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LOG_CLASS);

    /**
     * configLogResource
     * 
     */
    public static void configLogResource() {
        DOMConfigurator.configure(LOG_CONFIG_PATH);
    }
        
    /**
     * getLogger
     * 
     * @return Logger
     */
    public static org.apache.log4j.Logger getLogger(){
        return log;
    }
}
