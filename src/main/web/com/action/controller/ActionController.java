package com.action.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * parent class for controller 
 * 
 * @author Allen
 */
abstract class ActionController {

	protected Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private HttpServletRequest request;

	protected void setRequestValue(String key, Object value) {
		request.setAttribute(key, value);
	}

	protected Object getRequestValue(String key) {
		return request.getAttribute(key);
	}

	protected void setSeesionValue(String key, Object value) {
		request.getSession().setAttribute(key, value);
	}

	protected Object getSeesionValue(String key) {
		return request.getSession().getAttribute(key);
	}

}
