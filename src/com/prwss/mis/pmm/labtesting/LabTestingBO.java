/**
 * 
 */
package com.prwss.mis.pmm.labtesting;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.pmm.labtesting.struts.LabTestingForm;

/**
 * @author pjha
 *
 */
public interface LabTestingBO {
	public List<LabTestingBean> findLabTesting(LabTestingForm labTestingForm,List<String> statusList) throws MISException;
	public long saveLabTesting(LabTestingForm labTestingForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean updateLabTesting(LabTestingForm labTestingForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean deleteLabTesting(LabTestingForm labTestingForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean postLabTesting(LabTestingForm labTestingForm,  MISSessionBean misSessionBean) throws MISException;
}
