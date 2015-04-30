package com.ibm.glp.bluepages;

import java.util.ArrayList;
import java.util.List;



import com.ibm.glp.bluepages.exception.BPPersonNotFoundException;


/**
 * Class provides various methods to fetch employee details from Blue Pages
 * @author Viswanath
 *
 */
public class BPFinder {
	/**
	 * searches by last name and fetches employee details from blue pages 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<BPPerson> serachByLastName(String name) throws Exception
	{
		return searchBP(name, IBluePagesFinder.BY_NAME);
	}
	
	/**
	 * searches by notes mail and fetches employee details from blue pages
	 * @param mail
	 * @return
	 * @throws Exception
	 */
	public List<BPPerson> serachByNotesMail(String mail) throws Exception
	{
		return searchBP(mail,IBluePagesFinder.BY_NOTES_MAIL);
	}
	
	/**
	 * searches by serial number and fetches employee details from blue pages
	 * @param serialNumber
	 * @return
	 * @throws Exception
	 */
	public List<BPPerson> serachBySerialNumber(String serialNumber) throws Exception
	{
		return searchBP(serialNumber,IBluePagesFinder.BY_SERIAL_NUMBER);
	}
	
	/**
	 * searches by internet address and fetches employee details from blue pages
	 * @param inetAdress
	 * @return
	 * @throws Exception
	 */
	public List<BPPerson> searchByInternetAddress(String inetAdress) throws Exception
	{
		return searchBP(inetAdress,IBluePagesFinder.BY_INTERNET_ADDRESS );
	}
	
	/**
	 * searches by slno + countrycode and fetches employee details from blue pages 
	 * @param cnum
	 * @return
	 * @throws Exception
	 */
	public List<BPPerson> searchBycNum(String cnum) throws Exception
	{
		return searchBP(cnum,IBluePagesFinder.BY_CNUM );
	}
	
	/**
	 * 
	 * @param searchString
	 * @param searchType
	 * @return
	 * @throws Exception
	 */
	public List<BPPerson> searchBP(String searchString, String searchType) throws Exception 
	{
		final String USER_SEARCH_TYPE = searchType;
		BPPerson user=null;
		List foundPersons=new ArrayList<BPPerson>();
		try {
			IBluePagesFinder userFinder = BPFinderFactory.createFinder(USER_SEARCH_TYPE);
			
			foundPersons= userFinder.find(searchString);
			return foundPersons;
		} catch (BPPersonNotFoundException e) {
			if (searchType.equals(IBluePagesFinder.BY_CNUM))
				return null;
		}
		return foundPersons;
		
	}
	
	
	public static void main(String[] args) {
//		BPFinder finder = new BPFinder();
//		try {
////			BPPerson person = finder.searchByInternetAddress("lizhiwcd@cn.ibm.com");
//			String nameListStr = "Han YW Zhang/China/Contr/IBM@IBMCN";
//			String[] str = nameListStr.split(",");
//			for (String string : str) {
//				BPPerson person = finder.serachByNotesMail(string);
//				System.out.println(person.getInternetAddress());
//			}
////			BPPerson person = finder.serachByNotesMail("Zhi Wei CD Li/China/IBM@IBMCN");
////			
////			System.out.println(person.getInternetAddress());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
