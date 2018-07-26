/**
 * 
 */
package com.prwss.mis.pmm.dsrcanal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
import com.prwss.mis.pmm.dsrcanal.dao.DSRCanalDao;
import com.prwss.mis.pmm.dsrcanal.dao.DSRCanalDistributionBean;
import com.prwss.mis.pmm.dsrcanal.dao.DSRCanalDistributionDao;
import com.prwss.mis.pmm.dsrcanal.dao.DSRCanalInletBean;
import com.prwss.mis.pmm.dsrcanal.dao.DSRCanalInletDao;
import com.prwss.mis.pmm.dsrcanal.struts.DSRCanalForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author pjha
 *
 */
public class DSRCanalBOImpl implements DSRCanalBO {
	private Logger log = Logger.getLogger(DSRCanalBOImpl.class);
	private DSRCanalDao dsrCanalDao ;
	private DSRCanalDistributionDao dsrCanalDistributionDao;
	private DSRCanalInletDao dsrCanalInletDao;
	
	
	public void setDsrCanalDao(DSRCanalDao dsrCanalDao) {
		this.dsrCanalDao = dsrCanalDao;
	}

	public void setDsrCanalDistributionDao(
			DSRCanalDistributionDao dsrCanalDistributionDao) {
		this.dsrCanalDistributionDao = dsrCanalDistributionDao;
	}

