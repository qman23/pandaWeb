package com.work.bussiness;

import java.util.Hashtable;
import java.util.Map;

import com.entity.work.Task;

public class ExecuteFactory {
	
	private Hashtable context=new Hashtable<String,String>();
	private final static String EXECUTER="Executer";
	private final static String PACKAGENAME="com.work.taskexecuter.";
	
	/**
	 * 
	 * @param className
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> T getClassInstance(String className){
		try {
			return (T) Class.forName(PACKAGENAME+className).newInstance();
		} catch (Exception e){
			return null;
		}
	}
	
	private String getCatalog(int catalogId){
		switch(catalogId){
			case 1:return "Script";
			case 4:return "AccessSev";
			case 3:return "Validate";
			case 2:return "AccessWeb";
			default :return "";
		}
	}
	
	/**
	 * 
	 * @param taskCatalog
	 * @return TaskExecuter
	 */
	public Executer getTaskExecuter(int catalogId){
		return getClassInstance(getCatalog(catalogId)+EXECUTER);
	}
	
	
	/**
	 * 
	 * @param tasks
	 */
	public Map excuteTask(Task task){
		return getTaskExecuter(task.getCatalogId()).execute(task, context);
	}
}
