/*
 * History:
 * --------------------------------------------------------------
 * Date:			Author:				Description:
 * --------------------------------------------------------------
 * 19.09.2008		Yury Kuchmel		Initial creator
 */
package com.ibm.glp.bluepages.utilities;

/**
 * @author Yury Kuchmel
 *
 */
public class NotesAddressWrapper {
	/*
	 * IBM Confidential
	 * 
	 * Copyright IBM Corp. 2004, 2008 All Rights Reserved.
	 * 
	 * The source code for this program is not published or otherwise divested
	 * of its trade secrets, irrespective of what has been deposited with the
	 * U.S. Copyright office.
	 */
	
	private String notesId;
	
	/**
	 * 
	 */
	public NotesAddressWrapper(String notesId) {
		this.notesId = notesId;
	}

	public String toString() {
		String notesAddress = notesId;
		notesAddress = notesAddress.replaceAll("CN=", "");
		notesAddress = notesAddress.replaceAll("OU=", "");
		notesAddress = notesAddress.replaceAll("O=", "");
		return notesAddress;
	}
	
	public String wrapNotesAddress(){
		StringBuffer notesAddress = new StringBuffer();
		String [] parts = this.toString().split("/");
		if (parts.length > 0){
			notesAddress.append("CN=%").append(parts[0]);
			if(parts.length > 1){
				notesAddress.append("/OU=%").append(parts[1]);
				if(parts.length > 2){
					if(parts[2].toUpperCase().startsWith("IBM@")){
						notesAddress.append("/O=%").append(parts[2]);
					}else{
						notesAddress.append("/OU=%").append(parts[2]);
						if(parts.length > 3 && parts[3].toUpperCase().startsWith("IBM@")){
							notesAddress.append("/O=%").append(parts[3]);
						}
					}
				}
			}
		}
		return notesAddress.toString();
	}
	
	public static String wrapNotesAddress(String notes){
		StringBuffer notesAddress = new StringBuffer();
		String [] parts = notes.split("/");
		if (parts.length > 0){
			notesAddress.append("CN=").append(parts[0]);
			if(parts.length > 1){
				notesAddress.append("/OU=").append(parts[1]);
				if(parts.length > 2){
					if(parts[2].toUpperCase().startsWith("IBM@")){
						notesAddress.append("/O=").append(parts[2]);
					}else{
						notesAddress.append("/OU=").append(parts[2]);
						if(parts.length > 3 && parts[3].toUpperCase().startsWith("IBM@")){
							notesAddress.append("/O=").append(parts[3]);
						}
					}
				}
			}
		}
		return notesAddress.toString();
	}
}
