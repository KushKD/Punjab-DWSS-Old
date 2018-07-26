/**
 * 
 */
package com.prwss.mis.pmm.dsrtubewell;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.pmm.dsrtubewell.struts.DSRTubeWellForm;

/**
 * @author pjha
 *
 */
public interface DSRTubeWellBO {
	public List<DSRTubeWellBean> findDSRTubeWell(DSRTubeWellForm dsrTubeWellForm, List<String> statusList) throws MISException;

	public boolean saveDSRTubeWell(DSRTubeWellForm dsrTubeWellForm, MISSessionBean misSessionBean) throws MISException;

	public boolean updateDSRTubeWell(DSRTubeWellForm dsrTubeWellForm, MISSessionBean misSessionBean) throws MISException;

	public boolean deleteDSRTubeWell(DSRTubeWellForm dsrTubeWellForm, MISSessionBean misSessionBean) throws MISException;

	public boolean postDSRTubeWell(DSRTubeWellForm dsrTubeWellForm, MISSessionBean misSessionBean) throws MISException;

	public DSRTubeWellBean validateDSRTubeWellBean(
			DSRTubeWellForm dsrTubeWellForm);
}
