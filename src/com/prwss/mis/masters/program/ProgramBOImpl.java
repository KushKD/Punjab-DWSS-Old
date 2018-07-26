/**
 * 
 */
package com.prwss.mis.masters.program;

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
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;
import com.prwss.mis.masters.program.struts.ProgramForm;

/**
 * @author pjha
 *
 */
public class ProgramBOImpl implements ProgramBO {
	private Logger log = Logger.getLogger(ProgramBOImpl.class);
	private ProgramDao programDao;
	
	
	public void setProgramDao(ProgramDao programDao) {
		this.programDao = programDao;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveProgram(ProgramForm programForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			ProgramBean programBean = populateProgramBean(programForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			programBean.setMisAuditBean(misAuditBean);

			status = programDao.saveScheme(programBean);



			if(!status){
				log.error(programBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to save Program details");
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
	public boolean updateProgram(ProgramForm programForm,
			MISSessionBean misSessionBean,List<String> statusList) throws MISException {
		try {
			boolean status = false;
			ProgramBean programBean = populateProgramBean(programForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(statusList.get(0));
			programBean.setMisAuditBean(misAuditBean);

			status = programDao.saveOrUpdateScheme(programBean);



			if(!status){
				log.error(programBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Update Program details");
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
	public boolean deleteProgram(ProgramForm programForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			ProgramBean programBean = populateProgramBean(programForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			programBean.setMisAuditBean(misAuditBean);

			status = programDao.saveOrUpdateScheme(programBean);



			if(!status){
				log.error(programBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Program details");
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
	public boolean postProgram(ProgramForm programForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			boolean status = false;
			ProgramBean programBean= new ProgramBean();
			programBean.setProgramId(programForm.getProgramId());
			programBean= programDao.findProgram(programBean,statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean = programBean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			programBean.setMisAuditBean(misAuditBean);			
		
			
			status = programDao.saveOrUpdateScheme(programBean);

			if(!status){
				log.error(programBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Program details");
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
	
	private ProgramBean populateProgramBean(ProgramForm programForm){
		
		
		ProgramBean programBean = new ProgramBean();
		try {
			programBean.setProgramId(programForm.getProgramId());
			programBean.setProgramName(programForm.getProgramName());
			programBean.setBenifciaryShare(programForm.getBenifciaryShare());
			programBean.setGoiShare(programForm.getGoiShare());
			programBean.setGopShare(programForm.getGopShare());
			programBean.setPlannedNonPlanned(programForm.getPlannedNonPlanned());
			programBean.setSwapNonSwap(programForm.getSwapNonSwap());
			programBean.setProgram(programForm.getProgram());
			programBean.setSponserAgencyShare(programForm.getSponserAgencyShare());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return programBean;
		
		
		
	}

	@Override
	public List<ProgramBean> findProgram(ProgramForm programForm,
			List<String> statusList) throws MISException {
		List<ProgramBean> programBean2  = new ArrayList<ProgramBean>();
	
			try {
				ProgramBean programBean = new ProgramBean();
				
				programBean.setProgramId(programForm.getProgramId());
				programBean.setProgramName(programForm.getProgramName());
				programBean.setBenifciaryShare(programForm.getBenifciaryShare());
				programBean.setGoiShare(programForm.getGoiShare());
				programBean.setGopShare(programForm.getGopShare());
				programBean.setPlannedNonPlanned(programForm.getPlannedNonPlanned());
				programBean.setSwapNonSwap(programForm.getSwapNonSwap());
				
				programBean.setSponserAgencyShare(programForm.getSponserAgencyShare());
				programBean2 = programDao.findProgram(programBean, statusList);
			
		} catch (DataAccessException e) {
			throw e;
		}
		return programBean2;
	}

}
