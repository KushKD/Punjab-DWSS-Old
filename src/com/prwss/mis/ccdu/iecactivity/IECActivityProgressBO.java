package com.prwss.mis.ccdu.iecactivity;

import java.util.List;

import com.prwss.mis.ccdu.iecactivity.dao.IECActivityProgressBean;
import com.prwss.mis.ccdu.iecactivity.struts.IecActivityProgressForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;

public interface IECActivityProgressBO {

	public List<IECActivityProgressBean> findCBIECActivityProgress(IecActivityProgressForm iecActivityProgressForm, List<String> statusList) throws MISException;
	
	public long saveCBIECActivityProgress(IecActivityProgressForm iecActivityProgressForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean updateCBIECActivityProgress(IecActivityProgressForm iecActivityProgressForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean deleteCBIECActivityProgress(IecActivityProgressForm iecActivityProgressForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean postCBIECActivityProgress(IecActivityProgressForm iecActivityProgressForm, MISSessionBean misAuditBean) throws MISException;

}
