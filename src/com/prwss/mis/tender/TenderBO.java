package com.prwss.mis.tender;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.tender.award.dao.TenderAwardBean;
import com.prwss.mis.tender.dao.AdvUploadBean;
import com.prwss.mis.tender.dao.CorrigendumUploadBean;
import com.prwss.mis.tender.dao.EOIUploadBean;
import com.prwss.mis.tender.dao.QuoatationUploadBean;
import com.prwss.mis.tender.dao.TenderDetailBean;
import com.prwss.mis.tender.dao.TenderHeaderBean;
import com.prwss.mis.tender.dao.TenderUploadBean;
import com.prwss.mis.tender.struts.AdvUploadForm;
import com.prwss.mis.tender.struts.CorrigendumUploadForm;
import com.prwss.mis.tender.struts.EOIUploadForm;
import com.prwss.mis.tender.struts.QuoatationUploadForm;
import com.prwss.mis.tender.struts.TenderAwardForm;
import com.prwss.mis.tender.struts.TenderManagementForm;
import com.prwss.mis.tender.struts.TenderUploadForm;

public interface TenderBO {
	
	public List<TenderHeaderBean> findTenders(TenderManagementForm tenderManagementForm, List<String> statusList) throws MISException;
	public String save(TenderManagementForm tenderManagementForm, MISSessionBean misAuditBean) throws MISException;
	public boolean update(TenderManagementForm tenderManagementForm, MISSessionBean misAuditBean) throws MISException;
	public boolean delete(TenderManagementForm tenderManagementForm, MISSessionBean misAuditBean) throws MISException;
	public String post(TenderManagementForm tenderManagementForm, MISSessionBean misAuditBean) throws MISException;
	
	public Set<TenderDetailBean> getTenderDetails(String tenderId) throws MISException;
	
	public List<TenderAwardBean> findTenderAwarded(TenderAwardForm tenderAwardForm, List<String> statusList) throws MISException;	
	public String saveTenderAwarded(TenderAwardForm tenderAwardForm, MISSessionBean misAuditBean) throws MISException;	
	public boolean updateTenderAwarded(TenderAwardForm tenderAwardForm, MISSessionBean misAuditBean) throws MISException;	
	public boolean deleteTenderAwarded(TenderAwardForm tenderAwardForm, MISSessionBean misAuditBean) throws MISException;
	public String postTenderAwarded(TenderAwardForm tenderAwardForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean uploadTender(TenderUploadBean tenderUploadBean,MISSessionBean misSessionBean) throws MISException;
	public boolean uploadEOI(EOIUploadBean eoiUploadBean,MISSessionBean misSessionBean) throws MISException;
	public boolean uploadQuoatation(QuoatationUploadBean quoatationUploadBean,MISSessionBean misSessionBean) throws MISException;
	public boolean uploadAdv(AdvUploadBean advUploadBean,MISSessionBean misSessionBean) throws MISException;
	public boolean uploadCorrigendum(CorrigendumUploadBean corrigendumUploadBean,MISSessionBean misSessionBean) throws MISException;
	
	public List<TenderUploadBean> getTenderUploaded(TenderUploadBean tenderUploadBean) throws DataAccessException;
	public List<TenderUploadBean> findTenderUploaded(TenderUploadForm tenderUploadForm, List<String> statusList) throws MISException;
	public List<EOIUploadBean> getEOIUploaded(EOIUploadBean EOIUploadBean) throws DataAccessException;
	public List<EOIUploadBean> findEOIUploaded(EOIUploadForm eoiUploadForm, List<String> statusList) throws MISException;
	
	public List<QuoatationUploadBean> getQuoatationUploaded(QuoatationUploadBean quoatationUploadBean) throws DataAccessException;
	public List<QuoatationUploadBean> findQuoatationUploaded(QuoatationUploadForm quoatationUploadForm, List<String> statusList) throws MISException;
	
	public List<AdvUploadBean> getAdvUploaded(AdvUploadBean advUploadBean) throws DataAccessException;
	public List<AdvUploadBean> findAdvUploaded(AdvUploadForm advUploadForm, List<String> statusList) throws MISException;
	
	public List<CorrigendumUploadBean> getCorrigendumUploaded(CorrigendumUploadBean corrigendumUploadBean) throws DataAccessException;
	public List<CorrigendumUploadBean> findCorrigendumUploaded(CorrigendumUploadForm corrigendumUploadForm, List<String> statusList) throws MISException;
	
	public boolean updateAdv(AdvUploadBean advUploadBean, MISSessionBean misSessionBean)
			throws MISException;

}
