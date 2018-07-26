/**
 * 
 */
package com.prwss.mis.inventory.supplyorder;

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
import com.prwss.mis.inventory.supplyorder.SupplyOrderBOImpl;
import com.prwss.mis.inventory.supplyorder.dao.SupplyOrderDao;
import com.prwss.mis.inventory.supplyorder.SupplyOrderDetailsBean;
import com.prwss.mis.inventory.supplyorder.SupplyOrderHeaderBean;
import com.prwss.mis.inventory.supplyorder.struts.SupplyOrderForm;
import com.prwss.mis.inventory.supplyorder.struts.SupplyOrderGridBean;

/**
 * @author PJHA
 *
 */
public class SupplyOrderBOImpl implements SupplyOrderBO {
	
	private SupplyOrderDao SupplyOrderDao;
	
	Logger log=Logger.getLogger(SupplyOrderBOImpl.class);
	
	
	public void setSupplyOrderDao(SupplyOrderDao SupplyOrderDao) {
		this.SupplyOrderDao = SupplyOrderDao;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean saveSupplyOrderHeaderBeans(
			SupplyOrderForm SupplyOrderForm, MISSessionBean misSessionBean)
			throws MISException {
		
		boolean status=false;
		
				
		Collection<SupplyOrderGridBean> SupplyOrderGridBeans=SupplyOrderForm.getSupplyOrderDatagrid().getAddedData();
		
		/*Generating header id using random function.change and assign here if you want to generate the id using any other means*/
		
		long headerId=SupplyOrderForm.getSupplyOrderHeaderId();
		
		MISAuditBean misAuditBean=getMisAuditBean(misSessionBean,false);
		SupplyOrderForm.setSupplyOrderHeaderId(headerId);
		
		
		try{		
		
			List<SupplyOrderDetailsBean> SupplyOrderDetailsBeans=populateDetailBeans(SupplyOrderGridBeans, misAuditBean, headerId);
			if(MisUtility.ifEmpty(SupplyOrderDetailsBeans)){
				throw new MISException(MISExceptionCodes.MIS012,"Please add Items");
			}
			SupplyOrderHeaderBean SupplyOrderHeaderBean=populateSupplyOrderHeaderBean(SupplyOrderForm, misAuditBean, SupplyOrderDetailsBeans);
			status=SupplyOrderDao.saveSupplyOrderHeaderBeans(SupplyOrderHeaderBean);
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
	public List<SupplyOrderHeaderBean> finSupplyOrderHeaderBeans(
			SupplyOrderForm SupplyOrderForm, List<String> statusList)
			throws MISException {
		
		List<SupplyOrderHeaderBean> SupplyOrderHeaderBeans=null;
		try{
			SupplyOrderHeaderBean SupplyOrderHeaderBean=populateSupplyOrderHeaderBean(SupplyOrderForm, null, null);
			SupplyOrderHeaderBeans=SupplyOrderDao.findSupplyOrderHeaderBeans(SupplyOrderHeaderBean, statusList);
			System.out.println("header bean size in boimpl"+SupplyOrderHeaderBeans.size());
			
		}catch (DataAccessException e) {
			throw new MISException("Failed while retrieving data");
		}
		catch (MISException e) {
			throw e;
		}
	return SupplyOrderHeaderBeans;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean updateSupplyOrderHeaderBeans(
			SupplyOrderForm SupplyOrderForm, MISSessionBean misSessionBean)
			throws MISException {
		
		boolean status=false;		
				
		Collection<SupplyOrderGridBean> SupplyOrderGridBeansModified= SupplyOrderForm.getSupplyOrderDatagrid().getModifiedData();
		Collection<SupplyOrderGridBean> SupplyOrderGridBeansDeleted=SupplyOrderForm.getSupplyOrderDatagrid().getDeletedData();
		List<SupplyOrderDetailsBean> SupplyOrderDetailsBeans=populateDetailBeans(SupplyOrderGridBeansModified,getMisAuditBean(misSessionBean,false),SupplyOrderForm.getSupplyOrderHeaderId());
		if(!MisUtility.ifEmpty(SupplyOrderGridBeansDeleted))
		{
			List<SupplyOrderDetailsBean> SupplyOrderDetailsBeansDeleted=populateDetailBeans(SupplyOrderGridBeansDeleted,getMisAuditBean(misSessionBean,true),SupplyOrderForm.getSupplyOrderHeaderId());
			SupplyOrderDetailsBeans.addAll(SupplyOrderDetailsBeansDeleted);
		}
		SupplyOrderHeaderBean SupplyOrderHeaderBean=populateSupplyOrderHeaderBean(SupplyOrderForm, getMisAuditBean(misSessionBean,false), SupplyOrderDetailsBeans);
		try{
			status=SupplyOrderDao.updateSupplyOrderHeaderBeans(SupplyOrderHeaderBean);
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
	public boolean deleteSupplyOrderHeaderBeans(
			SupplyOrderForm SupplyOrderForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean status=false;
		
		SupplyOrderHeaderBean SupplyOrderHeaderBean=new SupplyOrderHeaderBean();
		SupplyOrderHeaderBean.setSupplyOrderHeaderId(SupplyOrderForm.getSupplyOrderHeaderId());
		List<String> statusList=new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try{
		List<SupplyOrderHeaderBean> SupplyOrderHeaderBeans= SupplyOrderDao.findSupplyOrderHeaderBeans(SupplyOrderHeaderBean, statusList);		
		if(SupplyOrderHeaderBeans.size()==0)
		{
			throw (new MISException(MISExceptionCodes.MIS005, "data not present in the database"));
			
		}
		else if(SupplyOrderHeaderBeans.size()!=1)
		{
			throw new MISException("the query is returning more than one record, so delete is not possible. try to delete single record at a time");
		}
		else if((SupplyOrderHeaderBeans.size()==1) && SupplyOrderHeaderBeans.get(0).getMisAuditBean().getStatus().equalsIgnoreCase(MISConstants.MASTER_STATUS_DELETED))
		{
			throw (new MISException(MISExceptionCodes.MIS006, "data already deleted from the database"));
		}
		else
		{
			MISAuditBean misAuditBean=getMisAuditBean(misSessionBean, true);
			
			SupplyOrderHeaderBeans.get(0).setMisAuditBean(misAuditBean);
			
			for (SupplyOrderDetailsBean SupplyOrderDetailsBean :SupplyOrderHeaderBeans.get(0).getSupplyOrderDetailsBeans()) {
				SupplyOrderDetailsBean.setMisAuditBean(misAuditBean);
			}
			status=SupplyOrderDao.updateSupplyOrderHeaderBeans(SupplyOrderHeaderBeans.get(0));
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
	
	public SupplyOrderHeaderBean populateSupplyOrderHeaderBean(SupplyOrderForm SupplyOrderForm,MISAuditBean misAuditBean,List<SupplyOrderDetailsBean> SupplyOrderDetailsBeans)
	{
		SupplyOrderHeaderBean SupplyOrderHeaderBean=new SupplyOrderHeaderBean();
		
		try{
		SupplyOrderHeaderBean.setSupplyOrderNumber(SupplyOrderForm.getSupplyOrderNumber());
		SupplyOrderHeaderBean.setSupplyOrderDetailsBeans(SupplyOrderDetailsBeans);
		System.out.println("header id in boimpl::: "+SupplyOrderForm.getSupplyOrderHeaderId());
		SupplyOrderHeaderBean.setSupplyOrderHeaderId(SupplyOrderForm.getSupplyOrderHeaderId());
		SupplyOrderHeaderBean.setSupplyOrderDate(MisUtility.convertStringToDate(SupplyOrderForm.getSupplyOrderDate()));
		SupplyOrderHeaderBean.setLocationId(SupplyOrderForm.getLocationId());
		SupplyOrderHeaderBean.setMisAuditBean(misAuditBean);
		SupplyOrderHeaderBean.setProjectId(SupplyOrderForm.getProjectId());
		SupplyOrderHeaderBean.setStoreId(SupplyOrderForm.getStoreId());
		SupplyOrderHeaderBean.setSupplierId(SupplyOrderForm.getSupplierId());	
		}catch(Exception e)
		{
			log.debug("failed during populating headerbean:::: boimpl");
		}
		
		return SupplyOrderHeaderBean;
	}

	public List<SupplyOrderDetailsBean> populateDetailBeans(Collection<SupplyOrderGridBean> SupplyOrderGridBeans,MISAuditBean misAuditBean,long headerId)
	{
		List<SupplyOrderDetailsBean> SupplyOrderDetailsBeans=new ArrayList<SupplyOrderDetailsBean>();
		
		for(SupplyOrderGridBean SupplyOrderGridBean:SupplyOrderGridBeans)
		{
			SupplyOrderDetailsBean SupplyOrderDetailsBean=new SupplyOrderDetailsBean();
			SupplyOrderDetailsBean.setSupplyOrderDetailsId((int)SupplyOrderGridBean.getId());
			SupplyOrderDetailsBean.setItemGroupId(SupplyOrderGridBean.getItemGroupId());
			SupplyOrderDetailsBean.setItemId(SupplyOrderGridBean.getItemId());			
			SupplyOrderDetailsBean.setMisAuditBean(misAuditBean);
			SupplyOrderDetailsBean.setQuantity(SupplyOrderGridBean.getQuantity());
			SupplyOrderDetailsBean.setRate(SupplyOrderGridBean.getItemRate());
			System.out.println("details header Id:::"+headerId);
			SupplyOrderDetailsBean.setSupplyOrderHeaderId(headerId);
			System.out.println("UOM Id:::"+SupplyOrderGridBean.getUnitOfMeasurement());
			SupplyOrderDetailsBean.setUnitOfMeasurementId(SupplyOrderGridBean.getUnitOfMeasurement());
			SupplyOrderDetailsBean.setRemarks(SupplyOrderGridBean.getRemarks());
			SupplyOrderDetailsBeans.add(SupplyOrderDetailsBean);
			
		}
		return SupplyOrderDetailsBeans;
	}


}
