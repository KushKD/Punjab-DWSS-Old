package com.prwss.mis.masters.village.dao;

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
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;
import com.prwss.mis.masters.village.ProposedProgramBean;
import com.prwss.mis.masters.village.ProposedProgramDao;
import com.prwss.mis.masters.village.VillageProgramImplementedBean;
import com.prwss.mis.masters.village.struts.VillageProgramImplForm;
import com.prwss.mis.pmm.village.ncpcstatus.dao.VillageNCPCStatusBean;
import com.prwss.mis.pmm.village.ncpcstatus.dao.VillageNCPCStatusDao;

public class VillageProgramImplementedBOImpl implements VillageProgramImplementedBO {
private Logger log = Logger.getLogger(VillageProgramImplementedBOImpl.class);
private VillageProgramImplementedDao villageProgramImplementedDao;
private VillageDao villageDao;
private ProgramDao programDao;
private VillageNCPCStatusDao villageNCPCStatusDao;
private ProposedProgramDao proposedProgramDao;

public void setProposedProgramDao(ProposedProgramDao proposedProgramDao) {
	this.proposedProgramDao = proposedProgramDao;
}

public void setVillageNCPCStatusDao(VillageNCPCStatusDao villageNCPCStatusDao) {
	this.villageNCPCStatusDao = villageNCPCStatusDao;
}

public void setProgramDao(ProgramDao programDao) {
	this.programDao = programDao;
}

public void setVillageDao(VillageDao villageDao) {
	this.villageDao = villageDao;
}

public void setVillageProgramImplementedDao(
		VillageProgramImplementedDao villageProgramImplementedDao) {
	this.villageProgramImplementedDao = villageProgramImplementedDao;
}

