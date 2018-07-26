package com.prwss.mis.daktask.outwarddak;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.daktask.outwarddak.dao.OutwardDakBean;
import com.prwss.mis.daktask.outwarddak.dao.OutwardDakDao;
import com.prwss.mis.daktask.outwarddak.struts.OutwardDakForm;
import com.prwss.mis.sd.document.dao.DocumentNumberBean;
import com.prwss.mis.sd.document.dao.DocumentNumberDAO;

public class OutwardDakBOImpl implements OutwardDakBO {
	Logger log=Logger.getLogger(OutwardDakBOImpl.class);
	private DocumentNumberDAO documentNumberDao;
	
	private OutwardDakDao outwardDakDao;
	

	public void setDocumentNumberDao(DocumentNumberDAO documentNumberDao) {
		this.documentNumberDao = documentNumberDao;
	}

	public void setOutwardDakDao(OutwardDakDao outwardDakDao) {
		this.outwardDakDao = outwardDakDao;
	}

	@Override
	public List<OutwardDakBean> findOutwardDak(OutwardDakForm outwardDakForm,
			List<String> statusList) throws MISException {
			List<OutwardDakBean> outwardDakFindBean=null;
			
			try {			
				OutwardDakBean outwardDakBean=new OutwardDakBean();
				if(outwardDakForm.getDocumentType()!=""&&outwardDakForm.getDocumentType()!=null)
				{
					outwardDakBean.setDocumentType(outwardDakForm.getDocumentType());
				}			
				if(outwardDakForm.getLocationId()!=""&&outwardDakForm.getLocationId()!=null)
				{
					outwardDakBean.setLocationId(outwardDakForm.getLocationId());
				}
				
				if(MisUtility.ifEmpty(outwardDakForm.getDocumentReferenceNo()))
				{
					outwardDakBean.setDocumentRefNo(outwardDakForm.getDocumentReferenceNo());
				}
				if(MisUtility.ifEmpty(outwardDakForm.getDispatchDate()))
				{
					outwardDakBean.setDispatchDate(MisUtility.convertStringToDate(outwardDakForm.getDispatchDate()));
				}
				
				outwardDakFindBean=outwardDakDao.getOutwardDakBeans(outwardDakBean, statusList);
				
			}catch(DataAccessException e)
			{
				log.error(e.getLocalizedMessage(),e);
				throw new MISException(e);
			}
			return outwardDakFindBean;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public long saveOutwardDak(OutwardDakForm outwardDakForm,
			MISSessionBean misSessionBean,DocumentNumberBean documentNumberBean) throws MISException {
		long outwardDakId = 0;
		boolean status;
		try {
			MISAuditBean misAuditBean=new MISAuditBean();
			OutwardDakBean outwardDakBean=new OutwardDakBean();
			outwardDakBean.setDocumentRefNo(documentNumberBean.getLastNumber());
			outwardDakId = documentNumberBean.getLastNumber();
			outwardDakBean.setLocationId(outwardDakForm.getLocationId());
			outwardDakBean.setDispatchDate(MisUtility.convertStringToDate(outwardDakForm.getDispatchDate()));
			outwardDakBean.setDispatchThrough(outwardDakForm.getDispatchThrough());
			outwardDakBean.setDocumentType(outwardDakForm.getDocumentType());		
			outwardDakBean.setPostal_charge(outwardDakForm.getPostalCharge());
			outwardDakBean.setReceiver_Address(outwardDakForm.getReceiverAddress());
			outwardDakBean.setReceiverName(outwardDakForm.getReceiverName());
			outwardDakBean.setSubject(outwardDakForm.getSubject());
			outwardDakBean.setEnclosures(outwardDakForm.getEnclosures());
			outwardDakBean.setAfterIssue(outwardDakForm.getAfterIssue());
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			outwardDakBean.setMisAuditBean(misAuditBean);
			status = outwardDakDao.save(outwardDakBean);
			documentNumberDao.saveOrUpdateDocumentNumberBeans(documentNumberBean);	
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return outwardDakId;
	}

	@Override
	public boolean updateOutwardDak(OutwardDakForm outwardDakForm,
			MISSessionBean misSessionBean) throws MISException {
		
		MISAuditBean misAuditBean=new MISAuditBean();
		OutwardDakBean outwardDakBean=new OutwardDakBean();
		outwardDakBean.setLocationId(outwardDakForm.getLocationId());
		outwardDakBean.setDispatchDate(MisUtility.convertStringToDate(outwardDakForm.getDispatchDate()));
		outwardDakBean.setDispatchThrough(outwardDakForm.getDispatchThrough());
		outwardDakBean.setDocumentRefNo(outwardDakForm.getDocumentReferenceNo());
		outwardDakBean.setDocumentType(outwardDakForm.getDocumentType());		
		outwardDakBean.setPostal_charge(outwardDakForm.getPostalCharge());
		outwardDakBean.setReceiver_Address(outwardDakForm.getReceiverAddress());
		outwardDakBean.setReceiverName(outwardDakForm.getReceiverName());
		outwardDakBean.setSubject(outwardDakForm.getSubject());
		outwardDakBean.setEnclosures(outwardDakForm.getEnclosures());
		outwardDakBean.setAfterIssue(outwardDakForm.getAfterIssue());
		misAuditBean.setEntBy(misSessionBean.getEnteredBy());
		misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		outwardDakBean.setMisAuditBean(misAuditBean);
		boolean status=outwardDakDao.saveOrUpdateOutwardDakBean(outwardDakBean);
		return status;
	}
	

}
