/*
IBM Confidential
 * (C) Copyright IBM Corp. 2011.
 * All Rights Reserved - Licensed Materials - Property of IBM
 */
package com.ibm.healthchecktool.util;

import java.util.UUID;

public class IDGererator{
    
    public static String getUUID(){
        
        return UUID.randomUUID().toString().replace("-","");
    }
    
}