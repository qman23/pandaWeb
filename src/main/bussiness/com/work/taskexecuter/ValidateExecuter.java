package com.work.taskexecuter;

import java.util.Map;

import org.apache.log4j.Logger;

import com.entity.work.Task;
import com.work.bussiness.Executer;


/**
 * 
 * @author Allen
 *
 * validate the relative task result by the regular expressions.
 * 
 * 
 */
public class ValidateExecuter implements Executer{

	private static Logger log=Logger.getLogger(ValidateExecuter.class.getName());
	
	
	public Map execute(Task t, Map context) {
		log.info(("User Id:"+t.getUserId()+"--Task Id:"+t.getTaskId()+"--Name:"+t.getTaskName()+"--Task Parameter:"+t.getTaskParameter()+"--Running Complate!"));
		context.put(t.getTaskId() ,"Task Id:"+t.getTaskId()+" Name:"+t.getTaskName()+"-Running Complate!");
		return context;
	}

}