	public void setDsrCanalInletDao(DSRCanalInletDao dsrCanalInletDao) {
		this.dsrCanalInletDao = dsrCanalInletDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<DSRCanalBean> findDSRCanal(DSRCanalForm dsrCanalForm,
			List<String> statusList) throws MISException {
		List<DSRCanalBean> dsrCanalBeans = new  ArrayList<DSRCanalBean>();
		try {
			System.out.println("In Find DSR Canel BO");
			DSRCanalBean dsrCanalBean = new DSRCanalBean();
			dsrCanalBean.setSchemeId(dsrCanalForm.getSchemeId());
			dsrCanalBeans= dsrCanalDao.findDSRCanal(dsrCanalBean, statusList);
			Set<DSRCanalDistributionBean> dsrCanalDistributionBeans = null;
			Iterator<DSRCanalDistributionBean> dsrCanalDistributionIterator = null;
			DSRCanalDistributionBean  dsrCanalDistributionBean = null;
			if(!MisUtility.ifEmpty(dsrCanalBeans)){

				for (DSRCanalBean bean : dsrCanalBeans) {
					dsrCanalDistributionBeans = bean.getDsrCanalDistributionBeans();
					if(!MisUtility.ifEmpty(dsrCanalDistributionBeans)){
						dsrCanalDistributionIterator = dsrCanalDistributionBeans.iterator();					
						while(dsrCanalDistributionIterator.hasNext()){
							dsrCanalDistributionBean = dsrCanalDistributionIterator.next();
							if(MISConstants.MASTER_STATUS_DELETED.equals(dsrCanalDistributionBean.getMisAuditBean().getStatus())){
								dsrCanalDistributionIterator.remove();
								break;
							}
						}
					}
				}
			}
			Set<DSRCanalInletBean> dsrCanalInletBeans = null;
			Iterator<DSRCanalInletBean> iteratorDSRCanalInletBean = null;
			DSRCanalInletBean  dsrCanalInletBean = null;
			if(!MisUtility.ifEmpty(dsrCanalInletBeans)){

				for (DSRCanalBean bean : dsrCanalBeans) {
					dsrCanalInletBeans = bean.getDsrCanalInletBeans();
					if(!MisUtility.ifEmpty(dsrCanalInletBeans)){
						iteratorDSRCanalInletBean = dsrCanalInletBeans.iterator();					
						while(iteratorDSRCanalInletBean.hasNext()){
							dsrCanalInletBean = iteratorDSRCanalInletBean.next();
							if(MISConstants.MASTER_STATUS_DELETED.equals(dsrCanalInletBean.getMisAuditBean().getStatus())){
								iteratorDSRCanalInletBean.remove();
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
		return dsrCanalBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveDSRCanal(DSRCanalForm dsrCanalForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			System.out.println("In saveDSRCanal +++++++++++++++++++++++ ");
			DSRCanalBean dsrCanalBean = populateDSRCanalBean(dsrCanalForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			dsrCanalBean.setMisAuditBean(misAuditBean);
			String schemeId = dsrCanalDao.saveDSRCanal(dsrCanalBean);
		
			if(MisUtility.ifEmpty(schemeId)){
				List<DSRCanalDistributionBean> dsrCanalDistributionBeans = populateDSRCanalDistributionBeans(dsrCanalForm,schemeId,  misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(dsrCanalDistributionBeans)){
					boolean dsrCanalDistributionStatus = dsrCanalDistributionDao.saveOrUpdateDSRCanalDistribution(dsrCanalDistributionBeans);
					if(!dsrCanalDistributionStatus){
						log.error(dsrCanalDistributionBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save DSR Canal distribution details");
					}
				}

				List<DSRCanalInletBean> dsrCanalInletBeans = populateDSRCanalInletBeans(dsrCanalForm,schemeId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(dsrCanalInletBeans)){
					boolean dsrCanalInletStatus =dsrCanalInletDao.saveOrUpdateDSRCanalDistribution(dsrCanalInletBeans);
					if(!dsrCanalInletStatus){
						log.error(dsrCanalInletBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to DSR Canal Inlet details");
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
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateDSRCanal(DSRCanalForm dsrCanalForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			System.out.println("In update DSRCanal  ");
			DSRCanalBean dsrCanalBean = populateDSRCanalBean(dsrCanalForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			dsrCanalBean.setMisAuditBean(misAuditBean);

			boolean status  = dsrCanalDao.updateDSRCanal(dsrCanalBean);
			System.out.println("After update of DSRCanalBean");
			String schemeId = dsrCanalForm.getSchemeId();
		
			if(MisUtility.ifEmpty(status)){
				List<DSRCanalDistributionBean> dsrCanalDistributionBeans = populateDSRCanalDistributionBeans(dsrCanalForm,schemeId,  misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(dsrCanalDistributionBeans)){
					boolean dsrCanalDistributionStatus = dsrCanalDistributionDao.saveOrUpdateDSRCanalDistribution(dsrCanalDistributionBeans);
					if(!dsrCanalDistributionStatus){
						log.error(dsrCanalDistributionBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to update DSR Canal distribution details");
					}
				}

				List<DSRCanalInletBean> dsrCanalInletBeans = populateDSRCanalInletBeans(dsrCanalForm,schemeId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(dsrCanalInletBeans)){
					boolean dsrCanalInletStatus =dsrCanalInletDao.saveOrUpdateDSRCanalDistribution(dsrCanalInletBeans);
					if(!dsrCanalInletStatus){
						log.error(dsrCanalInletBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to update Canal Inlet details");
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
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteDSRCanal(DSRCanalForm dsrCanalForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			System.out.println("In delete DSRCanal  ");
			DSRCanalBean dsrCanalBean = populateDSRCanalBean(dsrCanalForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			dsrCanalBean.setMisAuditBean(misAuditBean);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			boolean status  = dsrCanalDao.updateDSRCanal(dsrCanalBean);
			
			String schemeId  =dsrCanalForm.getSchemeId();

			DSRCanalDistributionBean dsrCanalDistributionBean = new DSRCanalDistributionBean();
			dsrCanalDistributionBean.setSchemeId(schemeId);

			if(status){
				List<DSRCanalDistributionBean> dsrCanalDistributionBeans = dsrCanalDistributionDao.findDSRCanalDistribution(dsrCanalDistributionBean, statusList);
				if(!MisUtility.ifEmpty(dsrCanalDistributionBeans)){
					for (DSRCanalDistributionBean dsrCanalDistributionBean2 : dsrCanalDistributionBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
						dsrCanalDistributionBean2.setMisAuditBean(misAuditBean);
					}
					boolean dsrCanalDistributionStatus = dsrCanalDistributionDao.saveOrUpdateDSRCanalDistribution(dsrCanalDistributionBeans);
					if(!dsrCanalDistributionStatus){
						log.error(dsrCanalDistributionBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to delete DSR Canal distributions details");
					}
				}

				DSRCanalInletBean dsrCanalInletBean = new DSRCanalInletBean();
				dsrCanalInletBean.setSchemeId(schemeId);

				List<DSRCanalInletBean> dsrCanalInletBeans = dsrCanalInletDao.findDSRCanalDistribution(dsrCanalInletBean, statusList);
				if(!MisUtility.ifEmpty(dsrCanalInletBeans)){

					for (DSRCanalInletBean dsrCanalInletBean2 : dsrCanalInletBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
						dsrCanalInletBean2.setMisAuditBean(misAuditBean);
					}
					boolean dsrCanalInletStatus =dsrCanalInletDao.saveOrUpdateDSRCanalDistribution(dsrCanalInletBeans);
					if(!dsrCanalInletStatus){
						log.error(dsrCanalInletBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to delete  DSR Canal Inlet details");
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
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postDSRCanal(DSRCanalForm dsrCanalForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			System.out.println("In Post DSRCanal  ");
			DSRCanalBean dsrCanalBean = populateDSRCanalBean(dsrCanalForm);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			dsrCanalBean = dsrCanalDao.findDSRCanal(dsrCanalBean, statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			dsrCanalBean.setMisAuditBean(misAuditBean);
			
			boolean status  = dsrCanalDao.updateDSRCanal(dsrCanalBean);
			
			String schemeId  =dsrCanalForm.getSchemeId();

			DSRCanalDistributionBean dsrCanalDistributionBean = new DSRCanalDistributionBean();
			dsrCanalDistributionBean.setSchemeId(schemeId);

			if(status){
				List<DSRCanalDistributionBean> dsrCanalDistributionBeans = dsrCanalDistributionDao.findDSRCanalDistribution(dsrCanalDistributionBean, statusList);
				if(!MisUtility.ifEmpty(dsrCanalDistributionBeans)){
					for (DSRCanalDistributionBean dsrCanalDistributionBean2 : dsrCanalDistributionBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
						dsrCanalDistributionBean2.setMisAuditBean(misAuditBean);
					}
					boolean dsrCanalDistributionStatus = dsrCanalDistributionDao.saveOrUpdateDSRCanalDistribution(dsrCanalDistributionBeans);
					if(!dsrCanalDistributionStatus){
						log.error(dsrCanalDistributionBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to post DSR Canal distributions details");
					}
				}

				DSRCanalInletBean dsrCanalInletBean = new DSRCanalInletBean();
				dsrCanalInletBean.setSchemeId(schemeId);

				List<DSRCanalInletBean> dsrCanalInletBeans = dsrCanalInletDao.findDSRCanalDistribution(dsrCanalInletBean, statusList);
				if(!MisUtility.ifEmpty(dsrCanalInletBeans)){

					for (DSRCanalInletBean dsrCanalInletBean2 : dsrCanalInletBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
						dsrCanalInletBean2.setMisAuditBean(misAuditBean);
					}
					boolean dsrCanalInletStatus =dsrCanalInletDao.saveOrUpdateDSRCanalDistribution(dsrCanalInletBeans);
					if(!dsrCanalInletStatus){
						log.error(dsrCanalInletBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to post  DSR Canal Inlet details");
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
	private DSRCanalBean populateDSRCanalBean(DSRCanalForm dsrCanalForm){
		
		DSRCanalBean dsrCanalBean = new DSRCanalBean();
				
		dsrCanalBean.setBulkCount(dsrCanalForm.getBulkCount());
		dsrCanalBean.setBulkSize(dsrCanalForm.getBulkSize());
		dsrCanalBean.setBulkTotalCost(dsrCanalForm.getBulkTotalCost());
		dsrCanalBean.setClearCost(dsrCanalForm.getClearCost());
		dsrCanalBean.setClearQuantity(dsrCanalForm.getClearQuantity());
		dsrCanalBean.setClearType(dsrCanalForm.getClearType());
		dsrCanalBean.setDsrDate(MisUtility.convertStringToDate(dsrCanalForm.getDsrDate()));
		dsrCanalBean.setVillageId(dsrCanalForm.getVillageId());
		dsrCanalBean.setContigencyCharges(dsrCanalForm.getContigencyCharges());
		dsrCanalBean.setCostOfElectricConnectionProvision(dsrCanalForm.getCostOfElectricConnectionProvision());
		dsrCanalBean.setDisinfectionCost(dsrCanalForm.getDisinfectionCost());
		dsrCanalBean.setDisinfectionQuantity(dsrCanalForm.getDisinfectionQuantity());
		dsrCanalBean.setDisinfectionType(dsrCanalForm.getDisinfectionType());
		dsrCanalBean.setDistributionCost(dsrCanalForm.getDistributionCost());
		dsrCanalBean.setElectronicCost(dsrCanalForm.getElectronicCost());
		dsrCanalBean.setElectronicQuantity(dsrCanalForm.getElectronicQuantity());
		dsrCanalBean.setEnvironmentCost(dsrCanalForm.getEnvironmentCost());
		dsrCanalBean.setFilterCost(dsrCanalForm.getFilterCost());
		dsrCanalBean.setFilterQuantity(dsrCanalForm.getFilterQuantity());
		dsrCanalBean.setFilterSize(dsrCanalForm.getFilterSize());
		dsrCanalBean.setFilterType(dsrCanalForm.getFilterType());
		dsrCanalBean.setGrandTotal(dsrCanalForm.getGrandTotal());
		dsrCanalBean.setHighCost(dsrCanalForm.getHighCost());
		dsrCanalBean.setHighQuantity(dsrCanalForm.getHighQuantity());
		dsrCanalBean.setHighSize(dsrCanalForm.getHighSize());
		dsrCanalBean.setHighType(dsrCanalForm.getHighType());
		dsrCanalBean.setInletTotalCost(dsrCanalForm.getInletTotalCost());
		dsrCanalBean.setLocationId(dsrCanalForm.getLocationId());
		dsrCanalBean.setPercapitaCost(dsrCanalForm.getPercapitaCost());
		dsrCanalBean.setPiplineCost(dsrCanalForm.getPiplineCost());
		dsrCanalBean.setPiplineLength(dsrCanalForm.getPiplineLength());
		dsrCanalBean.setPiplineSize(dsrCanalForm.getPiplineSize());
		dsrCanalBean.setPiplineType(dsrCanalForm.getPiplineType());
		dsrCanalBean.setPlinthCost(dsrCanalForm.getPlinthCost());
		dsrCanalBean.setPumpCost(dsrCanalForm.getPumpCost());
		dsrCanalBean.setPumpingCentrifugal(dsrCanalForm.getPumpingCentrifugal());
		dsrCanalBean.setPumpingCost(dsrCanalForm.getPumpingCost());
		dsrCanalBean.setPumpingDischarge(dsrCanalForm.getPumpingDischarge());
		dsrCanalBean.setPumpingHead(dsrCanalForm.getPumpingHead());
		dsrCanalBean.setPumpingMachineryApplicable(dsrCanalForm.isPumpingMachineryApplicable());
		dsrCanalBean.setPumpingMachineryCost(dsrCanalForm.getPumpingMachineryCost());
		dsrCanalBean.setPumpingMachineryDischarge(dsrCanalForm.getPumpingMachineryDischarge());
		dsrCanalBean.setPumpingMachinerySize(dsrCanalForm.getPumpingMachinerySize());
		dsrCanalBean.setPumpingPower(dsrCanalForm.getPumpingPower());
		dsrCanalBean.setPumpingQuantity(dsrCanalForm.getPumpingQuantity());
		dsrCanalBean.setPumpingSubmersible(dsrCanalForm.getPumpingSubmersible());
		dsrCanalBean.setPumpQuantity(dsrCanalForm.getPumpQuantity());
		dsrCanalBean.setPumpSelect(dsrCanalForm.getPumpSelect());
		dsrCanalBean.setRccCapacity(dsrCanalForm.getRccCapacity());
		dsrCanalBean.setRccCost(dsrCanalForm.getRccCost());
		dsrCanalBean.setRccFsl(dsrCanalForm.getRccFsl());
		dsrCanalBean.setRccQuantity(dsrCanalForm.getRccQuantity());
		dsrCanalBean.setSchemeId(dsrCanalForm.getSchemeId());
		dsrCanalBean.setSlueiceCost(dsrCanalForm.getSlueiceCost());
		dsrCanalBean.setSlueiceSize(dsrCanalForm.getSlueiceSize());
		dsrCanalBean.setStorageCost(dsrCanalForm.getStorageCost());
		dsrCanalBean.setStorageDepth(dsrCanalForm.getStorageDepth());
		dsrCanalBean.setStorageQuantity(dsrCanalForm.getStorageQuantity());
		dsrCanalBean.setStorageSize(dsrCanalForm.getStorageSize());
		dsrCanalBean.setSuctionCost(dsrCanalForm.getSuctionCost());
		dsrCanalBean.setSuctionDimeter(dsrCanalForm.getSuctionDimeter());
		dsrCanalBean.setSuctionQuantity(dsrCanalForm.getSuctionQuantity());
		dsrCanalBean.setClearSize(dsrCanalForm.getClearSize());
		
		dsrCanalBean.setRccCapacity1(dsrCanalForm.getRccCapacity1());
		dsrCanalBean.setRccCost1(dsrCanalForm.getRccCost1());
		dsrCanalBean.setRccFsl1(dsrCanalForm.getRccFsl1());
		dsrCanalBean.setRccQuantity1(dsrCanalForm.getRccQuantity1());
		dsrCanalBean.setPumpCost1(dsrCanalForm.getPumpCost1());
		dsrCanalBean.setPumpingCentrifugal1(dsrCanalForm.getPumpingCentrifugal1());
		dsrCanalBean.setPumpingDischarge1(dsrCanalForm.getPumpingDischarge1());
		dsrCanalBean.setPumpingHead1(dsrCanalForm.getPumpingHead1());
		dsrCanalBean.setPumpingSubmersible1(dsrCanalForm.getPumpingSubmersible1());
		dsrCanalBean.setPumpQuantity1(dsrCanalForm.getPumpQuantity1());
			
			return dsrCanalBean;
		}
	@SuppressWarnings({ "unchecked" })
	private List<DSRCanalDistributionBean> populateDSRCanalDistributionBeans(DSRCanalForm dsrCanalForm,String schemeId,  MISSessionBean misSessionBean, String status){
		
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);

		List<DSRCanalDistributionBean> dsrCanalDistributionBeans = new ArrayList<DSRCanalDistributionBean>();


		Datagrid dsrCanalDistributionGrid = dsrCanalForm.getDsrCanalDistributionDatagrid();

		Collection<DSRCanalDistributionBean> addedDsrCanalDistributionBeans = dsrCanalDistributionGrid.getAddedData();
		if(!MisUtility.ifEmpty(addedDsrCanalDistributionBeans)){
			for (DSRCanalDistributionBean dsrCanalDistributionBean : addedDsrCanalDistributionBeans) {
				dsrCanalDistributionBean.setSchemeId(schemeId);
				dsrCanalDistributionBean.setMisAuditBean(misAuditBean);
				dsrCanalDistributionBeans.add(dsrCanalDistributionBean);
			}
		}

		Collection<DSRCanalDistributionBean> modifiedDsrCanalDistributionBeans = dsrCanalDistributionGrid.getModifiedData();
		if(!MisUtility.ifEmpty(modifiedDsrCanalDistributionBeans)){
			for (DSRCanalDistributionBean dsrCanalDistributionBean : modifiedDsrCanalDistributionBeans) {
				dsrCanalDistributionBean.setSchemeId(schemeId);
				dsrCanalDistributionBean.setMisAuditBean(misAuditBean);
				dsrCanalDistributionBeans.add(dsrCanalDistributionBean);
			}
				
			}
	
		
		Collection<DSRCanalDistributionBean> deletedDsrCanalDistributionBeans = dsrCanalDistributionGrid.getDeletedData();
		if(!MisUtility.ifEmpty(deletedDsrCanalDistributionBeans)){
			for (DSRCanalDistributionBean dsrCanalDistributionBean : deletedDsrCanalDistributionBeans) {
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				dsrCanalDistributionBean.setSchemeId(schemeId);
				dsrCanalDistributionBean.setMisAuditBean(misAuditBean);
				dsrCanalDistributionBeans.add(dsrCanalDistributionBean);
			}
				
			}
		
		return dsrCanalDistributionBeans;
	}
	
	@SuppressWarnings("unchecked")
	private List<DSRCanalInletBean> populateDSRCanalInletBeans(DSRCanalForm dsrCanalForm,String schemeId,  MISSessionBean misSessionBean, String status){
		
		List<DSRCanalInletBean> dsrCanalInletBeans = new ArrayList<DSRCanalInletBean>();
	
		
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
		
		Datagrid dsrCanalInletGrid = dsrCanalForm.getDsrCanalInletDatagrid();
		
		Collection<DSRCanalInletBean> addedDSRCanalInletGridBeans = dsrCanalInletGrid.getAddedData();
		if(!MisUtility.ifEmpty(addedDSRCanalInletGridBeans)){
			for (DSRCanalInletBean dsrCanalInletBean : addedDSRCanalInletGridBeans) {
				dsrCanalInletBean.setMisAuditBean(misAuditBean);
				dsrCanalInletBean.setSchemeId(schemeId);
				dsrCanalInletBeans.add(dsrCanalInletBean);
				
				
			}
		}
		
		Collection<DSRCanalInletBean> modifiedDSRCanalInletGridBeans = dsrCanalInletGrid.getModifiedData();
		if(!MisUtility.ifEmpty(modifiedDSRCanalInletGridBeans)){
			for (DSRCanalInletBean dsrCanalInletBean : modifiedDSRCanalInletGridBeans) {
				dsrCanalInletBean.setMisAuditBean(misAuditBean);
				dsrCanalInletBean.setSchemeId(schemeId);
				dsrCanalInletBeans.add(dsrCanalInletBean);
				
			}
		}
		
		Collection<DSRCanalInletBean> deletedDSRCanalInletGridBeans= dsrCanalInletGrid.getDeletedData();
		if(!MisUtility.ifEmpty(deletedDSRCanalInletGridBeans)){
			for (DSRCanalInletBean dsrCanalInletBean : deletedDSRCanalInletGridBeans) {
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				dsrCanalInletBean.setMisAuditBean(misAuditBean);
				dsrCanalInletBean.setSchemeId(schemeId);
				dsrCanalInletBeans.add(dsrCanalInletBean);
			}
		}
		
		return dsrCanalInletBeans;
	}

	@Override
	public DSRCanalBean validateDSRCanalBean(DSRCanalForm dsrCanalForm){
		DSRCanalBean obj = null;
		List<String> schemeIds = new ArrayList<String>();
		List<DSRCanalBean> canalBeans = null;
		schemeIds.add(dsrCanalForm.getSchemeId());
		try{
			canalBeans = dsrCanalDao.findDSRCanal(schemeIds);
			obj = canalBeans.get(0);
		}
		catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}		
		return obj;
	}
	
}
