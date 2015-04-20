package com.work.bussiness;

import java.util.Hashtable;
import java.util.Map;

import com.entity.work.Task;
import com.utils.business.PandaConstants;

/**
 * Task executeFactory
 * @author Allen
 *
 */
public abstract class ExecuteFactory {
	
	/**
	 * task chian execute context;
	 */
	private Hashtable context=new Hashtable<String,String>();
	
	/**
	 * 
	 * @param className
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> T getClassInstance(String className){
		try {
			return (T) Class.forName(PandaConstants.PACKAGENAME+className).newInstance();
		} catch (Exception e){
			return null;
		}
	}
	
	/**
	 * getTaskCatalog 
	 * @param catalogId
	 * @return
	 */
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
	private Executer getTaskExecuter(int catalogId){
		return getClassInstance(getCatalog(catalogId)+PandaConstants.EXECUTER);
	}
	
	
	/**
	 * 
	 * @param tasks
	 */
	public Map excuteTask(Task task){
		preExecuteTask(context);
		Map result=getTaskExecuter(task.getCatalogId()).execute(task, context);
		afterExecuteTask(context);
		return result;
	}
	
	/**
	 * Before task execute, execute some prepare work
	 */
	public abstract void preExecuteTask(Map context);
	
	/**
	 * After task execute, do some clean up work
	 */
	public abstract void afterExecuteTask(Map context);
}
