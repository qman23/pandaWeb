package com.work.bussiness;

import java.util.Map;

import com.bussiness.exception.BusinessException;
import com.entity.work.Task;

/**
 * 
 * @author Allen
 * 
 * The execute interface 
 * 
 * Throw the BussinessException to up level.
 */
public interface Executer {
	public Map execute(Task t,Map context) throws BusinessException;
}
