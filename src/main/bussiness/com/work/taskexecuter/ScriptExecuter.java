package com.work.taskexecuter;

import java.util.Map;

import org.apache.log4j.Logger;

import com.bussiness.exception.BusinessException;
import com.entity.work.Task;
import com.ibm.healthchecktool.util.SSHUtil;
import com.utils.business.PandaConstants;
import com.utils.business.SpringUtil;
import com.utils.business.Utils;
import com.work.bussiness.Executer;

/**
 * 
 * @author Allen
 *
 * Provide the script execute function for linux or unix .
 * 
 * Check the relative task execute status and put the execute result into context.
 */
public class ScriptExecuter implements Executer{

	private static Logger log=Logger.getLogger(ScriptExecuter.class.getName());
	
	/**
	 * implement the executer interface 
	 * 
	 * Execute the task and put the execute result into context map.
	 */
	public Map execute(Task t, Map context) throws BusinessException {
		SSHUtil sSHUtil=(SSHUtil) SpringUtil.getBean("sSHUtil");
		log.info("Task--Executer:"+ScriptExecuter.class.getName()+" check relative task status...");
		if(Integer.parseInt(String.valueOf(context.get(t.getRelativeId())))==PandaConstants.TASK_SUCCESS){
			try {
				log.info("Task--Executer:"+ScriptExecuter.class.getName()+" check relative task status..Complete");
				String result=sSHUtil.shell(t.getData().get("script").toString());
				Utils.putExecuteResult(t, context, Utils.isStringNull(result)?"":result);
				context.put(t.getTaskId(), PandaConstants.TASK_SUCCESS);
				log.info("Task--Executer:"+ScriptExecuter.class.getName()+",script:"+t.getData().get("script").toString()+" execute success.");
			} catch (Exception e) {
				throw new BusinessException(e);
			}
		}else{
			log.error("Task--Executer:"+ScriptExecuter.class.getName()+" check relative task status..Failed");
			throw new BusinessException("Current Task id:"+t.getTaskId()+",Task Name:"+t.getCatalogName()+" relative task:"+t.getRelativeId()+" not excute success!");
		}
		return context;
	}

}
