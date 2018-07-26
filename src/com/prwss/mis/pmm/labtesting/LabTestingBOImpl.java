/**
 * 
 */
package com.prwss.mis.pmm.labtesting;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.labtesting.dao.LabTestingDao;
import com.prwss.mis.pmm.labtesting.struts.LabTestingForm;

/**
 * @author pjha
 *
 */
public class LabTestingBOImpl implements LabTestingBO {
	private Logger log = Logger.getLogger(LabTestingBOImpl.class);
	private LabTestingDao labTestingDao;
	
	

	public void setLabTestingDao(LabTestingDao labTestingDao) {
		this.labTestingDao = labTestingDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<LabTestingBean> findLabTesting(LabTestingForm labTestingForm,
			List<String> statusList) throws MISException {
		List<LabTestingBean> labTestingBeans  = new ArrayList<LabTestingBean>();
		
		try {
			LabTestingBean labTestingBean = new LabTestingBean();
			labTestingBean.setVillageId(labTestingForm.getVillageId());
			if(MisUtility.ifEmpty(labTestingForm.getTestId()))
			labTestingBean.setLabTestid(labTestingForm.getTestId());
			labTestingBeans =labTestingDao.findLabTesting(labTestingBean, statusList);
		
	} catch (DataAccessException e) {
		throw e;
	}
	return labTestingBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public long saveLabTesting(LabTestingForm labTestingForm,
			MISSessionBean misSessionBean) throws MISException {
		long labTestNumber = 0;
		try {
			
			LabTestingBean labTestingBean =populateLabTestingBean(labTestingForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			labTestingBean.setMisAuditBean(misAuditBean);

			labTestNumber = labTestingDao.saveLabTesting(labTestingBean);

//			if(!status){
//				log.error(labTestingBean);
//				throw new MISException(MISExceptionCodes.MIS003, "Failed to save Lab Testing  details");
//			}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} 


		return labTestNumber;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateLabTesting(LabTestingForm labTestingForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			long labId= labTestingForm.getTestId();
			LabTestingBean labTestingBean =populateLabTestingBean(labTestingForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			labTestingBean.setMisAuditBean(misAuditBean);
			labTestingBean.setLabTestid(labId);

			status = labTestingDao.saveOrUpdateLabTesting(labTestingBean);

			if(!status){
				log.error(labTestingBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Update Lab Testing  details");
			}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}


		return true;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteLabTesting(LabTestingForm labTestingForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			LabTestingBean labTestingBean =populateLabTestingBean(labTestingForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			labTestingBean=labTestingDao.findLabTesting(labTestingBean, statusList).get(0);
			labTestingBean.setMisAuditBean(misAuditBean);

			status = labTestingDao.saveOrUpdateLabTesting(labTestingBean);

			if(!status){
				log.error(labTestingBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Lab Testing  details");
			}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}


		return true;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postLabTesting(LabTestingForm labTestingForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			boolean status = false;
			LabTestingBean labTestingBean =new LabTestingBean();
			labTestingBean.setLabTestid(labTestingForm.getTestId());
			labTestingBean.setVillageId(labTestingForm.getVillageId());
			labTestingBean=labTestingDao.findLabTesting(labTestingBean, statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean=labTestingBean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			labTestingBean.setMisAuditBean(misAuditBean);

			status = labTestingDao.saveOrUpdateLabTesting(labTestingBean);

			if(!status){
				log.error(labTestingBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Lab Testing  details");
			}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}


		return true;
	}

	
private LabTestingBean populateLabTestingBean(LabTestingForm labTestingForm){
		
		
	LabTestingBean labTestingBean = new LabTestingBean();
		try {
			labTestingBean.setBlockId(labTestingForm.getBlockId());
			labTestingBean.setOverallSampleResult(labTestingForm.getOverallSampleResult());
			labTestingBean.setVillageId(labTestingForm.getVillageId());
			labTestingBean.setAlkalinityActual(labTestingForm.getAlkalinityActual());
			labTestingBean.setAlkalinityResult(labTestingForm.getAlkalinityResult());
			labTestingBean.setArsenicActual(labTestingForm.getArsenicActual());
			labTestingBean.setArsenicResult(labTestingForm.getArsenicResult());
			labTestingBean.setCadmiumActual(labTestingForm.getCadmiumActual());
			labTestingBean.setCadmiumResult(labTestingForm.getCadmiumResult());
			labTestingBean.setCalciumActual(labTestingForm.getCalciumActual());
			labTestingBean.setCalciumResult(labTestingForm.getCalciumResult());
			labTestingBean.setChlorideActual(labTestingForm.getChlorideActual());
			labTestingBean.setChlorideResult(labTestingForm.getChlorideResult());
			labTestingBean.setColourActual(labTestingForm.getColourActual());
			labTestingBean.setColourResult(labTestingForm.getColourResult());
			labTestingBean.setDissolvedActual(labTestingForm.getDissolvedActual());
			labTestingBean.setDissolvedResult(labTestingForm.getDissolvedResult());
			labTestingBean.setFluoridesActual(labTestingForm.getFluoridesActual());
			labTestingBean.setFluoridesResult(labTestingForm.getFluoridesResult());
			labTestingBean.setHabitation(labTestingForm.getHabitation());
			labTestingBean.setHardnessActual(labTestingForm.getHardnessActual());
			labTestingBean.setHardnessResult(labTestingForm.getHardnessResult());
			labTestingBean.setIronActual(labTestingForm.getIronActual());
			labTestingBean.setIronResult(labTestingForm.getIronResult());
			labTestingBean.setLabName(labTestingForm.getLabName());
			labTestingBean.setLocationId(labTestingForm.getLocationId());
			labTestingBean.setNitrateActual(labTestingForm.getNitrateActual());
			labTestingBean.setNitrateResult(labTestingForm.getNitrateResult());
			labTestingBean.setOther1Actual(labTestingForm.getOther1Actual());
			labTestingBean.setOther1Desirable(labTestingForm.getOther1Desirable());
			labTestingBean.setOther1Name(labTestingForm.getOther1Name());
			labTestingBean.setOther1Permissible(labTestingForm.getOther1Permissible());
			labTestingBean.setOther1Result(labTestingForm.getOther1Result());
			labTestingBean.setOther2Actual(labTestingForm.getOther2Actual());
			labTestingBean.setOther2Desirable(labTestingForm.getOther2Desirable());
			labTestingBean.setOther2Name(labTestingForm.getOther2Name());
			labTestingBean.setOther2Permissible(labTestingForm.getOther2Permissible());
			labTestingBean.setOther2Result(labTestingForm.getOther2Result());
			labTestingBean.setOther3Actual(labTestingForm.getOther3Actual());
			labTestingBean.setOther3Desirable(labTestingForm.getOther3Desirable());
			labTestingBean.setOther3Name(labTestingForm.getOther3Name());
			labTestingBean.setOther3Permissible(labTestingForm.getOther3Permissible());
			labTestingBean.setOther3Result(labTestingForm.getOther3Result());
			labTestingBean.setOther4Actual(labTestingForm.getOther4Actual());
			labTestingBean.setOther4Desirable(labTestingForm.getOther4Desirable());
			labTestingBean.setOther4Name(labTestingForm.getOther4Name());
			labTestingBean.setOther4Permissible(labTestingForm.getOther4Permissible());
			labTestingBean.setOther4Result(labTestingForm.getOther4Result());
			labTestingBean.setOtherbact1Actual(labTestingForm.getOtherbact1Actual());
			labTestingBean.setOtherbact1Desirable(labTestingForm.getOtherbact1Desirable());
			labTestingBean.setOtherbact1Name( labTestingForm.getOtherbact1Name());
			labTestingBean.setOtherbact1Permissible(labTestingForm.getOtherbact1Permissible());
			labTestingBean.setOtherbact1Result(labTestingForm.getOtherbact1Result());
			labTestingBean.setOtherbact2Actual(labTestingForm.getOtherbact2Actual());
			labTestingBean.setOtherbact2Desirable(labTestingForm.getOtherbact2Desirable());
			labTestingBean.setOtherbact2Name(labTestingForm.getOtherbact2Name());
			labTestingBean.setOtherbact2Permissible(labTestingForm.getOtherbact2Permissible());
			labTestingBean.setOtherbact2Result(labTestingForm.getOtherbact2Result());
			labTestingBean.setPhActual(labTestingForm.getPhActual());
			labTestingBean.setPhResult(labTestingForm.getPhResult());
			labTestingBean.setResidualActual(labTestingForm.getResidualActual());
			labTestingBean.setResidualResult(labTestingForm.getResidualResult());
			labTestingBean.setSeleniumActual(labTestingForm.getSeleniumActual());
			labTestingBean.setSeleniumResult(labTestingForm.getSeleniumResult());
			labTestingBean.setSulphateActual(labTestingForm.getSulphateActual());
			labTestingBean.setSulphateResult(labTestingForm.getSulphateResult());
			labTestingBean.setTasteOdourActual(labTestingForm.getTasteOdourActual());
			labTestingBean.setTasteOdourResult(labTestingForm.getTasteOdourResult());
			labTestingBean.setTestDate(MisUtility.convertStringToDate(labTestingForm.getTestDate()));
			labTestingBean.setTurbidityActual(labTestingForm.getTurbidityActual());
			labTestingBean.setTurbidityResult(labTestingForm.getTurbidityResult());
			labTestingBean.setTypeOfParameter(labTestingForm.getTypeOfParameter());
			labTestingBean.setTypeOfWaterSource(labTestingForm.getTypeOfWaterSource());
			labTestingBean.setUraniumActual(labTestingForm.getUraniumActual());
			labTestingBean.setUraniumResult(labTestingForm.getUraniumResult());
					
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return labTestingBean;
		
		
		
	}
	
	
}
