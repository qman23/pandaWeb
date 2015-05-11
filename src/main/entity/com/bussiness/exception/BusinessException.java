package com.bussiness.exception;

/**
 * 
 * @author Allen
 * 
 * Panda Web Business Exception.
 */
public class BusinessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException(String msg){
		super(msg);
	}
	
	public BusinessException(Exception e){
		super(e);
	}
}
