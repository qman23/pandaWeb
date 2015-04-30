/*
 * History:
 * --------------------------------------------------------------
 * Date:			Author:				Description:
 * --------------------------------------------------------------
 * 17.09.2008		Yury Kuchmel		Initial creator
 */
package com.ibm.glp.bluepages;

import java.util.List;

import com.ibm.glp.bluepages.exception.BPPersonNotFoundException;
import com.ibm.glp.bluepages.exception.BluePagesConnectionException;

/**
 * @author Yury Kuchmel
 *
 * 
 */
public interface IBluePagesFinder {
	/*
	 * IBM Confidential
	 * 
	 * Copyright IBM Corp. 2004, 2008 All Rights Reserved.
	 * 
	 * The source code for this program is not published or otherwise divested
	 * of its trade secrets, irrespective of what has been deposited with the
	 * U.S. Copyright office.
	 */
	
	public static final String BY_INTERNET_ADDRESS = "Internet address";
	public static final String BY_CNUM = "CNUM";
	public static final String BY_NAME = "Last name";
	public static final String BY_NOTES_MAIL = "Notes mail";
	public static final String BY_SERIAL_NUMBER = "Serial number";
	
	/**
	 * This method is to find person(s) by the specified criteria in the BluePages
	 * @param criteria
	 * @return person(s)
	 * @throws BPPersonNotFoundException - if IBM BluePages API returns nothing this exception will be thrown
	 * @throws BluePagesConnectionException - if connection to IBM BluePages API failed this exception will be thrown
	 */
	public List find(String criteria) throws BPPersonNotFoundException, BluePagesConnectionException;
}
