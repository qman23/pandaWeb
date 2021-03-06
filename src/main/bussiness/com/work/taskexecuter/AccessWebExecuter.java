package com.work.taskexecuter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bussiness.exception.BusinessException;
import com.entity.work.Task;
import com.utils.business.PandaConstants;
import com.utils.business.Utils;
import com.work.bussiness.Executer;

/**
 * 
 * @author Allen
 *
 *	provide access web function by the URL and UserName Password.
 *
 *	if there is no user name or password just access the Url directly.
 *
 *	Put the view result into context map .
 */
public class AccessWebExecuter implements Executer{
	private static Logger log=Logger.getLogger(AccessWebExecuter.class.getName());
	
	/**
	 * implement the interface executer access the URL and put the return HTML code into result map.
	 */
	public Map execute(Task t, Map context) throws BusinessException {
		String url= String.valueOf(t.getData().get("Url"));
		String userName= String.valueOf(t.getData().get("UserName"));
		String passWord= Utils.decrypt(String.valueOf(t.getData().get("Password")));
		log.info(("User Id:"+t.getUserId()+"--Task Id:"+t.getTaskId()+"--Name:"+t.getTaskName()+"--Task Parameter:"+t.getTaskParameter()+"--Begin Running!"));
		String result;
		try {
			result = getContentByUrl(url,userName,passWord);
			Utils.putExecuteResult(t, context, Utils.isStringNull(result)?"":result);
			context.put(t.getTaskId(), PandaConstants.TASK_SUCCESS);
		} catch (IOException e) {
			context.put(t.getTaskId(), PandaConstants.TASK_FAILED);
			throw new BusinessException(e);
		}
		log.info(("User Id:"+t.getUserId()+"--Task Id:"+t.getTaskId()+"--Name:"+t.getTaskName()+"--Task Parameter:"+t.getTaskParameter()+"--Running Complete!"));
		return context;
	}
	
	/**
	 * 
	 * @author Allen
	 *	
	 * New access entity extends from authenticator.
	 */
	static class MyAuthenticator extends Authenticator {
		private static String kuser = ""; 
		private static String kpass = "";
		public MyAuthenticator(String userName,String passWord){
			kuser=userName;
			kpass=passWord;
		}
		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			return (new PasswordAuthentication(kuser, kpass.toCharArray()));
		}
	}
	
	/**
	 * 
	 * @param webUrl
	 * @param userName
	 * @param passWord
	 * @return
	 * @throws IOException
	 * 
	 *  get the context by the URL( if the username and password is exist then use them ).
	 */
	private static String getContentByUrl(String webUrl,String userName,String passWord) throws IOException {
		URL url = null;
		HttpURLConnection conn = null;
		BufferedReader bf = null;
		StringBuffer accum = null;
		InputStream inStream =null;
		if(!(Utils.isStringNull(userName)&&Utils.isStringNull(passWord))){
			Authenticator.setDefault(new MyAuthenticator(userName,passWord));
		}
		try {
			url = new URL(webUrl);
			conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			if(conn.getResponseCode() != 200){
				throw new ConnectException("error : HTTP responseCode is "+conn.getResponseCode());
			}
			else{
				inStream= conn.getInputStream();
				bf = new BufferedReader(new InputStreamReader(inStream));
				String line;
				accum = new StringBuffer();
				while (((line = bf.readLine()) != null)) {
					accum.append(line+"\n");
				}
			}
		}finally{
			if(bf != null){ 
				bf.close();
			}if(inStream!=null){
				inStream.close();
			}
			if(conn != null){
				conn.disconnect();
			}
		}
		return accum.toString();
	}
}
