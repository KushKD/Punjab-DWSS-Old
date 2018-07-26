/**
 * 
 */
package com.prwss.mis.pmm.dsrsewerage;

import java.util.List;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.pmm.dsrsewerage.struts.DSRSewerageForm;

/**
 * @author pjha
 *
 */
public interface DSRSewerageBO {
	public List<DSRSewerageBean> findDSRSewerage(DSRSewerageForm dsrSewerageForm, List<String> statusList) throws MISException;

	public boolean saveDSRSewerage(DSRSewerageForm dsrSewerageForm, MISSessionBean misSessionBean) throws MISException;

	public boolean updateDSRSewerage(DSRSewerageForm dsrSewerageForm, MISSessionBean misSessionBean) throws MISException;

	public boolean deleteDSRSewerage(DSRSewerageForm dsrSewerageForm, MISSessionBean misSessionBean) throws MISException;

	public boolean postDSRSewerage(DSRSewerageForm dsrSewerageForm, MISSessionBean misSessionBean) throws MISException;
}
