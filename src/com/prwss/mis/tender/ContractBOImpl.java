package com.prwss.mis.tender;

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
import com.prwss.mis.masters.scheme.dao.SchemeVillageBean;
import com.prwss.mis.masters.vendor.dao.VendorBean;
import com.prwss.mis.tender.award.dao.TenderObjectionBean;
import com.prwss.mis.tender.contract.dao.ContractDao;
import com.prwss.mis.tender.contract.dao.ContractDetailBean;
import com.prwss.mis.tender.contract.dao.ContractDetailDao;
import com.prwss.mis.tender.contract.dao.ContractHeaderBean;
import com.prwss.mis.tender.struts.BidderDetailGridBean;
import com.prwss.mis.tender.struts.ContractDetailGridBean;
import com.prwss.mis.tender.struts.ContractManagementForm;
import com.prwss.mis.tender.struts.TenderObjectionGridBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class ContractBOImpl implements ContractBO {
	
	private Logger log = Logger.getLogger(ContractBOImpl.class); 
	
	private ContractDao contractDao;
	private ContractDetailDao contractDetailDao;
	
	public void setContractDao(ContractDao contractDao) {
		this.contractDao = contractDao;
	}
	
	public void setContractDetailDao(ContractDetailDao contractDetailDao) {
		this.contractDetailDao = contractDetailDao;
	}

	@Override
	public List<ContractHeaderBean> findContract(ContractManagementForm contractManagementForm, List<String> statusList) throws MISException {
		List<ContractHeaderBean> contractHeaderBeans = null;
		try {
			ContractHeaderBean contractHeaderBean = new ContractHeaderBean();
			contractHeaderBean.setContractId(contractManagementForm.getContractNo());
			contractHeaderBean.setLocationId(contractManagementForm.getLocationId());
			contractHeaderBean.setTenderId(contractManagementForm.getTenderId());
			Set<ContractDetailBean> contractDetailBeans = null;
			contractHeaderBeans = contractDao.findContracts(contractHeaderBean, statusList);
			// Logic to remove Contract Details whose status is not in the given statusList object
			Iterator<ContractDetailBean> contractDetailBeanIterator = null;
			ContractDetailBean contractDetailBean = null;
			if(!MisUtility.ifEmpty(contractHeaderBeans)){
				for (ContractHeaderBean contractHeaderBean2 : contractHeaderBeans) {
					contractDetailBeans = contractHeaderBean2.getContractDetailBeans();
					
					if(!MisUtility.ifEmpty(contractDetailBeans)){
					contractDetailBeanIterator = contractDetailBeans.iterator();					
						while(contractDetailBeanIterator.hasNext()){
							contractDetailBean = contractDetailBeanIterator.next();
							System.out.println("--------------"+contractDetailBean.getMisAuditBean().getStatus());
							if(MISConstants.MASTER_STATUS_DELETED.equals(contractDetailBean.getMisAuditBean().getStatus())){
								contractDetailBeanIterator.remove();								
							}
						}
					}
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		}
		
		return contractHeaderBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateContract(ContractManagementForm contractManagementForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false;		
		try {
			ContractHeaderBean contractHeaderBean = populatecoContractHeaderBean(contractManagementForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			contractHeaderBean.setMisAuditBean(misAuditBean);
			status = contractDao.updateContract(contractHeaderBean);
			if(status){
				Collection<ContractDetailBean> contractDetailBeans =generateContractDetailBeans(contractManagementForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED); 
				log.debug("\nContract DetailBeans\t"+contractDetailBeans);
				for(ContractDetailBean contractDetailBean:contractDetailBeans){
					log.debug("status---"+contractDetailBean.getMisAuditBean().getStatus());
					log.debug("release amount==="+contractDetailBean.getReleaseAmount());
				}
				if(!MisUtility.ifEmpty(contractDetailBeans)){
					if(!contractDetailDao.saveOrUpdateContractDetail(contractDetailBeans)){
						System.out.println("--------------- inside inside --------------- inside inside -----------------");
						throw new MISException(MISExceptionCodes.MIS003, "Contract Details  not saved for the Tender Id : "+contractManagementForm.getContractNo()+" in Contract Detail Table");
					}
				}
			/*	Collection<ContractDetailBean> contractDetailBeans1 =generateContractDetailBeans1(contractManagementForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED); 
				log.debug("\nContract DetailBeans\t"+contractDetailBeans1);
				if(!MisUtility.ifEmpty(contractDetailBeans1)){
					if(!contractDetailDao.saveOrUpdateContractDetail1(contractDetailBeans1)){
						System.out.println("--------------- inside inside --------------- inside inside -----------------");
						throw new MISException(MISExceptionCodes.MIS003, "Contract Details  not saved for the Tender Id : "+contractManagementForm.getContractNo()+" in Contract Detail Table");
					}
				}
				Collection<ContractDetailBean> contractDetailBeans2 =generateContractDetailBeans2(contractManagementForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED); 
				log.debug("\nContract DetailBeans\t"+contractDetailBeans2);
				if(!MisUtility.ifEmpty(contractDetailBeans2)){
					if(!contractDetailDao.saveOrUpdateContractDetail2(contractDetailBeans2)){
						System.out.println("--------------- inside inside --------------- inside inside -----------------");
						throw new MISException(MISExceptionCodes.MIS003, "Contract Details  not saved for the Tender Id : "+contractManagementForm.getContractNo()+" in Contract Detail Table");
					}
				}
			}*/
		} 
	}catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		}
		
		return true;
	}
	
	private ContractHeaderBean populatecoContractHeaderBean(ContractManagementForm contractManagementForm){
		ContractHeaderBean contractHeaderBean = new ContractHeaderBean();
		
		contractHeaderBean.setLocationId(contractManagementForm.getLocationId());
		contractHeaderBean.setTenderId(contractManagementForm.getTenderId());

		contractHeaderBean.setContractId(contractManagementForm.getContractNo());
		contractHeaderBean.setContractDate(MisUtility.convertStringToDate(contractManagementForm.getContractDate()));
		contractHeaderBean.setRevisedContractDate(MisUtility.convertStringToDate(contractManagementForm.getRevisedContractDate()));
		contractHeaderBean.setLdAmount(contractManagementForm.getLdAmount());
		contractHeaderBean.setMaxLDRate(contractManagementForm.getMaxLd());
		contractHeaderBean.setRevisedContractAmount(contractManagementForm.getRevisedContractAmount());
		contractHeaderBean.setActualCompletionDate(MisUtility.convertStringToDate(contractManagementForm.getActualCompletionDate()));
		VendorBean vendorBean = new VendorBean();
		vendorBean.setVendorId(contractManagementForm.getVendorId());
		contractHeaderBean.setVendorBean(vendorBean);
		
		//contractHeaderBean.setTenderId(contractManagementForm.getTenderId());
		return contractHeaderBean;
	}
	
	@SuppressWarnings("unchecked")
	private Collection<ContractDetailBean> generateContractDetailBeans(ContractManagementForm contractManagementForm, MISSessionBean misSessionBean, String status) throws MISException{
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
	
List<ContractDetailBean> contractDetailBeans = new ArrayList<ContractDetailBean>();
ContractDetailBean contractDetailBean = null;

try {		
	

Collection<ContractDetailGridBean> modifiedContractDetailBeans = contractManagementForm.getMilestoneDatagrid().getModifiedData();


for (ContractDetailGridBean contractDetailGridBean : modifiedContractDetailBeans) {
	System.out.println("updated");
	contractDetailBean = new ContractDetailBean();
	contractDetailBean.setContractId(contractManagementForm.getContractNo());
	contractDetailBean.setActualCompletionDate(MisUtility.convertStringToDate(contractDetailGridBean.getActualCompletionDate()));
	contractDetailBean.setAmountDue(contractDetailGridBean.getAmountDue());
	contractDetailBean.setAmountReleaseDate(MisUtility.convertStringToDate(contractDetailGridBean.getAmountReleaseDate()));
	contractDetailBean.setBillAmount(contractDetailGridBean.getBillAmount());
	contractDetailBean.setBillDate(MisUtility.convertStringToDate(contractDetailGridBean.getBillDate()));
	contractDetailBean.setBillNo(contractDetailGridBean.getBillNo());
	contractDetailBean.setEstimatedCompletionDate(MisUtility.convertStringToDate(contractDetailGridBean.getEstimatedCompletionDate()));
	contractDetailBean.setMilestoneId(contractDetailGridBean.getMilestoneId());
	contractDetailBean.setMilestone(contractDetailGridBean.getMilestone());
	contractDetailBean.setMilestoneStatus(contractDetailGridBean.getMilestoneStatus());
	contractDetailBean.setReleaseAmount(contractDetailGridBean.getReleaseAmount());
	contractDetailBean.setRemarks(contractDetailGridBean.getRemarks());
	contractDetailBean.setRevisedCompletionDate(MisUtility.convertStringToDate(contractDetailGridBean.getRevisedCompletionDate()));
	contractDetailBean.setMisAuditBean(misAuditBean);
	contractDetailBeans.add(contractDetailBean);
			}
Collection<ContractDetailGridBean> deletedContractDetailBeans = contractManagementForm.getMilestoneDatagrid().getDeletedData();


System.out.println("deleted");
for (ContractDetailGridBean contractDetailGridBean : deletedContractDetailBeans) {
contractDetailBean = new ContractDetailBean();
contractDetailBean.setContractId(deletedContractDetailBeans.iterator().next().getContractId());
contractDetailBean.setActualCompletionDate(MisUtility.convertStringToDate(contractManagementForm.getActualCompletionDate()));
contractDetailBean.setAmountDue(contractDetailGridBean.getAmountDue());
contractDetailBean.setAmountReleaseDate(MisUtility.convertStringToDate(contractDetailGridBean.getAmountReleaseDate()));
contractDetailBean.setBillAmount(contractDetailGridBean.getBillAmount());
contractDetailBean.setBillDate(MisUtility.convertStringToDate(contractDetailGridBean.getBillDate()));
contractDetailBean.setBillNo(contractDetailGridBean.getBillNo());
contractDetailBean.setEstimatedCompletionDate(MisUtility.convertStringToDate(contractDetailGridBean.getEstimatedCompletionDate()));
contractDetailBean.setMilestoneId(contractDetailGridBean.getMilestoneId());
contractDetailBean.setMilestone(contractDetailGridBean.getMilestone());
contractDetailBean.setMilestoneStatus(contractDetailGridBean.getMilestoneStatus());
contractDetailBean.setReleaseAmount(contractDetailGridBean.getReleaseAmount());
contractDetailBean.setRemarks(contractDetailGridBean.getRemarks());
contractDetailBean.setRevisedCompletionDate(MisUtility.convertStringToDate(contractDetailGridBean.getRevisedCompletionDate()));
MISAuditBean misAuditBean2 = new MISAuditBean();
misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
misAuditBean2.setStatus(MISConstants.MASTER_STATUS_DELETED);
contractDetailBean.setMisAuditBean(misAuditBean2);
contractDetailBeans.add(contractDetailBean);			
}
Collection<ContractDetailGridBean> addedContractDetailBeans = contractManagementForm.getMilestoneDatagrid().getAddedData(); 
for (ContractDetailGridBean contractDetailGridBean : addedContractDetailBeans) {
	System.out.println("adddded");
	contractDetailBean = new ContractDetailBean();
	contractDetailBean.setContractId(contractManagementForm.getContractNo());
	contractDetailBean.setActualCompletionDate(MisUtility.convertStringToDate(contractDetailGridBean.getActualCompletionDate()));
	contractDetailBean.setAmountDue(contractDetailGridBean.getAmountDue());
	contractDetailBean.setAmountReleaseDate(MisUtility.convertStringToDate(contractDetailGridBean.getAmountReleaseDate()));
	contractDetailBean.setBillAmount(contractDetailGridBean.getBillAmount());
	contractDetailBean.setBillDate(MisUtility.convertStringToDate(contractDetailGridBean.getBillDate()));
	contractDetailBean.setBillNo(contractDetailGridBean.getBillNo());
	contractDetailBean.setEstimatedCompletionDate(MisUtility.convertStringToDate(contractDetailGridBean.getEstimatedCompletionDate()));
	contractDetailBean.setMilestoneId(contractDetailGridBean.getMilestoneId());
	contractDetailBean.setMilestone(contractDetailGridBean.getMilestone());
	contractDetailBean.setMilestoneStatus(contractDetailGridBean.getMilestoneStatus());
	contractDetailBean.setReleaseAmount(contractDetailGridBean.getReleaseAmount());
	contractDetailBean.setRemarks(contractDetailGridBean.getRemarks());
	contractDetailBean.setRevisedCompletionDate(MisUtility.convertStringToDate(contractDetailGridBean.getRevisedCompletionDate()));
	contractDetailBean.setMisAuditBean(misAuditBean);
	contractDetailBeans.add(contractDetailBean);

}

} catch (Exception e) {
	log.error(e.getLocalizedMessage(),e);
	throw new MISException(e);
}
return contractDetailBeans;
}
	@SuppressWarnings("unchecked")
	private Collection<ContractDetailBean> generateContractDetailBeans1(ContractManagementForm contractManagementForm, MISSessionBean misSessionBean, String status) throws MISException{
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
	
List<ContractDetailBean> contractDetailBeans = new ArrayList<ContractDetailBean>();


try {			

Collection<ContractDetailGridBean> deletedContractDetailBeans = contractManagementForm.getMilestoneDatagrid().getDeletedData();
ContractDetailBean contractDetailBean = null;
if(!MisUtility.ifEmpty(deletedContractDetailBeans))
{
System.out.println("deleted");
for (ContractDetailGridBean contractDetailGridBean : deletedContractDetailBeans) {
contractDetailBean = new ContractDetailBean();
contractDetailBean.setContractId(deletedContractDetailBeans.iterator().next().getContractId());
contractDetailBean.setActualCompletionDate(MisUtility.convertStringToDate(contractManagementForm.getActualCompletionDate()));
contractDetailBean.setAmountDue(contractDetailGridBean.getAmountDue());
contractDetailBean.setAmountReleaseDate(MisUtility.convertStringToDate(contractDetailGridBean.getAmountReleaseDate()));
contractDetailBean.setBillAmount(contractDetailGridBean.getBillAmount());
contractDetailBean.setBillDate(MisUtility.convertStringToDate(contractDetailGridBean.getBillDate()));
contractDetailBean.setBillNo(contractDetailGridBean.getBillNo());
contractDetailBean.setEstimatedCompletionDate(MisUtility.convertStringToDate(contractDetailGridBean.getEstimatedCompletionDate()));
contractDetailBean.setMilestoneId(contractDetailGridBean.getMilestoneId());
contractDetailBean.setMilestone(contractDetailGridBean.getMilestone());
contractDetailBean.setMilestoneStatus(contractDetailGridBean.getMilestoneStatus());
contractDetailBean.setReleaseAmount(contractDetailGridBean.getReleaseAmount());
contractDetailBean.setRemarks(contractDetailGridBean.getRemarks());
contractDetailBean.setRevisedCompletionDate(MisUtility.convertStringToDate(contractDetailGridBean.getRevisedCompletionDate()));
misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
contractDetailBean.setMisAuditBean(misAuditBean);
contractDetailBeans.add(contractDetailBean);			
}
}
} catch (Exception e) {
	log.error(e.getLocalizedMessage(),e);
	throw new MISException(e);
}

return contractDetailBeans;
}
	@SuppressWarnings("unchecked")
	private Collection<ContractDetailBean> generateContractDetailBeans2(ContractManagementForm contractManagementForm, MISSessionBean misSessionBean, String status) throws MISException{
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
	
List<ContractDetailBean> contractDetailBeans = new ArrayList<ContractDetailBean>();


try {			

Collection<ContractDetailGridBean> addedContractDetailBeans = contractManagementForm.getMilestoneDatagrid().getAddedData(); 
ContractDetailBean contractDetailBean = null;


for (ContractDetailGridBean contractDetailGridBean : addedContractDetailBeans) {
	System.out.println("adddded");
	contractDetailBean = new ContractDetailBean();
	contractDetailBean.setContractId(contractManagementForm.getContractNo());
	contractDetailBean.setActualCompletionDate(MisUtility.convertStringToDate(contractDetailGridBean.getActualCompletionDate()));
	contractDetailBean.setAmountDue(contractDetailGridBean.getAmountDue());
	contractDetailBean.setAmountReleaseDate(MisUtility.convertStringToDate(contractDetailGridBean.getAmountReleaseDate()));
	contractDetailBean.setBillAmount(contractDetailGridBean.getBillAmount());
	contractDetailBean.setBillDate(MisUtility.convertStringToDate(contractDetailGridBean.getBillDate()));
	contractDetailBean.setBillNo(contractDetailGridBean.getBillNo());
	contractDetailBean.setEstimatedCompletionDate(MisUtility.convertStringToDate(contractDetailGridBean.getEstimatedCompletionDate()));
	contractDetailBean.setMilestoneId(contractDetailGridBean.getMilestoneId());
	contractDetailBean.setMilestone(contractDetailGridBean.getMilestone());
	contractDetailBean.setMilestoneStatus(contractDetailGridBean.getMilestoneStatus());
	contractDetailBean.setReleaseAmount(contractDetailGridBean.getReleaseAmount());
	contractDetailBean.setRemarks(contractDetailGridBean.getRemarks());
	contractDetailBean.setRevisedCompletionDate(MisUtility.convertStringToDate(contractDetailGridBean.getRevisedCompletionDate()));
	contractDetailBean.setMisAuditBean(misAuditBean);
	contractDetailBeans.add(contractDetailBean);

}
		
} catch (Exception e) {
	log.error(e.getLocalizedMessage(),e);
	throw new MISException(e);
}

return contractDetailBeans;
}


}
