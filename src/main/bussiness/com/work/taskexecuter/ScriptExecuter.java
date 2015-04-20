package com.work.taskexecuter;

import java.util.Map;

import org.apache.log4j.Logger;

import com.entity.work.Task;
import com.work.bussiness.Executer;


public class ScriptExecuter implements Executer{

	private static Logger log=Logger.getLogger(ScriptExecuter.class.getName());
	
	public Map execute(Task t, Map context) {
		log.info(("User Id:"+t.getUserId()+"--Task Id:"+t.getTaskId()+"--Name:"+t.getTaskName()+"--Task Parameter:"+t.getTaskParameter()+"--Running Complate!"));
		context.put(t.getTaskId() ,"Task Id:"+t.getTaskId()+" Name:"+t.getTaskName()+"-Running Complate!");
		return context;
	}

}
