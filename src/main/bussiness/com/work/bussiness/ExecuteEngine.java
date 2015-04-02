package com.work.bussiness;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.entity.work.ScriptTask;
import com.entity.work.Task;

public class ExecuteEngine implements Callable<Map>{
	
	private List<Task> tasks;
	private ExecuteFactory executer;
	public ExecuteEngine(List<Task> tasks,ExecuteFactory executer){
		this.tasks=tasks;
		this.executer=executer;
	}
	
	public static void execute() throws InterruptedException, ExecutionException{
		 ExecutorService exec = Executors.newFixedThreadPool(10);
	        CompletionService<Map> serv =  
	        new ExecutorCompletionService<Map>(exec);
	            ExecuteFactory factory1=  new ExecuteFactory();
	            ExecuteFactory factory2=  new ExecuteFactory();
	            ScriptTask t1=new ScriptTask();
	            ScriptTask t2=new ScriptTask();
	            ScriptTask t3=new ScriptTask();
	            ScriptTask t4=new ScriptTask();
	            t1.setCatalogId(1);
	            t2.setCatalogId(3);
	            t3.setCatalogId(3);
	            t4.setCatalogId(3);
	            t1.setTaskId(1);
	            t2.setTaskId(2);
	            t3.setTaskId(3);
	            t4.setTaskId(4);
	            List<Task> t1List=new ArrayList<Task>();
	            List<Task> t2List=new ArrayList<Task>();
	            t1List.add(t1);t1List.add(t2);t2List.add(t3);t2List.add(t4);
	            ExecuteEngine execute1=new ExecuteEngine(t1List,factory1);
	            ExecuteEngine execute2=new ExecuteEngine(t2List,factory2);
	            serv.submit(execute1);  
	            serv.submit(execute2);
	            exec.shutdown();  
	}

	public Map call() throws Exception {
		Map result=null;
		for(Task t:tasks){
			System.out.println(t.getTaskId()+"-Task begin execute!");
			result=executer.excuteTask(t);
			System.out.println(result);
		}
		return result;
	}
	
	public static void main(String[] args){
		try {
			execute();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
