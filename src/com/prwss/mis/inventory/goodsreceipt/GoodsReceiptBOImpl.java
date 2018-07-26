/**
 * 
 */
package com.prwss.mis.inventory.goodsreceipt;

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
import com.prwss.mis.inventory.goodsreceipt.dao.GoodsReceiptDao;
import com.prwss.mis.inventory.goodsreceipt.dao.GoodsReceiptDetailsBean;
import com.prwss.mis.inventory.goodsreceipt.dao.GoodsReceiptHeaderBean;
import com.prwss.mis.inventory.goodsreceipt.struts.GoodsReceiptForm;
import com.prwss.mis.inventory.goodsreceipt.struts.GoodsReceiptGridBean;

/**
 * @author PJHA
 *
 */

public class GoodsReceiptBOImpl implements GoodsReceiptBO {
	
	private GoodsReceiptDao goodsReceiptDao;
	
	Logger log=Logger.getLogger(GoodsReceiptBOImpl.class);
	
	
	public void setGoodsReceiptDao(GoodsReceiptDao goodsReceiptDao) {
		this.goodsReceiptDao = goodsReceiptDao;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean saveGoodsReceiptHeaderBeans(
			GoodsReceiptForm goodsReceiptForm, MISSessionBean misSessionBean)
			throws MISException {
		
		boolean status=false;
		
				
		Collection<GoodsReceiptGridBean> goodsReceiptGridBeans=goodsReceiptForm.getGoodsReceiptDatagrid().getAddedData();
		if(MisUtility.ifEmpty(goodsReceiptGridBeans)){
			throw new MISException(MISExceptionCodes.MIS012,"Please add Items");
		}
		/*Generating header id using random function.change and assign here if you want to generate the id using any other means*/
		
		long headerId=goodsReceiptForm.getGoodsReceiptHeaderId();
		
		MISAuditBean misAuditBean=getMisAuditBean(misSessionBean,false);
		goodsReceiptForm.setGoodsReceiptHeaderId(headerId);
		
		
		try{		
		
			List<GoodsReceiptDetailsBean> goodsReceiptDetailsBeans=populateDetailBeans(goodsReceiptGridBeans, misAuditBean, headerId);
			GoodsReceiptHeaderBean goodsReceiptHeaderBean=populateGoodsReceiptHeaderBean(goodsReceiptForm, misAuditBean, goodsReceiptDetailsBeans);
			status=goodsReceiptDao.saveGoodsReceiptHeaderBeans(goodsReceiptHeaderBean);
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
	public List<GoodsReceiptHeaderBean> finGoodsReceiptHeaderBeans(
			GoodsReceiptForm goodsReceiptForm, List<String> statusList)
			throws MISException {
		
		List<GoodsReceiptHeaderBean> goodsReceiptHeaderBeans=null;
		try{
			GoodsReceiptHeaderBean goodsReceiptHeaderBean=populateGoodsReceiptHeaderBean(goodsReceiptForm, null, null);
			goodsReceiptHeaderBeans=goodsReceiptDao.findGoodsReceiptHeaderBeans(goodsReceiptHeaderBean, statusList);
			System.out.println("header bean size in boimpl"+goodsReceiptHeaderBeans.size());
			
		}catch (DataAccessException e) {
			throw new MISException("Failed while retrieving data");
		}
		catch (MISException e) {
			throw e;
		}
	return goodsReceiptHeaderBeans;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean updateGoodsReceiptHeaderBeans(
			GoodsReceiptForm goodsReceiptForm, MISSessionBean misSessionBean)
			throws MISException {
		
		boolean status=false;		
				
		Collection<GoodsReceiptGridBean> goodsReceiptGridBeansModified= goodsReceiptForm.getGoodsReceiptDatagrid().getModifiedData();
		Collection<GoodsReceiptGridBean> goodsReceiptGridBeansDeleted=goodsReceiptForm.getGoodsReceiptDatagrid().getDeletedData();
		List<GoodsReceiptDetailsBean> goodsReceiptDetailsBeans=populateDetailBeans(goodsReceiptGridBeansModified,getMisAuditBean(misSessionBean,false),goodsReceiptForm.getGoodsReceiptHeaderId());
		if(!MisUtility.ifEmpty(goodsReceiptGridBeansDeleted));
		{
			List<GoodsReceiptDetailsBean> goodsReceiptDetailsBeansDeleted=populateDetailBeans(goodsReceiptGridBeansDeleted,getMisAuditBean(misSessionBean,true),goodsReceiptForm.getGoodsReceiptHeaderId());
			goodsReceiptDetailsBeans.addAll(goodsReceiptDetailsBeansDeleted);
		}
		GoodsReceiptHeaderBean goodsReceiptHeaderBean=populateGoodsReceiptHeaderBean(goodsReceiptForm, getMisAuditBean(misSessionBean,false), goodsReceiptDetailsBeans);
		try{
			status=goodsReceiptDao.updateGoodsReceiptHeaderBeans(goodsReceiptHeaderBean);
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
	public boolean deleteGoodsReceiptHeaderBeans(
			GoodsReceiptForm goodsReceiptForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean status=false;
		
		GoodsReceiptHeaderBean goodsReceiptHeaderBean=new GoodsReceiptHeaderBean();
		goodsReceiptHeaderBean.setGoodsReceiptHeaderId(goodsReceiptForm.getGoodsReceiptHeaderId());
		List<String> statusList=new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try{
		List<GoodsReceiptHeaderBean> goodsReceiptHeaderBeans= goodsReceiptDao.findGoodsReceiptHeaderBeans(goodsReceiptHeaderBean, statusList);		
		if(goodsReceiptHeaderBeans.size()==0)
		{
			throw (new MISException(MISExceptionCodes.MIS005, "data not present in the database"));
			
		}
		else if(goodsReceiptHeaderBeans.size()!=1)
		{
			throw new MISException("the query is returning more than one record, so delete is not possible. try to delete single record at a time");
		}
		else if((goodsReceiptHeaderBeans.size()==1) && goodsReceiptHeaderBeans.get(0).getMisAuditBean().getStatus().equalsIgnoreCase(MISConstants.MASTER_STATUS_DELETED))
		{
			throw (new MISException(MISExceptionCodes.MIS006, "data already deleted from the database"));
		}
		else
		{
			MISAuditBean misAuditBean=getMisAuditBean(misSessionBean, true);
			
			goodsReceiptHeaderBeans.get(0).setMisAuditBean(misAuditBean);
			
			for (GoodsReceiptDetailsBean goodsReceiptDetailsBean :goodsReceiptHeaderBeans.get(0).getGoodsReceiptDetailsBeans()) {
				goodsReceiptDetailsBean.setMisAuditBean(misAuditBean);
			}
			status=goodsReceiptDao.updateGoodsReceiptHeaderBeans(goodsReceiptHeaderBeans.get(0));
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
	
	public GoodsReceiptHeaderBean populateGoodsReceiptHeaderBean(GoodsReceiptForm goodsReceiptForm,MISAuditBean misAuditBean,List<GoodsReceiptDetailsBean> goodsReceiptDetailsBeans)
	{
		GoodsReceiptHeaderBean goodsReceiptHeaderBean=new GoodsReceiptHeaderBean();
		
		try{
		goodsReceiptHeaderBean.setContractNumber(goodsReceiptForm.getContractId());
		goodsReceiptHeaderBean.setGoodsReceiptDetailsBeans(goodsReceiptDetailsBeans);
		System.out.println("header id in boimpl::: "+goodsReceiptForm.getGoodsReceiptHeaderId());
		goodsReceiptHeaderBean.setGoodsReceiptHeaderId(goodsReceiptForm.getGoodsReceiptHeaderId());
		goodsReceiptHeaderBean.setInvoiceDate(MisUtility.convertStringToDate(goodsReceiptForm.getInvoiceDate()));
		goodsReceiptHeaderBean.setInvoiceNumber(goodsReceiptForm.getInvoiceNumber());
		goodsReceiptHeaderBean.setLocationId(goodsReceiptForm.getLocationId());
		goodsReceiptHeaderBean.setMisAuditBean(misAuditBean);
		goodsReceiptHeaderBean.setProjectId(goodsReceiptForm.getProjectId());
		goodsReceiptHeaderBean.setReceiptAmount(goodsReceiptForm.getInvoiceAmount());
		goodsReceiptHeaderBean.setReceivedDate(MisUtility.convertStringToDate(goodsReceiptForm.getReceivedDate()));
		goodsReceiptHeaderBean.setRemarks(goodsReceiptForm.getRemarks());
		goodsReceiptHeaderBean.setStoreId(goodsReceiptForm.getStoreId());
		goodsReceiptHeaderBean.setSupplierId(goodsReceiptForm.getSupplierId());	
		}catch(Exception e)
		{
			log.debug("failed during populating headerbean:::: boimpl");
		}
		
		return goodsReceiptHeaderBean;
	}

	public List<GoodsReceiptDetailsBean> populateDetailBeans(Collection<GoodsReceiptGridBean> goodsReceiptGridBeans,MISAuditBean misAuditBean,long headerId)
	{
		List<GoodsReceiptDetailsBean> goodsReceiptDetailsBeans=new ArrayList<GoodsReceiptDetailsBean>();
		
		for(GoodsReceiptGridBean goodsReceiptGridBean:goodsReceiptGridBeans)
		{
			GoodsReceiptDetailsBean goodsReceiptDetailsBean=new GoodsReceiptDetailsBean();
			goodsReceiptDetailsBean.setGoodsReceiptDetailsId((int)goodsReceiptGridBean.getId());
			goodsReceiptDetailsBean.setAmount(goodsReceiptGridBean.getItemAmount());
			goodsReceiptDetailsBean.setItemGroupId(goodsReceiptGridBean.getItemGroupId());
			goodsReceiptDetailsBean.setItemId(goodsReceiptGridBean.getItemId());			
			goodsReceiptDetailsBean.setMisAuditBean(misAuditBean);
			goodsReceiptDetailsBean.setQuantity(goodsReceiptGridBean.getQuantity());
			goodsReceiptDetailsBean.setRate(goodsReceiptGridBean.getItemRate());
			System.out.println("details header Id:::"+headerId);
			goodsReceiptDetailsBean.setGoodsReceiptDetailsHeaderId(headerId);
			goodsReceiptDetailsBeans.add(goodsReceiptDetailsBean);
			
		}
		return goodsReceiptDetailsBeans;
	}

	
	

	

}
