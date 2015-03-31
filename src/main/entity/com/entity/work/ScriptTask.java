package com.entity.work;

import java.util.HashMap;
import java.util.Map;

/**
 * script task 
 * @author Allen
 *
 */
public class ScriptTask extends Task {
	private String script;


	public String getScript() {
		return script;
	}
	
	public void setScript(String script) {
		this.script = script;
	}


	@Override
	protected void setData() {
		data=new HashMap<String,Object>();
		data.put("taskId", taskId);
		data.put("script", script);
		data.put("catalogId", catalogId);
		data.put("groupId", groupId);
	}
}
