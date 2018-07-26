package com.prwss.mis.common.util.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.scheme.dao.SchemeVillageBean;
import com.prwss.mis.masters.scheme.dao.SchemeVillageDao;
import com.prwss.mis.masters.village.dao.VillageSchemeViewBean;
import com.prwss.mis.masters.village.dao.VillageSchemeViewDao;

public class GetSchemeFilterValues extends HttpServlet{
	private static final long serialVersionUID = -1738910969424259354L;
	private SchemeVillageDao schemeVillageDao;
	private LocationDao locationDao;
	private Logger log = Logger.getLogger(GetSchemeFilterValues.class);
	private VillageSchemeViewDao villageSchemeViewDao;
	

	private StringBuffer getBlock(String locationId){
	StringBuffer buffer = new StringBuffer();
	try{
		
		LocationBean locationBean = new LocationBean();
		locationBean.setLocationId(locationId);
		locationBean = locationDao.getLocation(locationBean);
		
		buffer.append("<option value='' selected>");
		buffer.append("Select Block");
		buffer.append("</option>");
		
		Set<LocationBean> blockList = locationDao.getChildLocationIds(locationBean.getParentLocation(), "BLOCK");
		for (LocationBean locationBean2 : blockList) {
			buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
			buffer.append(locationBean2.getLocationName()).append(" - (").append(locationBean2.getLocationId()).append(")");
			buffer.append("</option>");
		}	
	
	} catch (Exception e) {
		log.error(e.getLocalizedMessage(),e);
	}
	return buffer;
	}
	
	private StringBuffer getVillage(String id)
	{
		StringBuffer buffer = new StringBuffer();
		try{
			LocationBean locationBean = new LocationBean();
			locationBean.setLocationId(id);
			locationBean = locationDao.getLocation(locationBean);
			if(locationBean.getLocationType().equalsIgnoreCase("BLOCK")){
				
				buffer.append("<option value='' selected>");
				buffer.append("Select Village");
				buffer.append("</option>");
			List<LocationBean>	villageList = locationDao.getChildLocationListIds(locationBean.getLocationId(), "VILLAGE");
			for (LocationBean locationBean2 : villageList) {
				buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
				buffer.append(locationBean2.getLocationName()).append(" - (").append(locationBean2.getLocationId()).append(")");
				buffer.append("</option>");
			}	
				
			}
		
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
		}
		return buffer;
	}
	
