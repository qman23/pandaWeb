/*
 * History:
 * --------------------------------------------------------------
 * Date:			Author:				Description:
 * --------------------------------------------------------------
 * 17.09.2008		Yury Kuchmel		Initial creator
 * 22.05.2009		Kirill Ezerski		properties managerPersNum, managerCountry added (CR20090064)
 */
package com.ibm.glp.bluepages;

import com.ibm.glp.bluepages.utilities.NotesAddressWrapper;

/**
 * @author Yury Kuchmel
 * 
 *  
 */
public class BPPerson {
	/*
	 * IBM Confidential
	 * 
	 * Copyright IBM Corp. 2004, 2008 All Rights Reserved.
	 * 
	 * The source code for this program is not published or otherwise divested
	 * of its trade secrets, irrespective of what has been deposited with the
	 * U.S. Copyright office.
	 */

	String cNUM;
	String name;
	String firstName;
	String lastName;
	String internetAddress;
	NotesAddressWrapper notesAddress;
	String managerCNUM;
	String managerPersNum;
	String managerCountry;	
	String department;
	String tie;
	String jobResponsibility;
	String xPhone;
	String countryCode;
	String empType;
	String userid;

	/**
	 * @return the cNUM
	 */
	public String getCNUM() {
		return cNUM;
	}

	/**
	 * @param cnum
	 *            the cNUM to set
	 */
	public void setCNUM(String cnum) {
		cNUM = cnum;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the internetAddress
	 */
	public String getInternetAddress() {
		return internetAddress;
	}

	/**
	 * @param internetAddress
	 *            the internetAddress to set
	 */
	public void setInternetAddress(String internetAddress) {
		this.internetAddress = internetAddress;
	}

	/**
	 * @return the jobResponsibility
	 */
	public String getJobResponsibility() {
		return jobResponsibility;
	}

	/**
	 * @param jobResponsibility
	 *            the jobResponsibility to set
	 */
	public void setJobResponsibility(String jobResponsibility) {
		this.jobResponsibility = jobResponsibility;
	}

	/**
	 * @return the managerCNUM
	 */
	public String getManagerCNUM() {
		return managerCNUM;
	}

	/**
	 * @param managerCNUM
	 *            the managerCNUM to set
	 */
	public void setManagerCNUM(String managerCNUM) {
		this.managerCNUM = managerCNUM;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the notesId
	 */
	public String getNotesAddress() {
		if (notesAddress == null) return null;
		return notesAddress.toString();
	}

	/**
	 * @param notesId
	 *            the notesId to set
	 */
	public void setNotesAddress(NotesAddressWrapper wrapper) {
		this.notesAddress = wrapper;
	}

	/**
	 * @return the tie
	 */
	public String getTie() {
		return tie;
	}

	/**
	 * @param tie
	 *            the tie to set
	 */
	public void setTie(String tie) {
		this.tie = tie;
	}

	/**
	 * @return the xPhone
	 */
	public String getXPhone() {
		return xPhone;
	}

	/**
	 * @param phone
	 *            the xPhone to set
	 */
	public void setXPhone(String phone) {
		xPhone = phone;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return Returns the countryCode.
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode The countryCode to set.
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	/**
	 * @return Returns the empType.
	 */
	public String getEmpType() {
		return empType;
	}
	/**
	 * @param empType The empType to set.
	 */
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	/**
	 * @return Returns the userid.
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid The userid to set.
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return Returns the managerCountry.
	 */
	public String getManagerCountry() {
		return managerCountry;
	}
	/**
	 * @param managerCountry The managerCountry to set.
	 */
	public void setManagerCountry(String managerCountry) {
		this.managerCountry = managerCountry;
	}
	/**
	 * @return Returns the managerPersNum.
	 */
	public String getManagerPersNum() {
		return managerPersNum;
	}
	/**
	 * @param managerPersNum The managerPersNum to set.
	 */
	public void setManagerPersNum(String managerPersNum) {
		this.managerPersNum = managerPersNum;
	}
}
