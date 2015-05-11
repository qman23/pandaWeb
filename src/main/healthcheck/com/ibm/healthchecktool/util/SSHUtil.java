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

/**
 * 
 * @author Allen
 * Use to connect server and execute command use ThreadLocal to avoid multithreading problem.
 * 
 * You can get the instance from sprig utils 
 */
@Service("sSHUtil")
public class SSHUtil {

	private ChannelExec exec;
	private InheritableThreadLocal<ChannelShell> shell=new InheritableThreadLocal<ChannelShell>();
	private ThreadLocal<Session> session=new ThreadLocal<Session>();
	private static final int SESSION_TIMEOUT = 10000;
	//waiting time out parameter which read from properties file .
	private static final int CMD_TIMEOUT;
	private InheritableThreadLocal<InputStream> in=new InheritableThreadLocal<InputStream>();
	private ThreadLocal<OutputStream> out=new ThreadLocal<OutputStream>();
	private ThreadLocal<InputStream> err=new ThreadLocal<InputStream>();
	private static final String TYPE_EXEC = "exec";
	private static final String TYPE_SFTP = "sftp";
	private boolean isTimeout;
	private ThreadLocal<Map<Channel,Session>> context;

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
		session.set(getSession(host, port, userName));
		session.get().setTimeout(SESSION_TIMEOUT);
		session.get().setPassword(password);
		Properties config = new Properties();
		config.setProperty("StrictHostKeyChecking", "no");
		session.get().setConfig(config);
		session.get().connect();
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
		exec = (ChannelExec) session.get().openChannel(type);
	}

	public String execCmd(String command) throws Exception {
		if (exec == null || !exec.isConnected()) {
			connectExec(TYPE_EXEC);
		}
		exec.setCommand(command);
		in.set(exec.getInputStream());
		err.set(exec.getErrStream());
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

	/**
	 * 
	 * @param cmd
	 * @return execute result.
	 * @throws Exception
	 * 
	 * execute the cmd and return the result 
	 * 
	 * get the exists shell and session instance from context to execute the command for one thread or one executer service.
	 */
	public String shell(String cmd) throws Exception {
		if(shell.get()==null||context.get()==null){
			newSessionShell();
		}
		if(!((Session)context.get().get(shell.get())).isConnected()){
			newSessionShell();
		}
		if(!shell.get().isConnected()){
			shell.get().connect();
		}
		if(in.get()==null&out.get()==null){
			in.set(shell.get().getInputStream());
			out.set(shell.get().getOutputStream());
			String shellCommand = cmd+" \n";
			out.get().write(shellCommand.getBytes());
			out.get().flush();
			isTimeout = true;
			waitExecute();
			return getReturnData(in.get());
		}else{
			String shellCommand = cmd+" \n";
			out.get().write(shellCommand.getBytes());
			out.get().flush();
			isTimeout = true;
			waitExecute();
			return getReturnData(in.get());
		}
	}

	public String getReturnMessage() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(in.get()));
		String line = "";
		StringBuffer buffer = new StringBuffer();
		while ((line = br.readLine()) != null) {
			if (!line.trim().equals("")) {
				buffer.append(line.trim() + "\n");
			}
		}
		return buffer.toString();
	}

	/**
	 *  
	 * @param in
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * 
	 * return the read message from input byte Stream .
	 */
	private String getReturnData(InputStream in) throws UnsupportedEncodingException, IOException{
		StringBuffer sb=new StringBuffer();
		while(in.available()>0){
			byte[] data = new byte[1024];
			int nLen = in.read(data);
			sb.append(new String(data, 0,  nLen));
		}
		return sb.toString();
		//System.out.println(sb.toString());
	}
	
	/**
	 * 
	 * @throws JSchException
	 * init new shell and put the shell and session into context map.
	 */
	private void newSessionShell() throws JSchException{
		shell.set( (ChannelShell) session.get().openChannel("shell"));
		context=new ThreadLocal<Map<Channel,Session>>();
		context.set(new HashMap<Channel,Session>());
		context.get().put(shell.get(), session.get());
	}
	
	private void waitExecute() throws InterruptedException{
		Thread thread = new Thread() {
			public void run() {
				try {
					while (true) {
						sleep(100);
					}
				} catch (Exception e) {
					// ignored
				}
				isTimeout = false;
				System.out.println(System.currentTimeMillis()+"sleep complate.");
			}
		};
		System.out.println(System.currentTimeMillis()+"sleep begin.");
		thread.start();
		thread.join(CMD_TIMEOUT);
	}
	
	
	public String getErrorMessage() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(err.get()));
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
		if (session.get() != null && session.get().isConnected()) {
			session.get().disconnect();
			if (exec != null && exec.isConnected()) {
				exec.disconnect();
			}
			if (shell.get()!= null && shell.get().isConnected()) {
				shell.get().disconnect();
			}
		}
		if(in.get()!=null){
			in.get().close();
			in.set(null);
		}if(out.get()!=null){
			out.get().close();
			out.set(null);
		}
		if( err.get()!=null){
			err.get().close();
			out.set(null);
		}
	}
	
	public void destorySession(){
		if(session.get()!=null){
			session.set(null);
		}
	}

	public boolean isConnected() {
		return session.get().isConnected();
	}
}