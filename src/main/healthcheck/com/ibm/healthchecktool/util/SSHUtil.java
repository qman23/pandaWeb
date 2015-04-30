/*
IBM Confidential
 * (C) Copyright IBM Corp. 2011.
 * All Rights Reserved - Licensed Materials - Property of IBM
 */
package com.ibm.healthchecktool.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.ibm.healthchecktool.bean.ConnBean;
import com.ibm.healthchecktool.config.ConfigProperty;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

@Service("sSHUtil")
public class SSHUtil {

	private ChannelExec exec;
	private ChannelShell shell;
	private Session session;
	private static final int SESSION_TIMEOUT = 10000;
	private static final int CMD_TIMEOUT;
	private InputStream in;
	private OutputStream out;
	private InputStream err;
	private static final String TYPE_EXEC = "exec";
	private static final String TYPE_SFTP = "sftp";
	private boolean isTimeout;
	private Map<Channel,Session> context;

	static {
		CMD_TIMEOUT = Integer.parseInt(ConfigProperty
				.getPropertyByKey("cmd.timeout"));
	}

	private Session getSession(String host, int port, String userName)
			throws Exception {
		JSch jsch = new JSch();
		Session session = jsch.getSession(userName, host, port);
		return session;
	}

	public void connect(String host, int port, String userName, String password)
			throws Exception {
		session = getSession(host, port, userName);
		session.setTimeout(SESSION_TIMEOUT);
		session.setPassword(password);
		Properties config = new Properties();
		config.setProperty("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
	}

	public void connect(ConnBean connBean) throws NumberFormatException,
			Exception {
		connect(connBean.getHost(), Integer.parseInt(connBean.getPort()),
				connBean.getName(), connBean.getPwd());
	}

	private void connectExec(String type) throws Exception {
		if (session == null) {
			throw new RuntimeException("Not connected");
		}
		exec = (ChannelExec) session.openChannel(type);
	}

	public String execCmd(String command) throws Exception {
		if (exec == null || !exec.isConnected()) {
			connectExec(TYPE_EXEC);
		}
		exec.setCommand(command);
		in = exec.getInputStream();
		err = exec.getErrStream();
		exec.connect();

		isTimeout = true;

		waitExecute();
		// if(isTimeout){
		// throw new Exception("Timeout in executing cmd:" + command +
		// " after + " + ((double)CMD_TIMEOUT)/1000 + " seconds");
		// }

		int exitStatus = exec.getExitStatus();
		if (exitStatus != 0 && exitStatus != -1) {
			String error = getErrorMessage().trim();
			if (error != null && !error.trim().equals("")) {
				throw new Exception("Error found in executing cmd:" + command
						+ "\n" + error);
			}
		}

		String result = getReturnMessage();
		return result;
	}

	public String shell(String cmd) throws Exception {
		if(shell==null||context==null){
			newSessionShell();
		}
		if(!((Session)context.get(shell)).isConnected()){
			newSessionShell();
		}
		if(!shell.isConnected()){
			shell.connect();
		}
		if(in==null&out==null){
			in = shell.getInputStream();
			out = shell.getOutputStream();
			String shellCommand = cmd+" \n";
			out.write(shellCommand.getBytes());
			out.flush();
			isTimeout = true;
			waitExecute();
			return getReturnData(in);
		}else{
			String shellCommand = cmd+" \n";
			out.write(shellCommand.getBytes());
			out.flush();
			isTimeout = true;
			waitExecute();
			return getReturnData(in);
		}
	}

	public String getReturnMessage() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = "";
		StringBuffer buffer = new StringBuffer();
		while ((line = br.readLine()) != null) {
			if (!line.trim().equals("")) {
				buffer.append(line.trim() + "\n");
			}
		}
		return buffer.toString();
	}

	private String getReturnData(InputStream in) throws UnsupportedEncodingException, IOException{
		StringBuffer sb=new StringBuffer();
		while(in.available() > 0){
			byte[] data = new byte[256];
			int nLen = in.read(data);
			sb.append(new String(data, 0,  nLen));
		}
		//System.out.println(sb.toString());
		return sb.toString();
	}
	
	private void newSessionShell() throws JSchException{
		shell = (ChannelShell) session.openChannel("shell");
		context=new HashMap<Channel,Session>();
		context.put(shell, session);
	}
	
	private void waitExecute() throws InterruptedException{
		Thread thread = new Thread() {
			public void run() {
				while (!shell.isClosed()) {
					try {
						sleep(100);
					} catch (Exception e) {
						// ignored
					}
				}
				isTimeout = false;
			}
		};

		thread.start();
		thread.join(CMD_TIMEOUT);
	}
	
	
	public String getErrorMessage() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(err));
		String line = "";
		StringBuffer buffer = new StringBuffer();
		while ((line = br.readLine()) != null) {
			if (!line.trim().equals("")) {
				buffer.append(line.trim() + "\n");
			}
		}
		return buffer.toString();
	}

	public void disconnect() throws IOException {
		if (session != null && session.isConnected()) {
			session.disconnect();
			if (exec != null && exec.isConnected()) {
				exec.disconnect();
			}
			if (shell != null && shell.isConnected()) {
				shell.disconnect();
			}
		}
		if(in!=null){
			in.close();
			in=null;
		}if(out!=null){
			out.close();
			out=null;
		}
		if( err!=null){
			err.close();
			out=null;
		}
	}
	
	public void destorySession(){
		if(session!=null){
			session=null;
		}
	}

	public boolean isConnected() {
		return session.isConnected();
	}
}