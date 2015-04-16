package com.work.taskexecuter;

import java.util.Map;

import org.apache.log4j.Logger;

import com.entity.work.Task;
import com.work.bussiness.Executer;

public class AccessSevExecuter implements Executer{

	private static Logger log=Logger.getLogger(AccessSevExecuter.class.getName());
	
	public Map execute(Task t, Map context) {
		log.info(("Task Id:"+t.getTaskId()+"--Name:"+t.getTaskName()+"--Running Complate!"));
		context.put(t.getTaskId() ,"Task Id:"+t.getTaskId()+" Name:"+t.getTaskName()+"-Running Complate!");
		return context;
	}

}
