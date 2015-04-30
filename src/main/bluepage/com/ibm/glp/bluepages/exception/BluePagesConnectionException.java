/*
 * History:
 * --------------------------------------------------------------
 * Date:			Author:				Description:
 * --------------------------------------------------------------
 * 14.05.2009		Yury Kuchmel		Initial creator
 */
package com.ibm.glp.bluepages.exception;

/**
 * @author Yury Kuchmel
 * @since GLP R2.2
 */
public class BluePagesConnectionException extends Exception {
	/*
	 * IBM Confidential
	 * 
	 * Copyright IBM Corp. 2004, 2008 All Rights Reserved.
	 * 
	 * The source code for this program is not published or otherwise divested
	 * of its trade secrets, irrespective of what has been deposited with the
	 * U.S. Copyright office.
	 */
	
	private static final String ERROR_MESSAGE = "Connection to BluePages failed.";
	
	private String statusMessage = null;
	
	public BluePagesConnectionException(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		if (statusMessage != null || statusMessage.trim().length() > 0) {
			return ERROR_MESSAGE + " Original status: " + statusMessage;	
		} else {
			return ERROR_MESSAGE;
		}
	}
	
}
