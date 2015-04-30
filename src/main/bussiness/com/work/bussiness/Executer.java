package com.work.bussiness;

import java.util.Map;

import com.bussiness.exception.BussinessException;
import com.entity.work.Task;

public interface Executer {
	public Map execute(Task t,Map context) throws BussinessException;
}
