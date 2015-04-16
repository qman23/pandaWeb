package com.entity.work;

import java.util.HashMap;

public class ValidateTask extends Task{

	private String validateExpression;


	public String getValidateExpression() {
		return validateExpression;
	}

	public void setValidateExpression(String validateExpression) {
		this.validateExpression = validateExpression;
	}

	private String expectResult;
	
	public String getExpectResult() {
		return expectResult;
	}

	public void setExpectResult(String expectResult) {
		this.expectResult = expectResult;
	}
	
	@Override
	protected void setData() {
		data=new HashMap<String,Object>();
		data.put("validateExpression", validateExpression);
		data.put("expectResult", expectResult);
	}
}
