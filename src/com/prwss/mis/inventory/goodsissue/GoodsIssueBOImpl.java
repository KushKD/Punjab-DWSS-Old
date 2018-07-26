/**
 * 
 */
package com.prwss.mis.inventory.goodsissue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.inventory.goodsissue.GoodsIssueBOImpl;
import com.prwss.mis.inventory.goodsissue.GoodsIssueDetailsBean;
import com.prwss.mis.inventory.goodsissue.GoodsIssueHeaderBean;
import com.prwss.mis.inventory.goodsissue.dao.GoodsIssueDao;
import com.prwss.mis.inventory.goodsissue.struts.GoodsIssueForm;
import com.prwss.mis.inventory.goodsissue.struts.GoodsIssueGridBean;

/**
 * @author PJHA
 *
 */
public class GoodsIssueBOImpl implements GoodsIssueBO {
	

	
	private GoodsIssueDao GoodsIssueDao;
	
	Logger log=Logger.getLogger(GoodsIssueBOImpl.class);
	
	
	public void setGoodsIssueDao(GoodsIssueDao GoodsIssueDao) {
		this.GoodsIssueDao = GoodsIssueDao;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean saveGoodsIssueHeaderBeans(
			GoodsIssueForm GoodsIssueForm, MISSessionBean misSessionBean)
			throws MISException {
		
		boolean status=false;
		
				
		Collection<GoodsIssueGridBean> GoodsIssueGridBeans=GoodsIssueForm.getGoodsIssueDatagrid().getAddedData();
		if(MisUtility.ifEmpty(GoodsIssueGridBeans)){
			throw new MISException(MISExceptionCodes.MIS012,"Please add Items");
		}
		/*Generating header id using random function.change and assign here if you want to generate the id using any other means*/
		
		long headerId=GoodsIssueForm.getGoodsIssueHeaderId();
		
		MISAuditBean misAuditBean=getMisAuditBean(misSessionBean,false);
		GoodsIssueForm.setGoodsIssueHeaderId(headerId);
		
		
		try{		
		
			List<GoodsIssueDetailsBean> GoodsIssueDetailsBeans=populateDetailBeans(GoodsIssueGridBeans, misAuditBean, headerId);
			GoodsIssueHeaderBean GoodsIssueHeaderBean=populateGoodsIssueHeaderBean(GoodsIssueForm, misAuditBean, GoodsIssueDetailsBeans);
			status=GoodsIssueDao.saveGoodsIssueHeaderBeans(GoodsIssueHeaderBean);
		if(!status)
		{
			throw new MISException("saving failed boImpl");
		}
		return status;
		}catch (MISException e) {
			throw e;
		}		
		
	}
	
	@Override
	public List<GoodsIssueHeaderBean> finGoodsIssueHeaderBeans(
			GoodsIssueForm GoodsIssueForm, List<String> statusList)
			throws MISException {
		
		List<GoodsIssueHeaderBean> GoodsIssueHeaderBeans=null;
		try{
			GoodsIssueHeaderBean GoodsIssueHeaderBean=populateGoodsIssueHeaderBean(GoodsIssueForm, null, null);
			GoodsIssueHeaderBeans=GoodsIssueDao.findGoodsIssueHeaderBeans(GoodsIssueHeaderBean, statusList);
			System.out.println("header bean size in boimpl"+GoodsIssueHeaderBeans.size());
			
		}catch (DataAccessException e) {
			throw new MISException("Failed while retrieving data");
		}
		catch (MISException e) {
			throw e;
		}
	return GoodsIssueHeaderBeans;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean updateGoodsIssueHeaderBeans(
			GoodsIssueForm GoodsIssueForm, MISSessionBean misSessionBean)
			throws MISException {
		
		boolean status=false;		
				
		Collection<GoodsIssueGridBean> GoodsIssueGridBeansModified= GoodsIssueForm.getGoodsIssueDatagrid().getModifiedData();
		Collection<GoodsIssueGridBean> GoodsIssueGridBeansDeleted=GoodsIssueForm.getGoodsIssueDatagrid().getDeletedData();
		List<GoodsIssueDetailsBean> GoodsIssueDetailsBeans=populateDetailBeans(GoodsIssueGridBeansModified,getMisAuditBean(misSessionBean,false),GoodsIssueForm.getGoodsIssueHeaderId());
		if(!MisUtility.ifEmpty(GoodsIssueGridBeansDeleted))
		{
			List<GoodsIssueDetailsBean> GoodsIssueDetailsBeansDeleted=populateDetailBeans(GoodsIssueGridBeansDeleted,getMisAuditBean(misSessionBean,true),GoodsIssueForm.getGoodsIssueHeaderId());
			GoodsIssueDetailsBeans.addAll(GoodsIssueDetailsBeansDeleted);
		}
		GoodsIssueHeaderBean GoodsIssueHeaderBean=populateGoodsIssueHeaderBean(GoodsIssueForm, getMisAuditBean(misSessionBean,false), GoodsIssueDetailsBeans);
		try{
			status=GoodsIssueDao.updateGoodsIssueHeaderBeans(GoodsIssueHeaderBean);
			System.out.println("boimpl status:::: "+status);
		if(!status)
		{
			throw new MISException("saving failed boImpl");
		}
		}catch (MISException e) {
			throw e;
		}	
		return status;
		
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public boolean deleteGoodsIssueHeaderBeans(
			GoodsIssueForm GoodsIssueForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean status=false;
		
		GoodsIssueHeaderBean GoodsIssueHeaderBean=new GoodsIssueHeaderBean();
		GoodsIssueHeaderBean.setGoodsIssueHeaderId(GoodsIssueForm.getGoodsIssueHeaderId());
		List<String> statusList=new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try{
		List<GoodsIssueHeaderBean> GoodsIssueHeaderBeans= GoodsIssueDao.findGoodsIssueHeaderBeans(GoodsIssueHeaderBean, statusList);		
		if(GoodsIssueHeaderBeans.size()==0)
		{
			throw (new MISException(MISExceptionCodes.MIS005, "data not present in the database"));
			
		}
		else if(GoodsIssueHeaderBeans.size()!=1)
		{
			throw new MISException("the query is returning more than one record, so delete is not possible. try to delete single record at a time");
		}
		else if((GoodsIssueHeaderBeans.size()==1) && GoodsIssueHeaderBeans.get(0).getMisAuditBean().getStatus().equalsIgnoreCase(MISConstants.MASTER_STATUS_DELETED))
		{
			throw (new MISException(MISExceptionCodes.MIS006, "data already deleted from the database"));
		}
		else
		{
			MISAuditBean misAuditBean=getMisAuditBean(misSessionBean, true);
			
			GoodsIssueHeaderBeans.get(0).setMisAuditBean(misAuditBean);
			
			for (GoodsIssueDetailsBean GoodsIssueDetailsBean :GoodsIssueHeaderBeans.get(0).getGoodsIssueDetailsBeans()) {
				GoodsIssueDetailsBean.setMisAuditBean(misAuditBean);
			}
			status=GoodsIssueDao.updateGoodsIssueHeaderBeans(GoodsIssueHeaderBeans.get(0));
		}
		}catch (MISException e) {
			throw e;
		}
		return status;
	}
	
	public MISAuditBean getMisAuditBean(MISSessionBean misSessionBean,boolean isDeleted)
	{
		MISAuditBean misAuditBean=new MISAuditBean();
		
		misAuditBean.setEntBy(misSessionBean.getEnteredBy());
		misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		if(isDeleted)
		{
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
		}else{
		misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
		}
		
		return misAuditBean;
		
	}
	
	public GoodsIssueHeaderBean populateGoodsIssueHeaderBean(GoodsIssueForm GoodsIssueForm,MISAuditBean misAuditBean,List<GoodsIssueDetailsBean> GoodsIssueDetailsBeans)
	{
		GoodsIssueHeaderBean GoodsIssueHeaderBean=new GoodsIssueHeaderBean();
		
		try{
		GoodsIssueHeaderBean.setIndentNumber(GoodsIssueForm.getIndentNumber());
		GoodsIssueHeaderBean.setGoodsIssueDetailsBeans(GoodsIssueDetailsBeans);
		System.out.println("header id in boimpl::: "+GoodsIssueForm.getGoodsIssueHeaderId());
		GoodsIssueHeaderBean.setGoodsIssueHeaderId(GoodsIssueForm.getGoodsIssueHeaderId());
		GoodsIssueHeaderBean.setIndentDate((MisUtility.convertStringToDate(GoodsIssueForm.getIndentDate())));
		GoodsIssueHeaderBean.setLocationId(GoodsIssueForm.getLocationId());
		GoodsIssueHeaderBean.setMisAuditBean(misAuditBean);
		GoodsIssueHeaderBean.setProjectId(GoodsIssueForm.getProjectId());
		GoodsIssueHeaderBean.setStoreId(GoodsIssueForm.getStoreId());
		GoodsIssueHeaderBean.setIssuedTo(GoodsIssueForm.getIssuedTo());
		GoodsIssueHeaderBean.setIssuedType(GoodsIssueForm.getIssuedType());
		}catch(Exception e)
		{
			log.debug("failed during populating headerbean:::: boimpl");
		}
		
		return GoodsIssueHeaderBean;
	}

	public List<GoodsIssueDetailsBean> populateDetailBeans(Collection<GoodsIssueGridBean> GoodsIssueGridBeans,MISAuditBean misAuditBean,long headerId)
	{
		List<GoodsIssueDetailsBean> GoodsIssueDetailsBeans=new ArrayList<GoodsIssueDetailsBean>();
		
		for(GoodsIssueGridBean GoodsIssueGridBean:GoodsIssueGridBeans)
		{
			GoodsIssueDetailsBean GoodsIssueDetailsBean=new GoodsIssueDetailsBean();
			GoodsIssueDetailsBean.setGoodsIssueDetailsId((int)GoodsIssueGridBean.getId());
			GoodsIssueDetailsBean.setItemGroupId(GoodsIssueGridBean.getItemGroupId());
			GoodsIssueDetailsBean.setItemId(GoodsIssueGridBean.getItemId());			
			GoodsIssueDetailsBean.setMisAuditBean(misAuditBean);
			GoodsIssueDetailsBean.setQuantity(GoodsIssueGridBean.getQuantity());
			GoodsIssueDetailsBean.setRate(GoodsIssueGridBean.getItemRate());
			System.out.println("details header Id:::"+headerId);
			GoodsIssueDetailsBean.setGoodsIssueHeaderId(headerId);
			GoodsIssueDetailsBean.setAmount(GoodsIssueGridBean.getItemAmount());
			GoodsIssueDetailsBeans.add(GoodsIssueDetailsBean);
			
		}
		return GoodsIssueDetailsBeans;
	}




}
