/*
IBM Confidential
 * (C) Copyright IBM Corp. 2011.
 * All Rights Reserved - Licensed Materials - Property of IBM
 */

package com.ibm.healthchecktool.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigProperty
 * 
 * Load all property needed in project
 * 
 */
public class ConfigProperty {
    
    private static Properties prop = new Properties();
    
    private static FileInputStream fis;
    
    private static FileWriter fw;
    
    private static boolean isLoaded = false;
    
    private static final String PROP_PATH = "config.properties";
    
    /**
     * Initiate property file
     * 
     * @throws Exception
     * 
     */
    public static void loadProperty() throws Exception{
        if(!isLoaded){
            fis = getPropAsStream();
            prop.load(fis);
        }
    }
    
    /**
     * get property by key
     * 
     * @param key
     * 
     */
    public static String getPropertyByKey(String key) {
        return prop.getProperty(key);
    }
    
    /**
     * set property by key
     * 
     * @param key
     * @param value
     * 
     */
    public static void setPropertyByKey(String key,String value) {
        prop.setProperty(key, value);
    }
    
    /**
     * save property
     * 
     */
    public static void saveProperty(){
        try {
            fw = getPropWriter();
            prop.store(fw, "");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(fw!=null){
                try {
                    fw.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * clear property
     * 
     */
    public static void clearProperty(){
        prop.clear();
        isLoaded = false;
    }
    
    /**
     * get File Input Stream
     * 
     * @return FileInputStream
     * 
     */
    private static FileInputStream getPropAsStream() throws FileNotFoundException{
        return new FileInputStream(new File(PROP_PATH));
    }
    
    /**
     * get File Writer
     * 
     * @return FileWriter
     * 
     */    
    private static FileWriter getPropWriter() throws FileNotFoundException{
        try {
            return new FileWriter(new File(PROP_PATH));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
