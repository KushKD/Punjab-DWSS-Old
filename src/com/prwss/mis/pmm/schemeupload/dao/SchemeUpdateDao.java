package com.prwss.mis.pmm.schemeupload.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.pmm.schemeupload.struts.SchemeUploadForm;


public interface SchemeUpdateDao {
	
	public boolean uploadScheme(SchemeUploadBean schemeUploadBean) throws DataAccessException;

	public List<SchemeUploadBean> getUploadScheme( SchemeUploadForm schemeuploadFom) throws DataAccessException;

	public List<SchemeUploadDto> getAttachmentData(String name, String id , String ColumnName) throws DataAccessException;

	public List<SchemeUploadBean> checkSchemeExistsOrNot( SchemeUploadForm schemeUploadFindBean) throws DataAccessException;

	public boolean uploadSchemeUpdate(SchemeUploadBean schemeUploadFindBean) throws DataAccessException;

	public SchemeUploadBean getId(SchemeUploadForm schemeuploadFom) throws DataAccessException;

}
