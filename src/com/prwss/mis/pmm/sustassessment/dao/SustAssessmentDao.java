/**
 * 
 */
package com.prwss.mis.pmm.sustassessment.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.pmm.sustassessment.SustAssessmentBean;



/**
 * @author pjha
 *
 */
public interface SustAssessmentDao {
	public  List<SustAssessmentBean> findSustAssessment(SustAssessmentBean sustAssessmentBean ,List<String> statusList) throws DataAccessException;

	public boolean saveSustAssessment(SustAssessmentBean sustAssessmentBean) throws DataAccessException;

	public boolean saveOrUpdateSustAssessment(SustAssessmentBean sustAssessmentBean) throws DataAccessException;

	public List<SustAssessmentBean> checkSustAssessment(
			SustAssessmentBean sustAssessmentBean, List<String> statusList)
			throws DataAccessException;

	public boolean updateSustAssessment(SustAssessmentBean sustAssessmentBean)
			throws DataAccessException;

	public List<SustAssessmentBean> findSustAssessment(
			SustAssessmentBean sustAssessmentBean) throws DataAccessException;


}
