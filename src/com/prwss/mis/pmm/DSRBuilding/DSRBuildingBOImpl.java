/**
 * 
 */
package com.prwss.mis.pmm.DSRBuilding;

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
import com.prwss.mis.pmm.DSRBuilding.struts.DSRBuildingForm;
import com.prwss.mis.pmm.DSRBuilding.dao.DSRBuildingBean;
import com.prwss.mis.pmm.DSRBuilding.dao.DSRBuildingDao;

/**
 * @author pjha
 *
 */
public class DSRBuildingBOImpl implements   DSRBuildingBO {
	private Logger log = Logger.getLogger(DSRBuildingBOImpl.class);
	private DSRBuildingDao dsrBuildingDao;	

	public void setDsrBuildingDao(DSRBuildingDao dsrBuildingDao) {
		this.dsrBuildingDao = dsrBuildingDao;
	}

	@Override
	public List<DSRBuildingBean> findDSRBuilding(DSRBuildingForm dsrBuildingForm,
			List<String> statusList) throws MISException {
		List<DSRBuildingBean> dsrBuildingBeans  = new ArrayList<DSRBuildingBean>();

		try {
			DSRBuildingBean dsrBuildingBean = new DSRBuildingBean();

			dsrBuildingBean.setSchemeId(dsrBuildingForm.getSchemeId());
			dsrBuildingBean.setLocationId(dsrBuildingForm.getLocationId());

			dsrBuildingBeans = dsrBuildingDao.findDSRBuilding(dsrBuildingBean, statusList);

		} catch (DataAccessException e) {
			throw e;
		}
		return dsrBuildingBeans;
	}


	@Override
	public boolean saveDSRBuilding(DSRBuildingForm dsrBuildingForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			DSRBuildingBean dsrBuildingBean = populateDSRBuildingBean(dsrBuildingForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			dsrBuildingBean.setMisAuditBean(misAuditBean);
			status = dsrBuildingDao.saveVillageDSRBuilding(dsrBuildingBean);
			if(!status){
				log.error(dsrBuildingBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to save DSR Building works details");
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
	public boolean updateDSRBuilding(DSRBuildingForm dsrBuildingForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			DSRBuildingBean dsrBuildingBean = populateDSRBuildingBean(dsrBuildingForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			dsrBuildingBean.setMisAuditBean(misAuditBean);

			status = dsrBuildingDao.saveOrUpdateDSRBuilding(dsrBuildingBean);
			if(!status){
				log.error(dsrBuildingBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Update DSR Building works details");
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
	public boolean deleteDSRBuilding(DSRBuildingForm dsrBuildingForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			DSRBuildingBean dsrBuildingBean = populateDSRBuildingBean(dsrBuildingForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			dsrBuildingBean.setMisAuditBean(misAuditBean);

			status = dsrBuildingDao.saveOrUpdateDSRBuilding(dsrBuildingBean);
			if(!status){
				log.error(dsrBuildingBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to delete DSR Building works details");
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
	public boolean postDSRBuilding(DSRBuildingForm dsrBuildingForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			boolean status = false;
			DSRBuildingBean dsrBuildingBean= new DSRBuildingBean();
			dsrBuildingBean.setSchemeId(dsrBuildingForm.getSchemeId());

			dsrBuildingBean= dsrBuildingDao.findDSRBuilding(dsrBuildingBean, statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean = dsrBuildingBean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			dsrBuildingBean.setMisAuditBean(misAuditBean);			


			status = dsrBuildingDao.saveOrUpdateDSRBuilding(dsrBuildingBean);

			if(!status){
				log.error(dsrBuildingBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Post DSR Building works details");
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

	private DSRBuildingBean populateDSRBuildingBean(DSRBuildingForm dsrBuildingForm){


		DSRBuildingBean dsrBuildingBean = new DSRBuildingBean();
		try {
			dsrBuildingBean.setAcplantCost(dsrBuildingForm.getAcplantCost());
			dsrBuildingBean.setDsrDate(MisUtility.convertStringToDate(dsrBuildingForm.getDsrDate()));
			dsrBuildingBean.setVillageId(dsrBuildingForm.getVillageId());
			dsrBuildingBean.setContigencyCharges(dsrBuildingForm.getContigencyCharges());
			dsrBuildingBean.setDepartmentalCharges(dsrBuildingForm.getDepartmentalCharges());
			dsrBuildingBean.setEstatePipelineCost(dsrBuildingForm.getEstatePipelineCost());
			dsrBuildingBean.setEstatePipelineLength(dsrBuildingForm.getEstatePipelineLength());
			dsrBuildingBean.setFiresystemCost(dsrBuildingForm.getFiresystemCost());
			dsrBuildingBean.setHeadworksCost(dsrBuildingForm.getHeadworksCost());
			dsrBuildingBean.setHeadworksDischarge(dsrBuildingForm.getHeadworksDischarge());
			dsrBuildingBean.setHeadworksHead(dsrBuildingForm.getHeadworksHead());
			dsrBuildingBean.setHeadworksPumpchamberCost(dsrBuildingForm.getHeadworksPumpchamberCost());
			dsrBuildingBean.setInternalPipelineCost(dsrBuildingForm.getInternalPipelineCost());
			dsrBuildingBean.setInternalPipelineLength(dsrBuildingForm.getInternalPipelineLength());
			dsrBuildingBean.setLocationId(dsrBuildingForm.getLocationId());
			dsrBuildingBean.setOhsrCapacity(dsrBuildingForm.getOhsrCapacity());
			dsrBuildingBean.setOhsrCost(dsrBuildingForm.getOhsrCost());
			dsrBuildingBean.setRainwaterCost(dsrBuildingForm.getRainwaterCost());
			dsrBuildingBean.setSanitaryOwcEwcCost(dsrBuildingForm.getSanitaryOwcEwcCost());
			dsrBuildingBean.setSanitaryOwcEwcQuantity(dsrBuildingForm.getSanitaryOwcEwcQuantity());
			dsrBuildingBean.setSanitaryUrinalCost(dsrBuildingForm.getSanitaryUrinalCost());
			dsrBuildingBean.setSanitaryUrinalQuantity(dsrBuildingForm.getSanitaryUrinalQuantity());
			dsrBuildingBean.setSanitaryWashbasinCost(dsrBuildingForm.getSanitaryWashbasinCost());
			dsrBuildingBean.setSchemeId(dsrBuildingForm.getSchemeId());
			dsrBuildingBean.setSewerageCost(dsrBuildingForm.getSewerageCost());
			dsrBuildingBean.setSewerageLength(dsrBuildingForm.getSewerageLength());
			dsrBuildingBean.setSplitacCost(dsrBuildingForm.getSplitacCost());
			dsrBuildingBean.setSplitacQuantity(dsrBuildingForm.getSplitacQuantity());
			dsrBuildingBean.setStormSewerageCost(dsrBuildingForm.getStormSewerageCost());
			dsrBuildingBean.setStormSewerageLength(dsrBuildingForm.getStormSewerageLength());
			dsrBuildingBean.setTotalCost(dsrBuildingForm.getTotalCost());
			
			


		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		return dsrBuildingBean;

	}

}
