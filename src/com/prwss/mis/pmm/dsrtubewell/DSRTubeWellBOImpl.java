/**
 * 
 */
package com.prwss.mis.pmm.dsrtubewell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.dsrcanal.DSRCanalBean;
import com.prwss.mis.pmm.dsrcanal.struts.DSRCanalForm;
import com.prwss.mis.pmm.dsrtubewell.dao.DSRTubeWellDao;
import com.prwss.mis.pmm.dsrtubewell.dao.DSRTubeWellDistributionBean;
import com.prwss.mis.pmm.dsrtubewell.dao.DSRTubeWellDistributionDao;
import com.prwss.mis.pmm.dsrtubewell.struts.DSRTubeWellForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author pjha
 *
 */
public class DSRTubeWellBOImpl implements DSRTubeWellBO{
	private Logger log = Logger.getLogger(DSRTubeWellBOImpl.class);
	private DSRTubeWellDao dsrTubeWellDao ;
	private DSRTubeWellDistributionDao dsrTubeWellDistributionDao;

	public void setDsrTubeWellDao(DSRTubeWellDao dsrTubeWellDao) {
		this.dsrTubeWellDao = dsrTubeWellDao;
	}
	public void setDsrTubeWellDistributionDao(
			DSRTubeWellDistributionDao dsrTubeWellDistributionDao) {
		this.dsrTubeWellDistributionDao = dsrTubeWellDistributionDao;
	}
	@SuppressWarnings({ "unchecked"})
	private List<DSRTubeWellDistributionBean> populateDSRTubeWellDistributionBeans(DSRTubeWellForm dsrTubeWellForm,String schemeId,  MISSessionBean misSessionBean, String status){
		
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);

		List<DSRTubeWellDistributionBean> dsrTubeWellDistributionBeans = new ArrayList<DSRTubeWellDistributionBean>();


		Datagrid dsrTubeWellDistributionGrid = dsrTubeWellForm.getDsrTubeWellDistributionGrid();

		Collection<DSRTubeWellDistributionBean> addedDSRTubeWellDistributionBeans = dsrTubeWellDistributionGrid.getAddedData();
		if(!MisUtility.ifEmpty(addedDSRTubeWellDistributionBeans)){
			for (DSRTubeWellDistributionBean dsrTubeWellDistributionBean : addedDSRTubeWellDistributionBeans) {
				dsrTubeWellDistributionBean.setSchemeId(schemeId);
				dsrTubeWellDistributionBean.setMisAuditBean(misAuditBean);
				dsrTubeWellDistributionBeans.add(dsrTubeWellDistributionBean);
			}
		}

		Collection<DSRTubeWellDistributionBean> modifiedDSRTubeWellDistributionBeans = dsrTubeWellDistributionGrid.getModifiedData();
		if(!MisUtility.ifEmpty(modifiedDSRTubeWellDistributionBeans)){
			for (DSRTubeWellDistributionBean dsrTubeWellDistributionBean : modifiedDSRTubeWellDistributionBeans) {
				dsrTubeWellDistributionBean.setSchemeId(schemeId);
				dsrTubeWellDistributionBean.setMisAuditBean(misAuditBean);
				dsrTubeWellDistributionBeans.add(dsrTubeWellDistributionBean);
			}
				
			}
	
		
		Collection<DSRTubeWellDistributionBean> deletedDSRTubeWellDistributionBeans = dsrTubeWellDistributionGrid.getDeletedData();
		if(!MisUtility.ifEmpty(deletedDSRTubeWellDistributionBeans)){
			for (DSRTubeWellDistributionBean dsrTubeWellDistributionBean : deletedDSRTubeWellDistributionBeans) {
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				dsrTubeWellDistributionBean.setSchemeId(schemeId);
				dsrTubeWellDistributionBean.setMisAuditBean(misAuditBean);
				dsrTubeWellDistributionBeans.add(dsrTubeWellDistributionBean);
			}
				
			}
		
