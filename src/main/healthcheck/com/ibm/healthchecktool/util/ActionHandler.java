package com.ibm.healthchecktool.util;

import java.io.IOException;

import com.ibm.healthchecktool.bean.ConnBean;

public class ActionHandler {
	public String executeCommond(SSHUtil util,String commond) throws Exception{
		return util.execCmd(commond);
	}
	
	public static void main(String[] args) throws IOException {
		ActionHandler handler = new ActionHandler();
		SSHUtil util = new SSHUtil();
		try {
			ConnBean bean = new ConnBean();
			bean.setHost("mopbz5231.cloud.dst.ibm.com");
			bean.setPort("22");
			bean.setName("db2inst1");
			bean.setPwd("sprt9wrd");
			util.connect(bean);
			System.out.println(handler.executeCommond(util, "ls"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(util.isConnected()){
				util.disconnect();
			}
		}
	}
}
