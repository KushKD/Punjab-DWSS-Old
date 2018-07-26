/**
 * 
 */
package com.prwss.mis.pmm.DSRPonds;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.DSRPonds.dao.DSRPondsBean;
import com.prwss.mis.pmm.DSRPonds.dao.DSRPondsDao;
import com.prwss.mis.pmm.DSRPonds.struts.DSRPondsForm;


/**
 * @author pjha
 *
 */
public class DSRPondsBOImpls implements DSRPondsBO {
	
	private Logger log = Logger.getLogger(DSRPondsBOImpls.class);
	private DSRPondsDao dsrPondsDao;	
	public void setDsrPondsDao(DSRPondsDao dsrPondsDao) {
		this.dsrPondsDao = dsrPondsDao;
	}

	@Override
	public List<DSRPondsBean> findDSRPonds(DSRPondsForm dsrPondsForm,
			List<String> statusList) throws MISException {
		List<DSRPondsBean> dsrPondsBeans  = new ArrayList<DSRPondsBean>();
		try {
			DSRPondsBean dsrPondsBean = new DSRPondsBean();
			dsrPondsBean.setSchemeId(dsrPondsForm.getSchemeId());
			dsrPondsBean.setLocationId(dsrPondsForm.getLocationId());
			dsrPondsBeans =dsrPondsDao.findDSRPonds(dsrPondsBean, statusList);
		} catch (DataAccessException e) {
			throw e;
		}
		return dsrPondsBeans;
	}

	@Override
	public boolean saveDSRPonds(DSRPondsForm dsrPondsForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			DSRPondsBean dsrPondsBean = populateDSRPondsBean(dsrPondsForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			dsrPondsBean.setMisAuditBean(misAuditBean);
			status = dsrPondsDao.saveDSRPonds(dsrPondsBean);
			if(!status){
				log.error(dsrPondsBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to save DSR Ponds details");
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
	public boolean updateDSRPonds(DSRPondsForm dsrPondsForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			DSRPondsBean dsrPondsBean = populateDSRPondsBean(dsrPondsForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			dsrPondsBean.setMisAuditBean(misAuditBean);
			status = dsrPondsDao.saveOrUpdateDSRPonds(dsrPondsBean);
			if(!status){
				log.error(dsrPondsBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to update DSR Ponds details");
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
	public boolean deleteDSRPonds(DSRPondsForm dsrPondsForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			DSRPondsBean dsrPondsBean = populateDSRPondsBean(dsrPondsForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			dsrPondsBean.setMisAuditBean(misAuditBean);
			status = dsrPondsDao.saveOrUpdateDSRPonds(dsrPondsBean);
			if(!status){
				log.error(dsrPondsBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to delete DSR Ponds details");
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
	public boolean postDSRPonds(DSRPondsForm dsrPondsForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			boolean status = false;
			DSRPondsBean dsrPondsBean= new DSRPondsBean();
			dsrPondsBean.setSchemeId(dsrPondsForm.getSchemeId());
			dsrPondsBean= dsrPondsDao.findDSRPonds(dsrPondsBean, statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean = dsrPondsBean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			dsrPondsBean.setMisAuditBean(misAuditBean);			
			status = dsrPondsDao.saveOrUpdateDSRPonds(dsrPondsBean);
			if(!status){
				log.error(dsrPondsBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Post DSR Ponds details");
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
	private DSRPondsBean populateDSRPondsBean(DSRPondsForm dsrPondsForm){
		DSRPondsBean dsrPondsBean = new DSRPondsBean();
		try {
			dsrPondsBean.setDsrDate(MisUtility.convertStringToDate(dsrPondsForm.getDsrDate()));
			dsrPondsBean.setVillageId(dsrPondsForm.getVillageId());
			dsrPondsBean.setDrainsCost(dsrPondsForm.getDrainsCost());
			dsrPondsBean.setHaudisCost(dsrPondsForm.getHaudisCost());
			dsrPondsBean.setHaudisQuantity(dsrPondsForm.getHaudisQuantity());
			dsrPondsBean.setJcbHours(dsrPondsForm.getJcbHours());
			dsrPondsBean.setJcbHoursCost(dsrPondsForm.getJcbHoursCost());
			dsrPondsBean.setLabourMandays(dsrPondsForm.getLabourMandays());
			dsrPondsBean.setLabourMandaysCost(dsrPondsForm.getLabourMandaysCost());
			dsrPondsBean.setLocationId(dsrPondsForm.getLocationId());
			dsrPondsBean.setOtheralliedCost(dsrPondsForm.getOtheralliedCost());
			dsrPondsBean.setPipelineCost(dsrPondsForm.getPipelineCost());
			dsrPondsBean.setPipelineLength(dsrPondsForm.getPipelineLength());
			dsrPondsBean.setSchemeId(dsrPondsForm.getSchemeId());
			dsrPondsBean.setTotalCost(dsrPondsForm.getTotalCost());
			dsrPondsBean.setTractorsHours(dsrPondsForm.getTractorsHours());
			dsrPondsBean.setTractorsHoursCost(dsrPondsForm.getTractorsHoursCost());
			dsrPondsBean.setUnforeseenCost(dsrPondsForm.getUnforeseenCost());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return dsrPondsBean;
	}
}
