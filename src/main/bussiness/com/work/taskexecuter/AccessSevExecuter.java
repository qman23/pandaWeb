package com.work.taskexecuter;

import java.util.Map;

import org.apache.log4j.Logger;

import com.bussiness.exception.BusinessException;
import com.entity.work.Task;
import com.ibm.healthchecktool.bean.ConnBean;
import com.ibm.healthchecktool.util.SSHUtil;
import com.utils.business.PandaConstants;
import com.utils.business.SpringUtil;
import com.utils.business.Utils;
import com.work.bussiness.Executer;

/**
 * 
 * @author Allen
 *
 * Provide the access server function .
 * 
 * access server by the server host name , user name or password.
 * 
 * put the access status into context map.
 */
public class AccessSevExecuter implements Executer{

	private static Logger log=Logger.getLogger(AccessSevExecuter.class.getName());

	/**
	 * implement the executer interface.
	 * 
	 * execute the task and put the access status into context map.
	 */
	public Map execute(Task t, Map context) throws BusinessException {
		SSHUtil sSHUtil=(SSHUtil) SpringUtil.getBean("sSHUtil");
		ConnBean connBean=new ConnBean();
		connBean.setHost(String.valueOf(t.getData().get("hostName")));
		connBean.setPort(String.valueOf(t.getData().get("port")));
		connBean.setName(String.valueOf(t.getData().get("userName")));
		connBean.setPwd(Utils.decrypt(String.valueOf(t.getData().get("passWord"))));
		log.info("Task--Executer:"+AccessSevExecuter.class.getName()+",init connBean complete!");
		try {
			sSHUtil.connect(connBean);
		log.info("Task--Executer:"+AccessSevExecuter.class.getName()+",ssh Connection build complete!");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		Utils.putExecuteResult(t, context, "Login Server :"+String.valueOf(t.getData().get("hostName"))+" success!");
		context.put(t.getTaskId(), PandaConstants.TASK_SUCCESS);
		return context;
	}

}
