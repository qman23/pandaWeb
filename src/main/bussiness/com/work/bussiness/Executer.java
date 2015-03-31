package com.work.bussiness;

import java.util.Map;

import com.entity.work.Task;

public interface Executer {
	public Map execute(Task t,Map context);
}
