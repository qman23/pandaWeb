package com.work.bussiness;


import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.entity.work.Task;


public class ExecuteEngine implements Callable<Map>{
	
	private static Logger log=Logger.getLogger(ExecuteEngine.class.getName());
	
	private List<Task> tasks;
	private ExecuteFactory executer;
	public ExecuteEngine(List<Task> tasks){
		this.tasks=tasks;
		executer=  new ExecuteFactory();
	}
	
	public  void execute() throws InterruptedException, ExecutionException{
		
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

	public Map call() throws Exception {
		Map result=null;
		for(Task t:tasks){
			log.info("Task Id:"+t.getTaskId()+"--Task Name:"+t.getTaskName()+"--Task begin execute!");
			result=executer.excuteTask(t);
			log.info("Task Id:"+t.getTaskId()+"--Task Name:"+t.getTaskName()+"--Running result:"+result);
		}
		return result;
	}
}
