package com.prwss.mis.pmm.schemeupload.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import com.prwss.mis.pmm.schemeupload.struts.SchemeUploadForm;

public class SchemeUploadBOImpl implements SchemeUploadBO {
	
	private Logger log = Logger.getLogger(SchemeUploadBOImpl.class);
	
	private SchemeUpdateDao schemeUpdateDao;

	


	public void setSchemeUpdateDao(SchemeUpdateDao schemeUpdateDao) {
		this.schemeUpdateDao = schemeUpdateDao;
	}




	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean uploadScheme(SchemeUploadForm schemeUploadform,
			MISSessionBean misSessionBean) throws MISException, FileNotFoundException, IOException, DataAccessException {
		
		boolean status = false;
		
		SchemeUploadBean schemeUploadFindBean = new SchemeUploadBean();
		
		try {
			
			schemeUploadFindBean = populateSchemeUploadForm(schemeUploadform,misSessionBean);
			
			//Check weather the data exists or not
			
				if (MisUtility.ifEmpty(schemeUploadFindBean)) {
					//System.out.println(schemeUploadFindBean.toString());
					status = schemeUpdateDao.uploadScheme(schemeUploadFindBean);
				}
		
			
			
			
			
		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
			throw new MISException(e);
		}
		
		return status;
	}




	@Transactional
	private SchemeUploadBean populateSchemeUploadForm( SchemeUploadForm schemeUploadform, MISSessionBean misSessionBean) throws FileNotFoundException, IOException {

		SchemeUploadBean schemeUploadFindBean = new SchemeUploadBean();
		

		if (MisUtility.ifEmpty(schemeUploadform.getLocation_id())) {
			schemeUploadFindBean.setLocation_id(schemeUploadform.getLocation_id());
		}
		
		if (MisUtility.ifEmpty(schemeUploadform.getScheme_type())) {
			schemeUploadFindBean.setScheme_type(schemeUploadform.getScheme_type());
		}
		
		if (MisUtility.ifEmpty(schemeUploadform.getScheme_id())) {
			schemeUploadFindBean.setScheme_id(schemeUploadform.getScheme_id());
		}
		
		if (MisUtility.ifEmpty(misSessionBean.getUserId())) {
			schemeUploadFindBean.setCrt_by_usr(misSessionBean.getUserId1());
		}
		
		if (MisUtility.ifEmpty(schemeUploadform.getDigitalSurvey_name_pdf_File().getFileSize())) {
			schemeUploadFindBean.setDigitalSurvey_name_pdf(schemeUploadform.getDigitalSurvey_name_pdf_File().getFileName());
			schemeUploadFindBean.setDigitalSurvey_name_pdf_File(schemeUploadform.getDigitalSurvey_name_pdf_File().getFileData());
		}
		
		
		
		if (MisUtility.ifEmpty(schemeUploadform.getDigitalSurvey_name_cdr_File().getFileSize())) {
			schemeUploadFindBean.setDigitalSurvey_name_cdr(schemeUploadform.getDigitalSurvey_name_cdr_File().getFileName());
			schemeUploadFindBean.setDigitalSurvey_name_cdr_File(schemeUploadform.getDigitalSurvey_name_cdr_File().getFileData());
		}
		
		if (MisUtility.ifEmpty(schemeUploadform.getAdminAprovalFile().getFileSize())) {
			schemeUploadFindBean.setAdminAproval_name(schemeUploadform.getAdminAprovalFile().getFileName());
			schemeUploadFindBean.setAdminAprovalFile(schemeUploadform.getAdminAprovalFile().getFileData());
		}
		
		if (MisUtility.ifEmpty(schemeUploadform.getSchemeEstimateFile().getFileSize())) {
			schemeUploadFindBean.setSchemeEstimate_name(schemeUploadform.getSchemeEstimateFile().getFileName());
			schemeUploadFindBean.setSchemeEstimateFile(schemeUploadform.getSchemeEstimateFile().getFileData());
		}
		
		if (MisUtility.ifEmpty(schemeUploadform.getStrataChartFile().getFileSize())) {
			schemeUploadFindBean.setStrataChart_name(schemeUploadform.getStrataChartFile().getFileName());
			schemeUploadFindBean.setStrataChartFile(schemeUploadform.getStrataChartFile().getFileData());
		}
		
		if (MisUtility.ifEmpty(schemeUploadform.getId())) {
			schemeUploadFindBean.setId(schemeUploadform.getId());
		}
		
		return schemeUploadFindBean;
	}




