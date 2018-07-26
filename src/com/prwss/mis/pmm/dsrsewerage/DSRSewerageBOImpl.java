/**
 * 
 */
package com.prwss.mis.pmm.dsrsewerage;

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
import com.prwss.mis.pmm.dsrsewerage.dao.DSRSewerageDao;
import com.prwss.mis.pmm.dsrsewerage.dao.DSRSewerageSewerBean;
import com.prwss.mis.pmm.dsrsewerage.dao.DSRSewerageSewerDao;
import com.prwss.mis.pmm.dsrsewerage.struts.DSRSewerageForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author pjha
 *
 */
public class DSRSewerageBOImpl implements DSRSewerageBO{
	
	private Logger log = Logger.getLogger(DSRSewerageBOImpl.class);
	private DSRSewerageDao dsrSewerageDao ;
	private DSRSewerageSewerDao dsrSewerageSewerDao;

	public void setDsrSewerageDao(DSRSewerageDao dsrSewerageDao) {
		this.dsrSewerageDao = dsrSewerageDao;
	}
	public void setDsrSewerageSewerDao(DSRSewerageSewerDao dsrSewerageSewerDao) {
		this.dsrSewerageSewerDao = dsrSewerageSewerDao;
	}
	private DSRSewerageBean populateDSRSewerageBean(DSRSewerageForm dsrSewerageForm){

		DSRSewerageBean dsrSewerageBean = new DSRSewerageBean();
		dsrSewerageBean.setCollectionIpsCost(dsrSewerageForm.getCollectionIpsCost());
		dsrSewerageBean.setCollectionIpsDimeter(dsrSewerageForm.getCollectionIpsDimeter());
		dsrSewerageBean.setCollectionIpsQuantity(dsrSewerageForm.getCollectionIpsQuantity());
		dsrSewerageBean.setCollectionStpCost(dsrSewerageForm.getCollectionStpCost());
		dsrSewerageBean.setCollectionStpDiameter(dsrSewerageForm.getCollectionStpDiameter());
		dsrSewerageBean.setCompositionCost(dsrSewerageForm.getCompositionCost());
		dsrSewerageBean.setContigencyCharges(dsrSewerageForm.getContigencyCharges());
//		dsrSewerageBean.setDsrSewerageSewerBeans(dsrSewerageForm.getDsrSewerageSewerBeans());
		dsrSewerageBean.setEnvironmentCost(dsrSewerageForm.getEnvironmentCost());
		dsrSewerageBean.setGensetCapacity(dsrSewerageForm.getGensetCapacity());
		dsrSewerageBean.setGensetElectricConnectionCost(dsrSewerageForm.getGensetElectricConnectionCost());
		dsrSewerageBean.setGensetElectricConnectionLoad(dsrSewerageForm.getGensetElectricConnectionLoad());
		dsrSewerageBean.setGensetPannelCost(dsrSewerageForm.getGensetPannelCost());
		dsrSewerageBean.setGensetPannelQuantity(dsrSewerageForm.getGensetPannelQuantity());
		dsrSewerageBean.setGensetQuantity(dsrSewerageForm.getGensetQuantity());
		dsrSewerageBean.setGrandTotal(dsrSewerageForm.getGrandTotal());
		dsrSewerageBean.setLocationId(dsrSewerageForm.getLocationId());
		dsrSewerageBean.setMachineryCost(dsrSewerageForm.getMachineryCost());
		dsrSewerageBean.setMachineryDischarge(dsrSewerageForm.getMachineryDischarge());
		dsrSewerageBean.setMachineryHead(dsrSewerageForm.getMachineryHead());
		dsrSewerageBean.setMachineryQuantity(dsrSewerageForm.getMachineryQuantity());
		dsrSewerageBean.setMachineryType(dsrSewerageForm.getMachineryType());
		dsrSewerageBean.setManholeDepthMax(dsrSewerageForm.getManholeDepthMax());
		dsrSewerageBean.setManholeDepthMin(dsrSewerageForm.getManholeDepthMin());
		dsrSewerageBean.setManholeQuantity(dsrSewerageForm.getManholeQuantity());
		dsrSewerageBean.setManholeSize(dsrSewerageForm.getManholeSize());
		dsrSewerageBean.setManholeCost(dsrSewerageForm.getManholeCost());
		dsrSewerageBean.setOMcostForSevenYears(dsrSewerageForm.getOMcostForSevenYears());
		dsrSewerageBean.setPumpingCost(dsrSewerageForm.getPumpingCost());
		dsrSewerageBean.setPumpingDischarge(dsrSewerageForm.getPumpingDischarge());
		dsrSewerageBean.setPumpingHead(dsrSewerageForm.getPumpingHead());
		dsrSewerageBean.setPumpingMachineryDischarge(dsrSewerageForm.getPumpingMachineryDischarge());
		dsrSewerageBean.setRestroomCost(dsrSewerageForm.getRestroomCost());
		dsrSewerageBean.setRestroomQuantity(dsrSewerageForm.getRestroomQuantity());
		dsrSewerageBean.setRisingCost(dsrSewerageForm.getRisingCost());
		dsrSewerageBean.setRisingSize(dsrSewerageForm.getRisingSize());
		dsrSewerageBean.setRisingType(dsrSewerageForm.getRisingType());
		dsrSewerageBean.setSchemeId(dsrSewerageForm.getSchemeId());
		dsrSewerageBean.setSewerageCost(dsrSewerageForm.getSewerageCost());
		dsrSewerageBean.setSewerageTechnology(dsrSewerageForm.getSewerageTechnology());
		dsrSewerageBean.setSiteDevelopmentCost(dsrSewerageForm.getSiteDevelopmentCost());
		dsrSewerageBean.setSludgeCuringCost(dsrSewerageForm.getSludgeCuringCost());
		dsrSewerageBean.setSludgeDryingCost(dsrSewerageForm.getSludgeDryingCost());
		dsrSewerageBean.setTotalCostOfStructure(dsrSewerageForm.getTotalCostOfStructure());
		dsrSewerageBean.setTotalCostPipeSewer(dsrSewerageForm.getTotalCostPipeSewer());
		dsrSewerageBean.setProvisionEnvironmentActivitiesCost(dsrSewerageForm.getProvisionEnvironmentActivitiesCost());
		dsrSewerageBean.setVillageId(dsrSewerageForm.getVillageId());
		dsrSewerageBean.setDsrDate(MisUtility.convertStringToDate(dsrSewerageForm.getDsrDate()));
		

		return dsrSewerageBean;
	}
	@SuppressWarnings({ "unchecked" })
	private List<DSRSewerageSewerBean> populateDSRSewerageSewerBeans(DSRSewerageForm dsrSewerageForm,String schemeId,  MISSessionBean misSessionBean, String status){
		
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);