		return dsrTubeWellDistributionBeans;
	}
	@Override
	public List<DSRTubeWellBean> findDSRTubeWell(
			DSRTubeWellForm dsrTubeWellForm, List<String> statusList)
			throws MISException {
		List<DSRTubeWellBean> dsrTubeWellBeans = new  ArrayList<DSRTubeWellBean>();
		try {
			DSRTubeWellBean dsrTubeWellBean = new DSRTubeWellBean();
			dsrTubeWellBean.setSchemeId(dsrTubeWellForm.getSchemeId());
			   dsrTubeWellBeans=dsrTubeWellDao.findDSRTubeWell(dsrTubeWellBean, statusList);
					Set<DSRTubeWellDistributionBean> dsrTubeWellDistributionBeans = null;
					Iterator<DSRTubeWellDistributionBean> dsrTubeWellDistributionIterator = null;
					DSRTubeWellDistributionBean  dsrTubeWellDistributionBean = null;
					if(!MisUtility.ifEmpty(dsrTubeWellBeans)){

						for (DSRTubeWellBean bean : dsrTubeWellBeans) {

							dsrTubeWellDistributionBeans = bean.getDsrTubeWellDistributionBeans();
							if(!MisUtility.ifEmpty(dsrTubeWellDistributionBeans)){
								dsrTubeWellDistributionIterator = dsrTubeWellDistributionBeans.iterator();					
								while(dsrTubeWellDistributionIterator.hasNext()){
									dsrTubeWellDistributionBean = dsrTubeWellDistributionIterator.next();
									if(MISConstants.MASTER_STATUS_DELETED.equals(dsrTubeWellDistributionBean.getMisAuditBean().getStatus())){
										dsrTubeWellDistributionIterator.remove();
										break;
									}
								}
							}
						}
					}

				} catch (DataAccessException e) {
					log.error(e);
					throw e;
				}
				return dsrTubeWellBeans;
	}
	@Override
	public boolean saveDSRTubeWell(DSRTubeWellForm dsrTubeWellForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			System.out.println("In  saveDSRTubeWell  ");
			DSRTubeWellBean dsrTubeWellBean = populateDSRTubeWellBean(dsrTubeWellForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			dsrTubeWellBean.setMisAuditBean(misAuditBean);
			
			String schemeId = dsrTubeWellDao.saveDSRTubeWell(dsrTubeWellBean);
		
			if(MisUtility.ifEmpty(schemeId)){
				List<DSRTubeWellDistributionBean> dsrTubeWellDistributionBeans = populateDSRTubeWellDistributionBeans(dsrTubeWellForm,schemeId,  misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(dsrTubeWellDistributionBeans)){
					boolean dsrTubeWellDistributionStatus =dsrTubeWellDistributionDao.saveOrUpdateDSRTubeWellDistribution(dsrTubeWellDistributionBeans);
					if(!dsrTubeWellDistributionStatus){
						log.error(dsrTubeWellDistributionBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to save DSR- Tubewell  Distribution details");
					}
				}
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
	public boolean updateDSRTubeWell(DSRTubeWellForm dsrTubeWellForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			System.out.println("In  updateDSRTubeWell  ");
			DSRTubeWellBean dsrTubeWellBean = populateDSRTubeWellBean(dsrTubeWellForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			dsrTubeWellBean.setMisAuditBean(misAuditBean);
			String schemeId = dsrTubeWellForm.getSchemeId();
			
			boolean status = dsrTubeWellDao.updateDSRTubeWell(dsrTubeWellBean);
		
			if(MisUtility.ifEmpty(status)){
				List<DSRTubeWellDistributionBean> dsrTubeWellDistributionBeans = populateDSRTubeWellDistributionBeans(dsrTubeWellForm,schemeId,  misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(dsrTubeWellDistributionBeans)){
					boolean dsrTubeWellDistributionStatus =dsrTubeWellDistributionDao.saveOrUpdateDSRTubeWellDistribution(dsrTubeWellDistributionBeans);
					if(!dsrTubeWellDistributionStatus){
						log.error(dsrTubeWellDistributionBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Update DSR- Tubewell  Distribution details");
					}
				}
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
	public boolean deleteDSRTubeWell(DSRTubeWellForm dsrTubeWellForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			System.out.println("In  DeleteDSRTubeWell  ");
			DSRTubeWellBean dsrTubeWellBean = populateDSRTubeWellBean(dsrTubeWellForm);
			
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			dsrTubeWellBean.setMisAuditBean(misAuditBean);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		
			String schemeId = dsrTubeWellForm.getSchemeId();
			boolean status = dsrTubeWellDao.updateDSRTubeWell(dsrTubeWellBean);
			DSRTubeWellDistributionBean dsrTubeWellDistributionBean = new DSRTubeWellDistributionBean();
			dsrTubeWellDistributionBean.setSchemeId(schemeId);
		
			if(status){
				List<DSRTubeWellDistributionBean> dsrTubeWellDistributionBeans = dsrTubeWellDistributionDao.findDSRTubeWellDistribution(dsrTubeWellDistributionBean, statusList);
					
				if(!MisUtility.ifEmpty(dsrTubeWellDistributionBeans)){
					for (DSRTubeWellDistributionBean dsrTubeWellDistributionBean2 : dsrTubeWellDistributionBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
						dsrTubeWellDistributionBean2.setMisAuditBean(misAuditBean);
					}
					boolean dsrDSRTubeWellDistributionStatus = dsrTubeWellDistributionDao.saveOrUpdateDSRTubeWellDistribution(dsrTubeWellDistributionBeans);
					if(!dsrDSRTubeWellDistributionStatus){
						log.error(dsrTubeWellDistributionBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to delete DSR TubeWell distribution details");
					}
				}
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
	public boolean postDSRTubeWell(DSRTubeWellForm dsrTubeWellForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			System.out.println("In  Post DSRTubeWell  ");
			DSRTubeWellBean dsrTubeWellBean = populateDSRTubeWellBean(dsrTubeWellForm);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			dsrTubeWellBean = dsrTubeWellDao.findDSRTubeWell(dsrTubeWellBean, statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			dsrTubeWellBean.setMisAuditBean(misAuditBean);
		
			String schemeId = dsrTubeWellForm.getSchemeId();
			boolean status = dsrTubeWellDao.updateDSRTubeWell(dsrTubeWellBean);
			DSRTubeWellDistributionBean dsrTubeWellDistributionBean = new DSRTubeWellDistributionBean();
			dsrTubeWellDistributionBean.setSchemeId(schemeId);
		
			if(status){
				List<DSRTubeWellDistributionBean> dsrTubeWellDistributionBeans = dsrTubeWellDistributionDao.findDSRTubeWellDistribution(dsrTubeWellDistributionBean, statusList);
					
				if(!MisUtility.ifEmpty(dsrTubeWellDistributionBeans)){
					for (DSRTubeWellDistributionBean dsrTubeWellDistributionBean2 : dsrTubeWellDistributionBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
						dsrTubeWellDistributionBean2.setMisAuditBean(misAuditBean);
					}
					boolean dsrDSRTubeWellDistributionStatus = dsrTubeWellDistributionDao.saveOrUpdateDSRTubeWellDistribution(dsrTubeWellDistributionBeans);
					if(!dsrDSRTubeWellDistributionStatus){
						log.error(dsrTubeWellDistributionBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Post DSR TubeWell distribution details");
					}
				}
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
	private DSRTubeWellBean populateDSRTubeWellBean(DSRTubeWellForm dsrTubeWellForm){

		DSRTubeWellBean dsrTubeWellBean = new DSRTubeWellBean();
		dsrTubeWellBean.setBulkCount(dsrTubeWellForm.getBulkCount());
		dsrTubeWellBean.setSchemeId(dsrTubeWellForm.getSchemeId());
		dsrTubeWellBean.setLocationId(dsrTubeWellForm.getLocationId());
		dsrTubeWellBean.setTubewellSize(dsrTubeWellForm.getTubewellSize());
		dsrTubeWellBean.setTubewellDepth(dsrTubeWellForm.getTubewellDepth());
		dsrTubeWellBean.setTubewellCost(dsrTubeWellForm.getTubewellCost());
		dsrTubeWellBean.setPumpSelect(dsrTubeWellForm.getPumpSelect());
		dsrTubeWellBean.setPumpQuantity(dsrTubeWellForm.getPumpQuantity());
		dsrTubeWellBean.setPumpTotalCost(dsrTubeWellForm.getPumpTotalCost());
		dsrTubeWellBean.setPumpingCentrifugal(dsrTubeWellForm.getPumpingCentrifugal());
		dsrTubeWellBean.setPumpingDischarge(dsrTubeWellForm.getPumpingDischarge());
		dsrTubeWellBean.setPumpingSubmersible(dsrTubeWellForm.getPumpingSubmersible());
		dsrTubeWellBean.setPumpingQuantity(dsrTubeWellForm.getPumpingQuantity());
		dsrTubeWellBean.setPumpingHead(dsrTubeWellForm.getPumpingHead());
		dsrTubeWellBean.setPumpingCost(dsrTubeWellForm.getPumpingCost());
		dsrTubeWellBean.setCostOfElectricConnection(dsrTubeWellForm.getCostOfElectricConnection());
		dsrTubeWellBean.setCostOfCIFitting(dsrTubeWellForm.getCostOfCIFitting());
		dsrTubeWellBean.setVnotchQuantity(dsrTubeWellForm.getVnotchQuantity());
		dsrTubeWellBean.setVnotchCost(dsrTubeWellForm.getVnotchCost());								
		dsrTubeWellBean.setDisinfectionType(dsrTubeWellForm.getDisinfectionType());
		dsrTubeWellBean.setDisinfectionQuantity(dsrTubeWellForm.getDisinfectionQuantity());
		dsrTubeWellBean.setDisinfectionCost(dsrTubeWellForm.getDisinfectionCost());
		dsrTubeWellBean.setRccQuantity(dsrTubeWellForm.getRccQuantity());
		dsrTubeWellBean.setRccCapacity(dsrTubeWellForm.getRccCapacity());
		dsrTubeWellBean.setRccFsl(dsrTubeWellForm.getRccFsl());
		dsrTubeWellBean.setRccCost(dsrTubeWellForm.getRccCost());
		dsrTubeWellBean.setElectronicCost(dsrTubeWellForm.getElectronicCost());
		dsrTubeWellBean.setElectronicQuantity(dsrTubeWellForm.getElectronicQuantity());
		dsrTubeWellBean.setPlinthCost(dsrTubeWellForm.getPlinthCost());
		dsrTubeWellBean.setRisingSize(dsrTubeWellForm.getRisingSize());
		dsrTubeWellBean.setRisingLength(dsrTubeWellForm.getRisingLength());
		dsrTubeWellBean.setRisingCost(dsrTubeWellForm.getRisingCost());
		dsrTubeWellBean.setRisingType(dsrTubeWellForm.getRisingType());
		dsrTubeWellBean.setTotalCostTopographical(dsrTubeWellForm.getTotalCostTopographical());
		dsrTubeWellBean.setTotalCostDevelopmentWater(dsrTubeWellForm.getTotalCostDevelopmentWater());
		dsrTubeWellBean.setBulkSize(dsrTubeWellForm.getBulkSize());
		dsrTubeWellBean.setBulkCount(dsrTubeWellForm.getBulkCount());
		dsrTubeWellBean.setBulkTotalCost(dsrTubeWellForm.getBulkTotalCost());
		dsrTubeWellBean.setDistributionTotalCost(dsrTubeWellForm.getDistributionTotalCost());
		dsrTubeWellBean.setSlueiceSize(dsrTubeWellForm.getSlueiceSize());
		dsrTubeWellBean.setTotalCostStructure(dsrTubeWellForm.getTotalCostStructure());
		dsrTubeWellBean.setPercapitaCost(dsrTubeWellForm.getPercapitaCost());
		dsrTubeWellBean.setEnvironmentCost(dsrTubeWellForm.getEnvironmentCost());
		dsrTubeWellBean.setGrandTotal(dsrTubeWellForm.getGrandTotal());
		dsrTubeWellBean.setSlueiceCost(dsrTubeWellForm.getSlueiceCost());
		dsrTubeWellBean.setVillageId(dsrTubeWellForm.getVillageId());
		dsrTubeWellBean.setDsrDate(MisUtility.convertStringToDate(dsrTubeWellForm.getDsrDate()));
		dsrTubeWellBean.setTubewellSize1(dsrTubeWellForm.getTubewellSize1());
		dsrTubeWellBean.setTubewellDepth1(dsrTubeWellForm.getTubewellDepth1());
		dsrTubeWellBean.setTubewellCost1(dsrTubeWellForm.getTubewellCost1());
		dsrTubeWellBean.setPumpingCentrifugal1(dsrTubeWellForm.getPumpingCentrifugal1());
		dsrTubeWellBean.setPumpingDischarge1(dsrTubeWellForm.getPumpingDischarge1());
		dsrTubeWellBean.setPumpingSubmersible1(dsrTubeWellForm.getPumpingSubmersible1());
		dsrTubeWellBean.setPumpingQuantity1(dsrTubeWellForm.getPumpingQuantity1());
		dsrTubeWellBean.setPumpingHead1(dsrTubeWellForm.getPumpingHead1());
		dsrTubeWellBean.setPumpingCost1(dsrTubeWellForm.getPumpingCost1());
		dsrTubeWellBean.setCostOfElectricConnection1(dsrTubeWellForm.getCostOfElectricConnection1());
		dsrTubeWellBean.setCostOfCIFitting1(dsrTubeWellForm.getCostOfCIFitting1());
		dsrTubeWellBean.setVnotchQuantity1(dsrTubeWellForm.getVnotchQuantity1());
		dsrTubeWellBean.setVnotchCost1(dsrTubeWellForm.getVnotchCost1());	
		dsrTubeWellBean.setRccQuantity1(dsrTubeWellForm.getRccQuantity1());
		dsrTubeWellBean.setRccCapacity1(dsrTubeWellForm.getRccCapacity1());
		dsrTubeWellBean.setRccFsl1(dsrTubeWellForm.getRccFsl1());
		dsrTubeWellBean.setRccCost1(dsrTubeWellForm.getRccCost1());
		dsrTubeWellBean.setElectronicCost1(dsrTubeWellForm.getElectronicCost1());
		dsrTubeWellBean.setPlinthCost1(dsrTubeWellForm.getPlinthCost1());
		dsrTubeWellBean.setElectronicQuantity1(dsrTubeWellForm.getElectronicQuantity1());
		dsrTubeWellBean.setRisingSize1(dsrTubeWellForm.getRisingSize1());
		dsrTubeWellBean.setRisingLength1(dsrTubeWellForm.getRisingLength1());
		dsrTubeWellBean.setRisingCost1(dsrTubeWellForm.getRisingCost1());
		dsrTubeWellBean.setRisingType1(dsrTubeWellForm.getRisingType1());
		dsrTubeWellBean.setTotalCostTopographical1(dsrTubeWellForm.getTotalCostTopographical1());
		dsrTubeWellBean.setTotalCostDevelopmentWater1(dsrTubeWellForm.getTotalCostDevelopmentWater1());
		
		

		return dsrTubeWellBean;
	}
	
	
	@Override
	public DSRTubeWellBean validateDSRTubeWellBean(
			DSRTubeWellForm dsrTubeWellForm) {
		DSRTubeWellBean obj = null;
		List<String> schemeIds = new ArrayList<String>();
		List<DSRTubeWellBean> wellBeans = null;
		System.out.println("----------------Scheme Id is"+dsrTubeWellForm.getSchemeId());
		schemeIds.add(dsrTubeWellForm.getSchemeId());
		try{
			wellBeans = dsrTubeWellDao.findDSRTubeWell(schemeIds);
			obj = wellBeans.get(0);
		}
		catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}		
		return obj;
	}
}