	private StringBuffer getScheme(String id)
	{
		StringBuffer buffer = new StringBuffer();
		try {
			List <SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
//			SchemeVillageBean schemeVillageBean = new SchemeVillageBean();
//			schemeVillageBean.setVillageId(id);
//			schemeVillageBeans = schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList);
			VillageSchemeViewBean villageSchemeViewBean = new VillageSchemeViewBean();
			villageSchemeViewBean.setVillageId(id);
			List<VillageSchemeViewBean> schemeVillageBeans2 = villageSchemeViewDao.findVillageSchemeFromView(villageSchemeViewBean, statusList);
			buffer.append("<option value='' selected>");
			buffer.append("Select");
			buffer.append("</option>");
			if(!MisUtility.ifEmpty(schemeVillageBeans2)){
				for (VillageSchemeViewBean schemeVillageBean2 : schemeVillageBeans2) {
					buffer.append("<option value=\"").append(schemeVillageBean2.getSchemeId()).append("\">");
					buffer.append(schemeVillageBean2.getSchemeName()+" -("+schemeVillageBean2.getSchemeId()+")-"+schemeVillageBean2.getProgramId());
					buffer.append("</option>");
				}
			}
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
		}
		return buffer;
		
	}
	private StringBuffer getPipedSwapMHScheme(String id)
	{
		StringBuffer buffer = new StringBuffer();
		try {
			List <SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			VillageSchemeViewBean villageSchemeViewBean = new VillageSchemeViewBean();
			villageSchemeViewBean.setVillageId(id);
			List<VillageSchemeViewBean> schemeVillageBeans2 = villageSchemeViewDao.findVillageSchemeFromView(villageSchemeViewBean, statusList);
			if(!MisUtility.ifEmpty(schemeVillageBeans2)){
				for (VillageSchemeViewBean schemeVillageBean2 : schemeVillageBeans2) {
					if((schemeVillageBean2.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_CANNAL)||
					schemeVillageBean2.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_TUBE_WELL)||
					schemeVillageBean2.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_TUBE_WELL_WITH_RO)||
					schemeVillageBean2.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_PERCULATION_WELL)||
					schemeVillageBean2.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_LIFTING_WATER_RSD_LAKE)||
					schemeVillageBean2.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_ROOF_TOP)
					||schemeVillageBean2.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_IMPROVEMENT)||
					schemeVillageBean2.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_DISTRIBUTION)
					) && (
					schemeVillageBean2.getProgramId().equals("PROG06")||
					schemeVillageBean2.getProgramId().equals("PROG11")||
					schemeVillageBean2.getProgramId().equals("PROG15")||
					schemeVillageBean2.getProgramId().equals("PROG30")||
					schemeVillageBean2.getProgramId().equals("PROG20")
					)){
						buffer.append("<option value=\"").append(schemeVillageBean2.getSchemeId()).append("\">");
						buffer.append(schemeVillageBean2.getSchemeName()+" -("+schemeVillageBean2.getSchemeId()+")-"+schemeVillageBean2.getProgramId());
						buffer.append("</option>");
					}
					
				}
			}
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
		}
		return buffer;
		
	}
	private StringBuffer getSubPlanScheme(String id)
	{
		StringBuffer buffer = new StringBuffer();
		try {
			List <SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
//			SchemeVillageBean schemeVillageBean = new SchemeVillageBean();
//			schemeVillageBean.setVillageId(id);
//			schemeVillageBeans = schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList);
			VillageSchemeViewBean villageSchemeViewBean = new VillageSchemeViewBean();
			villageSchemeViewBean.setVillageId(id);
			List<VillageSchemeViewBean> schemeVillageBeans2 = villageSchemeViewDao.findVillageSchemeFromView(villageSchemeViewBean, statusList);
			if(!MisUtility.ifEmpty(schemeVillageBeans2)){
				buffer.append("<option value=\"\"></option>");
				/*buffer.append("<option value=\"GOODS\">GOODS</option>");
				buffer.append("<option value=\"SERVICES\">SERVICES</option>");
				buffer.append("<option value=\"CONSULTANCY\">CONSULTANCY</option>");*/
				for (VillageSchemeViewBean schemeVillageBean2 : schemeVillageBeans2) {
					buffer.append("<option value=\"").append(schemeVillageBean2.getSchemeId()).append("\">");
					buffer.append(schemeVillageBean2.getSchemeName()+" -("+schemeVillageBean2.getSchemeId()+")-"+schemeVillageBean2.getProgramId());
					buffer.append("</option>");
				}
			}
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
		}
		return buffer;
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		try {
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext());
			this.locationDao = (LocationDao)webApplicationContext.getBean("locationDao");
			this.schemeVillageDao = (SchemeVillageDao)webApplicationContext.getBean("schemeVillageDao");
			this.villageSchemeViewDao =  (VillageSchemeViewDao)webApplicationContext.getBean("villageSchemeViewDao");
			StringBuffer buffer = new StringBuffer();
			if(MisUtility.ifEmpty(req.getParameter("locationId"))){
				buffer = getBlock((String)req.getParameter("locationId"));
				
			}
			
			if(MisUtility.ifEmpty(req.getParameter("blockId"))){
				buffer = getVillage((String)req.getParameter("blockId"));
				
			}
			
			if(MisUtility.ifEmpty(req.getParameter("villageId"))){
				if(MisUtility.ifEmpty(req.getParameter("subPlanId"))){
					buffer = getSubPlanScheme((String)req.getParameter("villageId"));
				}else if(MisUtility.ifEmpty(req.getParameter("type"))){
					String type=req.getParameter("type");
					if(type.equals("PIPED_SWAP_MH")){
						buffer=getPipedSwapMHScheme((String)req.getParameter("villageId"));
					}
				}else{
					buffer = getScheme((String)req.getParameter("villageId"));
				}				
			}
			
			PrintWriter out = resp.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				//out.print(buffer.substring(0, buffer.length() - 1));
				out.print(buffer.toString());
			}
		} catch (BeansException e) {
			log.error(e.getLocalizedMessage(),e);
		} catch(Exception e){
			log.error(e.getLocalizedMessage(),e);
		}

		
		
		
	}
	
}
