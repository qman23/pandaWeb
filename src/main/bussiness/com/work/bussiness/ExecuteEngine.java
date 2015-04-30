package com.work.bussiness;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.bussiness.exception.BussinessException;
import com.entity.work.Task;
import com.ibm.healthchecktool.util.SSHUtil;
import com.task.bussiness.TaskService;
import com.utils.business.PandaConstants;
import com.utils.business.SpringUtil;
import com.utils.business.Utils;


/**
 * task execte engine 
 * @author Allen
 *
 */
public class ExecuteEngine extends ExecuteFactory implements Callable<Map>{
	
	private static Logger log=Logger.getLogger(ExecuteEngine.class.getName());
	
	private List<Task> tasks;

	private TaskService taskService;
	
	public ExecuteEngine(List<Task> taskList){
		tasks=taskList;
		taskService=(TaskService) SpringUtil.getBean("taskService");
	}
	
	/**
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public void submitExecuteRequest() throws InterruptedException, ExecutionException{
			if(tasks!=null&&tasks.size()!=0){
				log.info("Submit task executer");
			 	ExecutorService exec = Executors.newFixedThreadPool(tasks.size());
		        CompletionService<Map> serv =  
		        new ExecutorCompletionService<Map>(exec);
		            ExecuteEngine executer=new ExecuteEngine(tasks);
		            serv.submit(executer);  
		            exec.shutdown(); 
		       log.info("Complete Submit task executer");
			}
	}

	public Map call()  {
		Map result=null;
		int i=tasks.size();
		int j=0;
		for(Task t:tasks){
				try {
					result=this.excuteTask(t);
					j++;
				} catch (BussinessException e) {
					log.error("Task execute error--task name:"+t.getTaskName()+",task catalog name:"+t.getCatalogName()+",task parameter:"+t.getTaskParameter()+",Exception details:"+e.getMessage());
					taskService.addTaskLog(t, e.getMessage(), PandaConstants.TASK_FAILED);
				} catch (Exception e) {
					log.error("Task execute error--task name:"+t.getTaskName()+",task catalog name:"+t.getCatalogName()+",task parameter:"+t.getTaskParameter()+",Exception details:"+e.getMessage());
					taskService.addTaskLog(t, e.getMessage(), PandaConstants.TASK_FAILED);
				}
		}
		if(i==j){
			StringBuffer sb=new StringBuffer();
			for(Task t:tasks){
				String temp=">>Task execute -- Task Id:"+t.getTaskId()+",Task Name:"+t.getTaskName()+",Task Catalog Name:"+t.getCatalogName()+",Task Parameter:"+t.getTaskParameter();
				sb.append(temp+"\n");
				sb.append(Utils.getExecuteResult(t, result)+"\n\n");
			}
			taskService.addTaskLog(tasks.get(tasks.size()-1), sb.toString(), PandaConstants.TASK_SUCCESS);
		}
		SSHUtil sSHUtil=(SSHUtil) SpringUtil.getBean("sSHUtil");
		try {
			sSHUtil.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void preExecuteTask(Task t,Map context) {
		log.info("Task Id:"+t.getTaskId()+"--Task Name:"+t.getTaskName()+"--Task begin execute!");
	}

	@Override
	public void afterExecuteTask(Task t,Map context) {
		log.info("Task Id:"+t.getTaskId()+"--Task Name:"+t.getTaskName()+"--Running result:"+context);
	}
}
