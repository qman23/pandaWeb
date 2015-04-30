/*
 * History:
 * --------------------------------------------------------------
 * Date:			Author:				Description:
 * --------------------------------------------------------------
 * 17.09.2008		Yury Kuchmel		Initial creator
 * 22.05.2009		Kirill Ezerski		mapToBPPerson() method updated (map managerPersNum, managerCountry) (CR20090064)
 */
package com.ibm.glp.bluepages;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import com.ibm.bluepages.BPResults;
import com.ibm.glp.bluepages.exception.BPPersonNotFoundException;
import com.ibm.glp.bluepages.utilities.NotesAddressWrapper;

/**
 * @author Yury Kuchmel
 *
 *  
 */
public class BPResultMapper {
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
	 * This method is to convert information from the BluePages to application specific objects.
	 * The method can return empty list if no records were found in the BluePages. 
	 * @param results
	 * @return list of BPPerson instances.
	 */
	public List parseBPResults(BPResults results) throws BPPersonNotFoundException {
		List persons = new LinkedList();
		if (results != null && results.rows() > 0) {
			for (int rowNumber = 0; rowNumber < results.rows(); rowNumber++) {
				persons.add(mapToBPPerson(results.getRow(rowNumber)));
			}
		} else {
			throw new BPPersonNotFoundException();
		}
		return persons;
	}

	
	/**
	 * This method is to map information from BluePages to the application object
	 * @param personRow
	 * @return instance of BPPerson type
	 */
	protected BPPerson mapToBPPerson(Hashtable personRow) {
		BPPerson thePerson = new BPPerson();
		thePerson.setCNUM((String) personRow.get("CNUM"));
		thePerson.setDepartment((String) personRow.get("DEPT"));
		thePerson.setInternetAddress((String) personRow.get("INTERNET"));
		thePerson.setJobResponsibility((String) personRow.get("JOBRESPONSIB")) ;
		thePerson.setName((String) personRow.get("NAME"));
		populatePersonName(thePerson);
		thePerson.setNotesAddress(new NotesAddressWrapper((String) personRow.get("NOTESID")));
		thePerson.setManagerCNUM((String) personRow.get("MNUM"));
		thePerson.setManagerPersNum((String) personRow.get("MGRNUM"));
		thePerson.setManagerCountry((String) personRow.get("MGRCC"));
		thePerson.setTie((String) personRow.get("TIE"));
		thePerson.setXPhone((String) personRow.get("XPHONE"));
		thePerson.setCountryCode((String) personRow.get("EMPCC"));
		thePerson.setEmpType((String) personRow.get("EMPTYPE"));
		thePerson.setUserid((String) personRow.get("USERID"));
		return thePerson;
	}
	
	private void populatePersonName(BPPerson person) {
		int index = person.getName().indexOf('*');
		String fullName = person.getName();
		if (index >= 0) {
			fullName = person.getName().substring(0, index).trim();
		}
		String [] userNames = fullName.split(",");
		if (userNames != null){
			if (userNames.length > 0 && userNames[0] != null) {
				person.setLastName(userNames[0].trim());
			} else {
				person.setLastName("");
			}
			if (userNames.length > 1 && userNames[1] != null) {
				person.setFirstName(userNames[1].trim());
			} else {
				person.setFirstName("");
			}
		}
	}
	
}
