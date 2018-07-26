package com.prwss.mis.daktask.inwarddak;

import java.sql.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.daktask.inwarddak.dao.InwardDakBean;
import com.prwss.mis.daktask.inwarddak.dao.InwardDakDao;
import com.prwss.mis.daktask.inwarddak.struts.InwardDakForm;
import com.prwss.mis.sd.document.dao.DocumentNumberBean;
import com.prwss.mis.sd.document.dao.DocumentNumberDAO;

public class InwardDakBOImpl implements InwardDakBO {
	private InwardDakDao inwardDakDao;
	private DocumentNumberDAO documentNumberDAO;
	
	
	public void setInwardDakDao(InwardDakDao inwardDakDao)
	{
		this.inwardDakDao=inwardDakDao;
	}
	public void setDocumentNumberDao(DocumentNumberDAO documentNumberDAO)
	{
		this.documentNumberDAO=documentNumberDAO;
	}
	
	private Logger log=Logger.getLogger(InwardDakBOImpl.class); 

	@Override
	public List<InwardDakBean> findInwardDak(InwardDakForm inwardDakForm,
			List<String> statusList) throws MISException {
		
		List<InwardDakBean> inwardDakFindBean=null;
		try {			
			InwardDakBean inwardDakBean=new InwardDakBean();
			if(inwardDakForm.getDocumentNo()!=""&&inwardDakForm.getDocumentNo()!=null)
			{
				inwardDakBean.setDocumentNo(inwardDakForm.getDocumentNo());
			}			
			if(MisUtility.ifEmpty(inwardDakForm.getLocationId()))
			{
			inwardDakBean.setLocationId(inwardDakForm.getLocationId());
			}
			
			if(inwardDakForm.getDocumentReferenceNo()!="" && inwardDakForm.getDocumentReferenceNo()!=null)
			{
				inwardDakBean.setDocumentRefNo(inwardDakForm.getDocumentReferenceNo());
			}
			if(MisUtility.ifEmpty(inwardDakForm.getRecieptDate())){
				inwardDakBean.setReceiptDate(MisUtility.convertStringToDate(inwardDakForm.getRecieptDate()));
			}
			inwardDakFindBean=inwardDakDao.getInwardDakBeans(inwardDakBean, statusList);
			return inwardDakFindBean;
		}catch(DataAccessException e)
		{
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveInwardDak(InwardDakForm inwardDakForm,
			MISSessionBean misSessionBean,DocumentNumberBean documentNumberBean) throws MISException {
		log.debug("inside save--"+documentNumberBean.getLastNumber());
		try{
			
			MISAuditBean misAuditBean=new MISAuditBean();
			InwardDakBean inwardDakBean=new InwardDakBean();			
			inwardDakBean.setDocumentNo(documentNumberBean.getLastNumber().toString());	
			inwardDakBean.setRowCretDate(new Date((new java.util.Date()).getTime()));
			if(inwardDakForm.getLocationId()!=""&&inwardDakForm.getLocationId()!=null)
			{
			inwardDakBean.setLocationId(inwardDakForm.getLocationId());
			}else
			{
				throw new MISException(); 
			}
			if(inwardDakForm.getDocumentType()!=""&&inwardDakForm.getDocumentType()!=null)
			{
				inwardDakBean.setDocumentType(inwardDakForm.getDocumentType());
			}else
			{
				throw new MISException(); 
			}
			
			inwardDakBean.setDocumentRefNo(inwardDakForm.getDocumentReferenceNo());
			inwardDakBean.setReceiptDate(MisUtility.convertStringToDate(inwardDakForm.getRecieptDate()));
			inwardDakBean.setSender_Address(inwardDakForm.getSenderAddress());
			inwardDakBean.setSenderName(inwardDakForm.getSenderName());
			inwardDakBean.setSubject(inwardDakForm.getSubject());
			inwardDakBean.setForwardedTo(inwardDakForm.getForwardedTo());
			
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setAuthBy(0);
			
			inwardDakBean.setMisAuditBean(misAuditBean);
			
			inwardDakDao.saveOrUpdateInwardDakBean(inwardDakBean);
			documentNumberDAO.saveOrUpdateDocumentNumberBeans(documentNumberBean);	
			
			
		}catch(MISException e)
		{
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}catch(DataAccessException e)
		{
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return true;
			
		
	}
	public boolean updateInwardDak(InwardDakForm inwardDakForm,
			MISSessionBean misSessionBean,DocumentNumberBean documentNumberBean) throws MISException {
		log.debug("inside save--"+documentNumberBean.getLastNumber());
		try{
			
			MISAuditBean misAuditBean=new MISAuditBean();
			InwardDakBean inwardDakBean=new InwardDakBean();			
			inwardDakBean.setDocumentNo(documentNumberBean.getLastNumber().toString());			
			if(inwardDakForm.getLocationId()!=""&&inwardDakForm.getLocationId()!=null)
			{
			inwardDakBean.setLocationId(inwardDakForm.getLocationId());
			}else
			{
				throw new MISException(); 
			}
			if(inwardDakForm.getDocumentType()!=""&&inwardDakForm.getDocumentType()!=null)
			{
				inwardDakBean.setDocumentType(inwardDakForm.getDocumentType());
			}else
			{
				throw new MISException(); 
			}
			
			inwardDakBean.setDocumentRefNo(inwardDakForm.getDocumentReferenceNo());
			inwardDakBean.setReceiptDate(MisUtility.convertStringToDate(inwardDakForm.getRecieptDate()));
			inwardDakBean.setSender_Address(inwardDakForm.getSenderAddress());
			inwardDakBean.setSenderName(inwardDakForm.getSenderName());
			inwardDakBean.setSubject(inwardDakForm.getSubject());
			inwardDakBean.setForwardedTo(inwardDakForm.getForwardedTo());
			
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setAuthBy(0);
			
			inwardDakBean.setMisAuditBean(misAuditBean);
			
			inwardDakDao.saveOrUpdateInwardDakBean(inwardDakBean);		
			
		}catch(MISException e)
		{
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}catch(DataAccessException e)
		{
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return true;
			
		
	}
	

}
