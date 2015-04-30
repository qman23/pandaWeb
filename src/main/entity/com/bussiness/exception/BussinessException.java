package com.bussiness.exception;

public class BussinessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BussinessException(String msg){
		super(msg);
	}
	
	public BussinessException(Exception e){
		super(e);
	}
}
