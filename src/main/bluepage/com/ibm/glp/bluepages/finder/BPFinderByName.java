/*
 * History:
 * --------------------------------------------------------------
 * Date:			Author:				Description:
 * --------------------------------------------------------------
 * 17.09.2008		Yury Kuchmel		Initial creator
 */
package com.ibm.glp.bluepages.finder;

import java.util.List;

import com.ibm.bluepages.BPResults;
import com.ibm.bluepages.BluePages;
import com.ibm.glp.bluepages.BPResultMapper;
import com.ibm.glp.bluepages.IBluePagesFinder;
import com.ibm.glp.bluepages.exception.BluePagesConnectionException;
import com.ibm.glp.bluepages.exception.BPPersonNotFoundException;

/**
 * @author Yury Kuchmel
 *
 *  
 */
public class BPFinderByName implements IBluePagesFinder {
	/*
	 * IBM Confidential
	 * 
	 * Copyright IBM Corp. 2004, 2008 All Rights Reserved.
	 * 
	 * The source code for this program is not published or otherwise divested
	 * of its trade secrets, irrespective of what has been deposited with the
	 * U.S. Copyright office.
	 */
	
	/* (non-Javadoc)
	 * @see com.ibm.glp.bluepages.IBluePagesFinder#find(java.lang.String)
	 */
	public List find(String criteria) throws BPPersonNotFoundException, BluePagesConnectionException {
		BPResults results = BluePages.getPersonsByNameLite(criteria);
		if (!results.succeeded()) {
			throw new BluePagesConnectionException(results.getStatusMsg());
		}
		BPResultMapper mapper = new BPResultMapper();
		List persons = mapper.parseBPResults(results);
		return persons;
	}
	
}
