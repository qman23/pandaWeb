/*
 * History:
 * --------------------------------------------------------------
 * Date:			Author:				Description:
 * --------------------------------------------------------------
 * 17.09.2008		Yury Kuchmel		Initial creator
 */
package com.ibm.glp.bluepages;

import com.ibm.glp.bluepages.finder.BPFinderByCNUM;
import com.ibm.glp.bluepages.finder.BPFinderByEmail;
import com.ibm.glp.bluepages.finder.BPFinderByName;
import com.ibm.glp.bluepages.finder.BPFinderByNotesEmail;
import com.ibm.glp.bluepages.finder.BPFinderBySerial;

/**
 * @author Yury Kuchmel
 *
 *  
 */
public class BPFinderFactory {
	/*
	 * IBM Confidential
	 * 
	 * Copyright IBM Corp. 2004, 2008 All Rights Reserved.
	 * 
	 * The source code for this program is not published or otherwise divested
	 * of its trade secrets, irrespective of what has been deposited with the
	 * U.S. Copyright office.
	 */
	
	/**
	 * This method is to create BluePages finder for the particular kind of search.
	 * @param searchType
	 * @return finder itself
	 */
	public static IBluePagesFinder createFinder(String searchType) throws Exception	{
		if (searchType == null || "".equals(searchType)) {
			throw new Exception("Application cannot start search on BluePages without search type. Please select correct search type on the page.");
		}
		IBluePagesFinder finder = null;
		if (IBluePagesFinder.BY_INTERNET_ADDRESS.equals(searchType)) {
			finder = new BPFinderByEmail();
		} else if (IBluePagesFinder.BY_NAME.equals(searchType)) {
			finder = new BPFinderByName();
		} else if (IBluePagesFinder.BY_NOTES_MAIL.equals(searchType)) {
			finder = new BPFinderByNotesEmail();
		} else if (IBluePagesFinder.BY_SERIAL_NUMBER.equals(searchType)) {
			finder = new BPFinderBySerial();
		} else if (IBluePagesFinder.BY_CNUM.equals(searchType)) {
			finder = new BPFinderByCNUM();
		}
		return finder;
	}
	
}
