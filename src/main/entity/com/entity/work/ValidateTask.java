package com.entity.work;

import java.util.HashMap;

public class ValidateTask extends Task{

	private String relateTaskId;

	public String getRelateTaskId() {
		return relateTaskId;
	}

	public void setRelateTaskId(String relateTaskId) {
		this.relateTaskId = relateTaskId;
	}

	private String regularEp;

	public String getRegularEp() {
		return regularEp;
	}

	public void setRegularEp(String regularEp) {
		this.regularEp = regularEp;
	}

	@Override
	protected void setData() {
		data=new HashMap<String,Object>();
		data.put("taskId", taskId);
		data.put("regularEp", regularEp);
		data.put("catalogId", catalogId);
		data.put("groupId", groupId);
		
	}
}
