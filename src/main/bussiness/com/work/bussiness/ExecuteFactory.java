package com.work.bussiness;

import java.util.HashMap;
import java.util.Map;

import com.entity.work.Task;

public class ExecuteFactory {
	
	private Map context=new HashMap<String,String>();
	private final static String EXECUTER="Executer";
	private final static String PACKAGENAME="com.work.bussiness.";
	
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
			case 1:return "AcessWeb";
			case 2:return "AcessSev";
			case 3:return "Script";
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