		List<DSRSewerageSewerBean> dsrSewerageSewerBeans = new ArrayList<DSRSewerageSewerBean>();


		Datagrid dsrSewerageSewerGrid = dsrSewerageForm.getSewerageSystemDatagrid();

		Collection<DSRSewerageSewerBean> addedDSRSewerageSewerBeans = dsrSewerageSewerGrid.getAddedData();
		if(!MisUtility.ifEmpty(addedDSRSewerageSewerBeans)){
			for (DSRSewerageSewerBean dsrSewerageSewerBean : addedDSRSewerageSewerBeans) {
				dsrSewerageSewerBean.setSchemeId(schemeId);
				dsrSewerageSewerBean.setMisAuditBean(misAuditBean);
				dsrSewerageSewerBeans.add(dsrSewerageSewerBean);
			}
		}

		Collection<DSRSewerageSewerBean> modifiedDSRSewerageSewerBeans = dsrSewerageSewerGrid.getModifiedData();
		if(!MisUtility.ifEmpty(modifiedDSRSewerageSewerBeans)){
			for (DSRSewerageSewerBean dsrSewerageSewerBean : modifiedDSRSewerageSewerBeans) {
				dsrSewerageSewerBean.setSchemeId(schemeId);
				dsrSewerageSewerBean.setMisAuditBean(misAuditBean);
				dsrSewerageSewerBeans.add(dsrSewerageSewerBean);
			}
				
			}
	
		
		Collection<DSRSewerageSewerBean> deletedDSRSewerageSewerBeans = dsrSewerageSewerGrid.getDeletedData();
		if(!MisUtility.ifEmpty(deletedDSRSewerageSewerBeans)){
			for (DSRSewerageSewerBean dsrSewerageSewerBean : deletedDSRSewerageSewerBeans) {
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				dsrSewerageSewerBean.setSchemeId(schemeId);
				dsrSewerageSewerBean.setMisAuditBean(misAuditBean);
				dsrSewerageSewerBeans.add(dsrSewerageSewerBean);
			}
				
			}
		
