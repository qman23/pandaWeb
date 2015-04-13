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

	private String expectResult;
	
	public String getExpectResult() {
		return expectResult;
	}

	public void setExpectResult(String expectResult) {
		this.expectResult = expectResult;
	}

	@Override
	protected void setData() {
		data=new HashMap<String,Object>();
		data.put("script", script);
		data.put("expectResult", expectResult);
	}
}
