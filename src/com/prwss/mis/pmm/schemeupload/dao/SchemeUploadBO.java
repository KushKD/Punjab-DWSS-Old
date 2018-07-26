package com.prwss.mis.pmm.schemeupload.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.pmm.schemeupload.struts.SchemeUploadForm;

public interface SchemeUploadBO {
	
	public boolean uploadScheme(SchemeUploadForm schemeUploadForm,MISSessionBean misSessionBean) throws MISException,DataAccessException,FileNotFoundException,IOException;

	public List<SchemeUploadBean> getUploadedSchemes( SchemeUploadForm schemeuploadFom, MISSessionBean misSessionBean) throws MISException,DataAccessException,FileNotFoundException,IOException;

	public boolean uploadSchemeUpdate(SchemeUploadForm schemeuploadFom, MISSessionBean misSessionBean)  throws MISException,DataAccessException,FileNotFoundException,IOException;;

}
