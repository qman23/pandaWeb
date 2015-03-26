/*
IBM Confidential
 * (C) Copyright IBM Corp. 2011.
 * All Rights Reserved - Licensed Materials - Property of IBM
 */
package com.ibm.healthchecktool.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.ibm.healthchecktool.config.ConfigProperty;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSHUtil {

	private ChannelExec exec;
	private Session session;
	private static final int SESSION_TIMEOUT = 10000;
	private static final int CMD_TIMEOUT;
	private InputStream in;
	private InputStream err;
	private static final String TYPE_EXEC = "exec";
	private static final String TYPE_SFTP = "sftp";
	private boolean isTimeout;

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

		Thread thread = new Thread() {
			public void run() {
				while (!exec.isClosed()) {
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

	public void disconnect() {
		if (session != null && session.isConnected()) {
			session.disconnect();
			if (exec != null && exec.isConnected()) {
				exec.disconnect();
			}
		}
	}

	public boolean isConnected() {
		return session.isConnected();
	}
}