/**
 * 
 */
package com.prwss.mis.pmm.dsrcanal;

import java.util.List;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.pmm.dsrcanal.struts.DSRCanalForm;

/**
 * @author pjha
 *
 */
public interface DSRCanalBO {
	public List<DSRCanalBean> findDSRCanal(DSRCanalForm dsrCanalForm, List<String> statusList) throws MISException;

	public boolean saveDSRCanal(DSRCanalForm dsrCanalForm, MISSessionBean misSessionBean) throws MISException;

	public boolean updateDSRCanal(DSRCanalForm dsrCanalForm, MISSessionBean misSessionBean) throws MISException;

	public boolean deleteDSRCanal(DSRCanalForm dsrCanalForm, MISSessionBean misSessionBean) throws MISException;

	public boolean postDSRCanal(DSRCanalForm dsrCanalForm, MISSessionBean misSessionBean) throws MISException;

	public DSRCanalBean validateDSRCanalBean(DSRCanalForm dsrCanalForm);
}
