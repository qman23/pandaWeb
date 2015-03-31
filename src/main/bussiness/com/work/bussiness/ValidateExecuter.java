package com.work.bussiness;

import java.util.Map;

import com.entity.work.Task;

public class ValidateExecuter implements Executer{

	public Map execute(Task t, Map context) {
		
		int relateTaskId=Integer.parseInt((String) t.getData().get("relateTaskId"));
		String souce=(String) context.get("relateTaskId");
		//validate Source by the t.getData().get("regularEp")

		context.put(t.getTaskId(), "validateResult");
		return context;
	}

}
