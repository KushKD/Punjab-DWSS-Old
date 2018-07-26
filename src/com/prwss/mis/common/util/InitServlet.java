package com.prwss.mis.common.util;

import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.prwss.mis.masters.component.dao.ComponentBean;
import com.prwss.mis.masters.component.dao.ComponentDao;

public class InitServlet extends HttpServlet {
	
	private static Set<ComponentBean> componentCodes;
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 9064346969140104230L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ComponentDao componentDao = (ComponentDao)WebApplicationContextUtils.getWebApplicationContext(config.getServletContext()).getBean("componentDao");
		componentCodes = componentDao.getDistinctComponentCodes();
		config.getServletContext().setAttribute("componentCodes", componentCodes);
	}
	

	public static Set<ComponentBean> getComponentCodes() {
		
		return componentCodes;
	}
	
	

}
