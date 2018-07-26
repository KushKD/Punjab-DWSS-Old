package com.prwss.mis.common.exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

public class MISExceptionHandler extends ExceptionHandler {
	
	private static Logger log = Logger.getLogger(MISExceptionHandler.class);
	
	@Override
	public ActionForward execute(Exception exception, ExceptionConfig config,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		logException(exception.getCause());
		ActionForward actionForward = new ActionForward(config.getPath());
		return actionForward;
	}
	
	/**
	 * Method to log errors and exceptions
	 * @param ex
	 */
	public static void logException(Throwable ex)
	{
		if(ex != null)
		{
			log.error(ex.getMessage(), ex);
		}
	}

}