	@Override
	public List<SchemeUploadBean> getUploadedSchemes(
			SchemeUploadForm schemeuploadFom, MISSessionBean misSessionBean)
			throws MISException, DataAccessException, FileNotFoundException,
			IOException {
		 
		List<SchemeUploadBean> getData = null;
		
		getData = schemeUpdateDao.getUploadScheme(schemeuploadFom);
		System.out.println("Size is"+getData.size());
		return getData;
	}




	@Override
	@Transactional
	public boolean uploadSchemeUpdate(SchemeUploadForm schemeuploadFom,
			MISSessionBean misSessionBean) throws MISException,
			DataAccessException, FileNotFoundException, IOException {
boolean status = false;
		
		SchemeUploadBean schemeUploadFindBean = new SchemeUploadBean();
		
		try {
			
			schemeUploadFindBean = populateSchemeUploadFormUpdate(schemeuploadFom,misSessionBean);
			
			//Check weather the data exists or not
			
				if (MisUtility.ifEmpty(schemeUploadFindBean)) {
					//System.out.println(schemeUploadFindBean.toString());
					status = schemeUpdateDao.uploadSchemeUpdate(schemeUploadFindBean);
				}
		
			
			
			
			
		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
			throw new MISException(e);
		}
		
		return status;
	}



    @Transactional
	private SchemeUploadBean populateSchemeUploadFormUpdate( SchemeUploadForm schemeuploadFom, MISSessionBean misSessionBean) throws FileNotFoundException, IOException {
		   
    	SchemeUploadBean bean = new SchemeUploadBean();
    	bean = schemeUpdateDao.getId(schemeuploadFom);
    	
		
    	if (MisUtility.ifEmpty(schemeuploadFom.getDigitalSurvey_name_pdf_File().getFileSize())) {
    		bean.setDigitalSurvey_name_pdf(schemeuploadFom.getDigitalSurvey_name_pdf_File().getFileName());
    		bean.setDigitalSurvey_name_pdf_File(schemeuploadFom.getDigitalSurvey_name_pdf_File().getFileData());
		}
		
		
		
		if (MisUtility.ifEmpty(schemeuploadFom.getDigitalSurvey_name_cdr_File().getFileSize())) {
			bean.setDigitalSurvey_name_cdr(schemeuploadFom.getDigitalSurvey_name_cdr_File().getFileName());
			bean.setDigitalSurvey_name_cdr_File(schemeuploadFom.getDigitalSurvey_name_cdr_File().getFileData());
		}
		
		if (MisUtility.ifEmpty(schemeuploadFom.getAdminAprovalFile().getFileSize())) {
			bean.setAdminAproval_name(schemeuploadFom.getAdminAprovalFile().getFileName());
			bean.setAdminAprovalFile(schemeuploadFom.getAdminAprovalFile().getFileData());
		}
		
		if (MisUtility.ifEmpty(schemeuploadFom.getSchemeEstimateFile().getFileSize())) {
			bean.setSchemeEstimate_name(schemeuploadFom.getSchemeEstimateFile().getFileName());
			bean.setSchemeEstimateFile(schemeuploadFom.getSchemeEstimateFile().getFileData());
		}
		
		if (MisUtility.ifEmpty(schemeuploadFom.getStrataChartFile().getFileSize())) {
			bean.setStrataChart_name(schemeuploadFom.getStrataChartFile().getFileName());
			bean.setStrataChartFile(schemeuploadFom.getStrataChartFile().getFileData());
		}
			
			
			
			
			
			
			return bean;
			
			
		}
	

}
