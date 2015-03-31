package com.work.bussiness;

import java.util.Map;

import com.entity.work.Task;


public class ScriptExecuter implements Executer{

	public Map execute(Task t, Map context) {
		//Map data=t.getData();
		System.out.println(t.getData().get("taskId")+"-Running Complate!");
		context.put(t.getData().get("taskId"), "-Complate!");
		return context;
	}

}
