package com.prwss.mis.common.util.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.village.dao.SchemeCommissionedViewBean;
import com.prwss.mis.masters.village.dao.SchemeCommissionedViewDao;

public class GetSchemeCodeFilterValues extends HttpServlet{
	private static final long serialVersionUID = -1738910969424259354L;

	private Logger log = Logger.getLogger(GetSchemeFilterValues.class);

	private SchemeCommissionedViewDao schemeCommissionedViewDao;

	public void setSchemeCommissionedViewDao(
			SchemeCommissionedViewDao schemeCommissionedViewDao) {
		this.schemeCommissionedViewDao = schemeCommissionedViewDao;
	}

	


	private StringBuffer getPipedSwapMHScheme1(String id)
	{
		System.out.println("getPipedSwapMHScheme1");
		StringBuffer buffer = new StringBuffer();
	
		try {
			
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				SchemeCommissionedViewBean villageSchemeViewBean = new SchemeCommissionedViewBean();
				System.out.println("location---id----->"+id);
				villageSchemeViewBean.setLocationId(id);
				List<SchemeCommissionedViewBean> schemeVillageBeans2 = schemeCommissionedViewDao.findVillageSchemeFromView1(villageSchemeViewBean, statusList);
				//System.out.println("0--------"+schemeVillageBeans2);
				buffer.append("<option value='' selected>");
				//buffer.append("----Select Code----");
				buffer.append("Select Name");
				
				buffer.append("</option>");
				for (SchemeCommissionedViewBean schemeHeaderBean2 : schemeVillageBeans2) {
					if(schemeHeaderBean2.getSchemeupgraded().equals("NO")){
						buffer.append("<option value=\"").append(schemeHeaderBean2.getSchemeId()+"-"+schemeHeaderBean2.getSchemeupgraded()).append("\">");
						buffer.append(schemeHeaderBean2.getSchemeName()+" - ("+schemeHeaderBean2.getSchemeId()+")");
						buffer.append("</option>");
						}else{
							buffer.append("<option value=\"").append(schemeHeaderBean2.getSchemeId()+"-"+schemeHeaderBean2.getSchemeupgraded()).append("\">");
							buffer.append(schemeHeaderBean2.getSchemeName()+" - ("+schemeHeaderBean2.getSchemeId()+") -"+schemeHeaderBean2.getSchemeupgraded() );
							buffer.append("</option>");
						}
				}
			
//			PrintWriter out = resp.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
			}
		}
		
		
		
		catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
		}
		return buffer;
		
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		try {
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext());

			this.schemeCommissionedViewDao =  (SchemeCommissionedViewDao)webApplicationContext.getBean("schemeCommissionedViewDao");

			StringBuffer buffer = new StringBuffer();
		
			
			String type1=req.getParameter("type");

			if(type1.equals("GET_SCHEME"))
			{
				buffer=getPipedSwapMHScheme1((String)req.getParameter("locationId"));

			}	

			
			PrintWriter out = resp.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}
		} catch (BeansException e) {
			log.error(e.getLocalizedMessage(),e);
		} catch(Exception e){
			log.error(e.getLocalizedMessage(),e);
		}

		
		
		
	}
	
}