	@Override
	public boolean updateVillageProgImpl(
			VillageProgramImplForm villageProgramImplForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			VillageProgramImplementedBean implementedBean = populateVillage(villageProgramImplForm);	
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
//			villageBean.setMisAuditBean(misAuditBean);
			implementedBean.setMisAuditBean(misAuditBean);
			status = villageProgramImplementedDao.updateVillageImplemented(implementedBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}
	
	@Override
	public boolean postVillageProgImp(VillageProgramImplForm villageProgramImplForm,
			MISSessionBean misSessionBean) throws MISException {
				boolean status = false;
				
				try {
					VillageProgramImplementedBean implementedBean = populateVillage(villageProgramImplForm);	
					MISAuditBean misAuditBean = new MISAuditBean();
					misAuditBean.setEntBy(misSessionBean.getEnteredBy());
					misAuditBean.setEntDate(misSessionBean.getEnteredDate());
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
//					villageBean.setMisAuditBean(misAuditBean);
					implementedBean.setMisAuditBean(misAuditBean);
					ProposedProgramBean programBean = new ProposedProgramBean();
					ProgramBean bean = new ProgramBean();
					VillageNCPCStatusBean statusBean = new VillageNCPCStatusBean();
					statusBean = villageNCPCStatusDao.getVillNcpcStatusBeanById(villageProgramImplForm.getVillageId());
					System.out.println("----------check2--------------");
					programBean.setVillageId(villageProgramImplForm.getVillageId());
					String value = new String();
					if(!villageProgramImplForm.getPrgm_to_be_impl_nc_vill_habit_rws().equals("NA"))
						value=villageProgramImplForm.getPrgm_to_be_impl_nc_vill_habit_rws();					
					else if(!villageProgramImplForm.getPrgm_to_be_impl_nc_vill_habit_arp().equals("NA"))
						value=villageProgramImplForm.getPrgm_to_be_impl_nc_vill_habit_arp();
					else if(!villageProgramImplForm.getPrgm_to_be_impl_nc_vill_habit_qp().equals("NA"))
						value=villageProgramImplForm.getPrgm_to_be_impl_nc_vill_habit_qp();
					else if(!villageProgramImplForm.getPrgm_to_be_impl_nc_vill_habit_wb_ada().equals("NA"))
						value=villageProgramImplForm.getPrgm_to_be_impl_nc_vill_habit_wb_ada();
					else if(!villageProgramImplForm.getPrgm_to_be_impl_nc_vill_habit_nabard_xii().equals("NA")){
						value=villageProgramImplForm.getPrgm_to_be_impl_nc_vill_habit_nabard_xii();
						
					}
					else if(!villageProgramImplForm.getPrgm_to_be_impl_pc_vill_habit_rws().equals("NA"))
						value=villageProgramImplForm.getPrgm_to_be_impl_pc_vill_habit_rws();
					else if(!villageProgramImplForm.getPrgm_to_be_impl_pc_vill_habit_arp().equals("NA"))
						value=villageProgramImplForm.getPrgm_to_be_impl_pc_vill_habit_arp();
					else if(!villageProgramImplForm.getPrgm_to_be_impl_pc_vill_habit_qp().equals("NA"))
						value=villageProgramImplForm.getPrgm_to_be_impl_pc_vill_habit_qp();
					else  if(!villageProgramImplForm.getPrgm_to_be_impl_pc_vill_habit_wb_ada().equals("NA"))
						value=villageProgramImplForm.getPrgm_to_be_impl_pc_vill_habit_wb_ada();
					else  if(!villageProgramImplForm.getPrgm_to_be_impl_pc_vill_habit_nabard_xii().equals("NA"))
						value=villageProgramImplForm.getPrgm_to_be_impl_pc_vill_habit_nabard_xii();
					else
						value="NO";
					System.out.println("--------Check Value----"+value);
					if(!value.equals("NO")){
						bean = programDao.getProgramById(value);
						programBean.setProposedProgramId(bean.getProgramId());
						programBean.setProposedProgramName(bean.getProgramName());
						programBean.setProposedSwapNonswap(bean.getSwapNonSwap());
						programBean.setNcPcStatus(statusBean.getNcPcStatus());
					}
					else{
						programBean.setProposedProgramId("");
						programBean.setProposedProgramName("");
						programBean.setProposedSwapNonswap("");
						programBean.setNcPcStatus("FC");
					}
						programBean.setFreezValue(MisUtility.convertStringToDate("01-04-2013"));
						System.out.println("---------villageID "+villageProgramImplForm.getVillageId()+"------ProgramId "+bean.getProgramId()+"----Program Name "+bean.getProgramName()+"--------Swap NonSwap "+bean.getSwapNonSwap()+"---------NC PC "+statusBean.getNcPcStatus());
						 proposedProgramDao.saveProposedProgram(programBean);
					
					status = villageProgramImplementedDao.updateVillageImplemented(implementedBean);
					
					
				} catch (DataAccessException e) {
					log.error(e.getLocalizedMessage(),e);
					throw new MISException(e);
				}
				return status;
			}
	
	@Override
	public boolean saveVillageProgImpl(
			VillageProgramImplForm villageProgramImplForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			
			VillageBean villageBean = new VillageBean();
			villageBean.setVillageId(villageProgramImplForm.getVillageId());
			List<VillageBean> villageBeans =  villageDao.findVillage(villageBean, statusList);
		
			if(MisUtility.ifEmpty(villageBeans)){
				throw new MISException(MISExceptionCodes.MIS012,"Village does not exist");
			}
			VillageProgramImplementedBean villageProgramFindImplementedBean = new VillageProgramImplementedBean();
			villageProgramFindImplementedBean.setVillageId(villageProgramImplForm.getVillageId());
			
			List<VillageProgramImplementedBean> implementedBeans = villageProgramImplementedDao.findVillageImplemented(villageProgramFindImplementedBean, statusList);
			if(!MisUtility.ifEmpty(implementedBeans)){
				throw new MISException(MISExceptionCodes.MIS001,"Information already exisits for the specified village");
			}
			
			
			VillageProgramImplementedBean implementedBean = populateVillage(villageProgramImplForm);	
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
//			villageBean.setMisAuditBean(misAuditBean);
			implementedBean.setMisAuditBean(misAuditBean);
			status = villageProgramImplementedDao.saveVillageImplemented(implementedBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}
	
	@Override
	public VillageProgramImplementedBean findVillageProgImpl(
			VillageProgramImplForm villageProgramImplForm,
			List<String> statusList) throws MISException {
		VillageProgramImplementedBean villageProgramImplementedBean = null;
		List<VillageProgramImplementedBean> villageProgramImplementedBeans = null;
		try {
			VillageProgramImplementedBean bean = new VillageProgramImplementedBean();
			if(MisUtility.ifEmpty(villageProgramImplForm.getVillageId())){
				System.out.println("In");
				bean.setVillageId(villageProgramImplForm.getVillageId());
				villageProgramImplementedBeans = villageProgramImplementedDao.findVillageImplemented(bean, statusList);
				
				if(!MisUtility.ifEmpty(villageProgramImplementedBeans)){
					villageProgramImplementedBean = villageProgramImplementedBeans.get(0);
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return villageProgramImplementedBean;
	}
	
	private VillageProgramImplementedBean populateVillage(VillageProgramImplForm villageProgramImplForm)
	{  
		VillageProgramImplementedBean bean = new VillageProgramImplementedBean();
		bean.setPrgm_to_be_impl_nc_vill_habit_arp(villageProgramImplForm.getPrgm_to_be_impl_nc_vill_habit_arp());
		bean.setPrgm_to_be_impl_nc_vill_habit_nabard_xii(villageProgramImplForm.getPrgm_to_be_impl_nc_vill_habit_nabard_xii());
		bean.setPrgm_to_be_impl_nc_vill_habit_qp(villageProgramImplForm.getPrgm_to_be_impl_nc_vill_habit_qp());
		bean.setPrgm_to_be_impl_nc_vill_habit_rws(villageProgramImplForm.getPrgm_to_be_impl_nc_vill_habit_rws());
		bean.setPrgm_to_be_impl_nc_vill_habit_wb_ada(villageProgramImplForm.getPrgm_to_be_impl_nc_vill_habit_wb_ada());
		
		bean.setPrgm_to_be_impl_pc_vill_habit_arp(villageProgramImplForm.getPrgm_to_be_impl_pc_vill_habit_arp());
		bean.setPrgm_to_be_impl_pc_vill_habit_nabard_xii(villageProgramImplForm.getPrgm_to_be_impl_pc_vill_habit_nabard_xii());
		bean.setPrgm_to_be_impl_pc_vill_habit_qp(villageProgramImplForm.getPrgm_to_be_impl_pc_vill_habit_qp());
		bean.setPrgm_to_be_impl_pc_vill_habit_rws(villageProgramImplForm.getPrgm_to_be_impl_pc_vill_habit_rws());
		bean.setPrgm_to_be_impl_pc_vill_habit_wb_ada(villageProgramImplForm.getPrgm_to_be_impl_pc_vill_habit_wb_ada());
		bean.setVillageId(villageProgramImplForm.getVillageId());
		
		return bean;
		
	}

}
