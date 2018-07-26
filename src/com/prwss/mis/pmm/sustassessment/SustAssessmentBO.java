/**
 * 
 */
package com.prwss.mis.pmm.sustassessment;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.pmm.sustassessment.struts.SustAssessmentForm;

/**
 * @author pjha
 *
 */
public interface SustAssessmentBO {

	public List<SustAssessmentBean> findSustAssessment(SustAssessmentForm sustAssessmentForm,List<String> statusList) throws MISException;
	public boolean saveSustAssessment(SustAssessmentForm sustAssessmentForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean updateSustAssessment(SustAssessmentForm sustAssessmentForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteSustAssessment(SustAssessmentForm sustAssessmentForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean postSustAssessment(SustAssessmentForm sustAssessmentForm,  MISSessionBean misSessionBean) throws MISException;
}