		return dsrSewerageSewerBeans;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<DSRSewerageBean> findDSRSewerage(
			DSRSewerageForm dsrSewerageForm, List<String> statusList)
			throws MISException {
		List<DSRSewerageBean> dsrSewerageBeans = new  ArrayList<DSRSewerageBean>();
try {
			
	      DSRSewerageBean dsrSewerageBean = new DSRSewerageBean();
	      dsrSewerageBean.setSchemeId(dsrSewerageForm.getSchemeId());
	      dsrSewerageBeans= dsrSewerageDao.findDSRSewerage(dsrSewerageBean, statusList);
			Set<DSRSewerageSewerBean> dsrSewerageSewerBeans = null;
			Iterator<DSRSewerageSewerBean> dsrSewerageSewerIterator = null;
			DSRSewerageSewerBean  dsrSewerageSewerBean = null;
			if(!MisUtility.ifEmpty(dsrSewerageBeans)){

				for (DSRSewerageBean bean : dsrSewerageBeans) {

					dsrSewerageSewerBeans = bean.getDsrSewerageSewerBeans();
					if(!MisUtility.ifEmpty(dsrSewerageSewerBeans)){
						dsrSewerageSewerIterator = dsrSewerageSewerBeans.iterator();					
						while(dsrSewerageSewerIterator.hasNext()){
							dsrSewerageSewerBean = dsrSewerageSewerIterator.next();
							if(MISConstants.MASTER_STATUS_DELETED.equals(dsrSewerageSewerBean.getMisAuditBean().getStatus())){
								dsrSewerageSewerIterator.remove();
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
		return dsrSewerageBeans;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveDSRSewerage(DSRSewerageForm dsrSewerageForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			System.out.println("In Save DSRSewerage  ");
			DSRSewerageBean dsrSewerageBean = populateDSRSewerageBean(dsrSewerageForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			dsrSewerageBean.setMisAuditBean(misAuditBean);
			System.out.println("dsrSewerageBean" + dsrSewerageBean);
			String schemeId = dsrSewerageDao.saveDSRSewerage(dsrSewerageBean);
		
			if(MisUtility.ifEmpty(schemeId)){
				List<DSRSewerageSewerBean> dsrSewerageSewerBeans = populateDSRSewerageSewerBeans(dsrSewerageForm,schemeId,  misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(dsrSewerageSewerBeans)){
					boolean dsrCanalDistributionStatus = dsrSewerageSewerDao.saveOrUpdateDSRSewerageSewerBean(dsrSewerageSewerBeans);
					if(!dsrCanalDistributionStatus){
						log.error(dsrSewerageSewerBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to save DSR- Sewerage Sewer details");
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
	public boolean updateDSRSewerage(DSRSewerageForm dsrSewerageForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			System.out.println("In updateDSRSewerage  ");
			DSRSewerageBean dsrSewerageBean = populateDSRSewerageBean(dsrSewerageForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			dsrSewerageBean.setMisAuditBean(misAuditBean);

			boolean status  = dsrSewerageDao.updateDSRSewerage(dsrSewerageBean);
			
			String schemeId = dsrSewerageForm.getSchemeId();
		
			if(MisUtility.ifEmpty(status)){
				List<DSRSewerageSewerBean> dsrSewerageSewerBeans = populateDSRSewerageSewerBeans(dsrSewerageForm,schemeId,  misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(dsrSewerageSewerBeans)){
					boolean dsrCanalDistributionStatus = dsrSewerageSewerDao.saveOrUpdateDSRSewerageSewerBean(dsrSewerageSewerBeans);
					if(!dsrCanalDistributionStatus){
						log.error(dsrSewerageSewerBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to update DSR- Sewerage Sewer details");
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
	public boolean deleteDSRSewerage(DSRSewerageForm dsrSewerageForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			System.out.println("In deleteDSRSewerage  ");
			DSRSewerageBean dsrSewerageBean = populateDSRSewerageBean(dsrSewerageForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			dsrSewerageBean.setMisAuditBean(misAuditBean);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			boolean status  = dsrSewerageDao.updateDSRSewerage(dsrSewerageBean);
			
			String schemeId  =dsrSewerageForm.getSchemeId();

			DSRSewerageSewerBean dsrSewerageSewerBean = new DSRSewerageSewerBean();
			dsrSewerageSewerBean.setSchemeId(schemeId);

			if(status){
				List<DSRSewerageSewerBean> dsrSewerageSewerBeans =dsrSewerageSewerDao.findDSRCanalDistribution(dsrSewerageSewerBean, statusList);
					
				if(!MisUtility.ifEmpty(dsrSewerageSewerBeans)){
					for (DSRSewerageSewerBean dsrSewerageSewerBean2 : dsrSewerageSewerBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
						dsrSewerageSewerBean2.setMisAuditBean(misAuditBean);
					}
					boolean dsrDSRSewerageSewerStatus = dsrSewerageSewerDao.saveOrUpdateDSRSewerageSewerBean(dsrSewerageSewerBeans);
					if(!dsrDSRSewerageSewerStatus){
						log.error(dsrSewerageSewerBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to delete DSR SewerageSewer details");
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
	public boolean postDSRSewerage(DSRSewerageForm dsrSewerageForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			System.out.println("In Post deleteDSRSewerage  ");
			DSRSewerageBean dsrSewerageBean = populateDSRSewerageBean(dsrSewerageForm);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			dsrSewerageBean = dsrSewerageDao.findDSRSewerage(dsrSewerageBean, statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(dsrSewerageBean.getMisAuditBean().getEntBy());
			misAuditBean.setEntDate(dsrSewerageBean.getMisAuditBean().getEntDate());
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			dsrSewerageBean.setMisAuditBean(misAuditBean);
			
			boolean status  = dsrSewerageDao.updateDSRSewerage(dsrSewerageBean);
			
			String schemeId  =dsrSewerageForm.getSchemeId();

			DSRSewerageSewerBean dsrSewerageSewerBean = new DSRSewerageSewerBean();
			dsrSewerageSewerBean.setSchemeId(schemeId);

			if(status){
				List<DSRSewerageSewerBean> dsrSewerageSewerBeans =dsrSewerageSewerDao.findDSRCanalDistribution(dsrSewerageSewerBean, statusList);
					
				if(!MisUtility.ifEmpty(dsrSewerageSewerBeans)){
					for (DSRSewerageSewerBean dsrSewerageSewerBean2 : dsrSewerageSewerBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
						dsrSewerageSewerBean2.setMisAuditBean(misAuditBean);
					}
					boolean dsrDSRSewerageSewerStatus = dsrSewerageSewerDao.saveOrUpdateDSRSewerageSewerBean(dsrSewerageSewerBeans);
					if(!dsrDSRSewerageSewerStatus){
						log.error(dsrSewerageSewerBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to post DSR SewerageSewer details");
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
	
}
