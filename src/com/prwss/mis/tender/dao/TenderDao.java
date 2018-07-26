package com.prwss.mis.tender.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.tender.responsive.NonResponsiveTenderBean;
import com.prwss.mis.tender.responsive.ResponsiveTenderBean;

public interface TenderDao {
	
	public  List<TenderHeaderBean> findTenderV() throws DataAccessException;
	public  List<TenderHeaderBean> findTender(TenderHeaderBean tenderBean, List<String> statusList) throws DataAccessException;
	public  List<TenderHeaderBean> findTender(List<String> tenderIdList) throws DataAccessException;
	public String saveTender(TenderHeaderBean tenderBean) throws DataAccessException;
	public boolean updateTender(TenderHeaderBean tenderBean) throws DataAccessException;
	public Set<TenderHeaderBean> getDistinctTenderCodes(String locationId, List<String> statusList) throws DataAccessException;
	public Set<TenderHeaderBean> getDistinctTenders(TenderHeaderBean tenderBean, List<String> statusList) throws DataAccessException;
	public boolean updateTender(List<TenderHeaderBean> tenderBeans) throws DataAccessException;
	
	public boolean uploadTender(TenderUploadBean tenderUploadBean) throws DataAccessException;
	public boolean uploadEOI(EOIUploadBean eoiUploadBean) throws DataAccessException;
	public boolean uploadQuoatation(QuoatationUploadBean quoatationUploadBean) throws DataAccessException;
	public boolean uploadAdv(AdvUploadBean advUploadBean) throws DataAccessException;
	public boolean uploadCorrigendum(CorrigendumUploadBean corrigendumUploadBean) throws DataAccessException;
	
	public List<TenderUploadBean> getTenderUploaded(TenderUploadBean tenderUploadBean) throws DataAccessException;
	public  List<TenderUploadBean> findTenderUploaded(TenderUploadBean tenderUploadBean, List<String> statusList) throws DataAccessException;
	public List<EOIUploadBean> getEOIUploaded(EOIUploadBean eoiUploadBean) throws DataAccessException;
	public  List<EOIUploadBean> findEOIUploaded(EOIUploadBean eoiUploadBean, List<String> statusList) throws DataAccessException;
	
	public List<QuoatationUploadBean> getQuoatationUploaded(QuoatationUploadBean quoatationUploadBean) throws DataAccessException;
	public  List<QuoatationUploadBean> findQuoatationUploaded(QuoatationUploadBean quoatationUploadBean, List<String> statusList) throws DataAccessException;
	
	public List<AdvUploadBean> getAdvUploaded(AdvUploadBean advUploadBean) throws DataAccessException;
	public  List<AdvUploadBean> findAdvUploaded(AdvUploadBean advUploadBean, List<String> statusList) throws DataAccessException;
	
	public List<CorrigendumUploadBean> getCorrigendumUploaded(CorrigendumUploadBean corrigendumUploadBean) throws DataAccessException;
	public  List<CorrigendumUploadBean> findCorrigendumUploaded(CorrigendumUploadBean corrigendumUploadBean, List<String> statusList) throws DataAccessException;
	
	public boolean updateAdv(AdvUploadBean advUploadBean) throws DataAccessException;
	public Set<ResponsiveTenderBean> getDistinctTenderCodesResponsive(
			String locationId)
			throws DataAccessException;
	public Set<NonResponsiveTenderBean> getDistinctTenderCodesNONResponsive(
			String packageId) throws DataAccessException;
	
}
