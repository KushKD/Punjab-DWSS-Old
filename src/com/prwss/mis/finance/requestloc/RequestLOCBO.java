/**
 * 
 */
package com.prwss.mis.finance.requestloc;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.finance.loc.LOCHeaderBean;
import com.prwss.mis.finance.requestloc.struts.RequestLOCForm;

/**
 * @author PJHA
 *
 */
public interface RequestLOCBO {

	public List<LOCHeaderBean> findRequestLOC(RequestLOCForm requestLOCForm, List<String> statusList) throws MISException;

	public long saveRequestLOC(RequestLOCForm requestLOCForm, MISSessionBean misSessionBean) throws MISException;

	public boolean updateRequestLOC(RequestLOCForm requestLOCForm, MISSessionBean misSessionBean) throws MISException;

	public boolean deleteRequestLOC(RequestLOCForm requestLOCForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean postRequestLOC(RequestLOCForm requestLOCForm,MISSessionBean misSessionBean) throws MISException;

}
