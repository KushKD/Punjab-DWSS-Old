/**
 * 
 */
package com.prwss.mis.finance.releaseloc;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.finance.loc.LOCHeaderBean;
import com.prwss.mis.finance.loc.dao.LOCDetailBean;
import com.prwss.mis.finance.releaseloc.struts.ReleaseLOCForm;

/**
 * @author PJHA
 *
 */
public interface ReleaseLOCBO {

	public List<LOCHeaderBean> findReleaseLOC(ReleaseLOCForm releaseLOCForm, List<String> statusList) throws MISException;

	public boolean saveReleaseLOC(ReleaseLOCForm releaseLOCForm, MISSessionBean misSessionBean) throws MISException;

	public boolean updateReleaseLOC(ReleaseLOCForm releaseLOCForm, MISSessionBean misSessionBean) throws MISException;

	public boolean deleteReleaseLOC(ReleaseLOCForm releaseLOCForm, MISSessionBean misSessionBean) throws MISException;

	List<LOCDetailBean> findReleaseLOCDetail(ReleaseLOCForm releaseLOCForm,
			List<String> statusList) throws MISException;

}
