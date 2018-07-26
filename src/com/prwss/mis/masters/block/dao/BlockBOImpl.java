package com.prwss.mis.masters.block.dao;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.admin.divisional.dao.DivisionalBean;
import com.prwss.mis.admin.struts.BlockForm;
import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.circle.dao.CircleBean;
import com.prwss.mis.masters.constituency.dao.ConstituencyBean;
import com.prwss.mis.masters.district.dao.DistrictBean;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;

public class BlockBOImpl implements BlockBO{
	private Logger log = Logger.getLogger(BlockBOImpl.class);
	private BlockDao blockDao;
	private LocationDao locationDao;
	
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}
	
	public void setBlockDao(BlockDao blockDao) {
		this.blockDao = blockDao;
	}

	@Override
	public List<BlockBean> findBlock(BlockForm blockForm, List<String> statusList) throws MISException {
		List<BlockBean> blockBeans = null;
		try {
			BlockBean blockBean = new BlockBean();
			blockBean.setBlockId(blockForm.getBlockId());
			
			DistrictBean district = new DistrictBean();
			district.setDistrictId(blockForm.getDistrictId());
			blockBean.setLocationId(district.getDistrictId());
			blockBeans = blockDao.findBlock(blockBean, statusList);
			System.out.println("----block Bean"+blockBeans.size());
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return blockBeans;
	}

	@Override
	public boolean saveBlock(BlockForm blockForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		List<BlockBean> blockBeans = null;
		try {
			/*BlockBean blockBean = populateBlockBean(blockForm);		
			blockBean.setBlockId(blockBean.getBlockId());
			String blockName = blockBean.getBlockName();
			blockBeans = blockDao.findBlock(blockBean, null);
			if(!MisUtility.ifEmpty(blockBeans)){					
				StringBuffer message = new StringBuffer();
				message.append(" Block Id ").append(blockBean.getBlockId());
				if(blockName.equalsIgnoreCase(blockBeans.get(0).getBlockName())){
					message.append(" and Block Name ").append(blockName);
				}
				log.debug("Duplicate Entry for "+blockBean.getBlockId()+" and "+ blockBean.getBlockName());
				log.debug("Block Already exist \n"+blockBeans);
				throw new MISException(MISExceptionCodes.MIS001 , message.toString());
			}
			blockBean.setLocationId(blockForm.getDistrictId());
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			blockBean.setMisAuditBean(misAuditBean);
			status = blockDao.saveBlock(blockBean);*/
			BlockBean blockBean = new BlockBean();
			blockBean.setBlockId("");
			blockBean.setBlockName("");
			//blockBean.setLocationId(blockForm.getDistrictId());
			BlockBean blockBean2 = new BlockBean();
			
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList.add(MISConstants.MASTER_STATUS_DELETED);
			blockBeans = blockDao.findBlock(blockBean, statusList);
			if(!MisUtility.ifEmpty(blockBeans)){
				for(BlockBean blockBean3:blockBeans){
					if(blockForm.getBlockName().equalsIgnoreCase(blockBean3.getBlockName())){
						StringBuffer message = new StringBuffer();
						message.append(" Block Name ").append(blockForm.getBlockName());
						log.debug("Duplicate Entry for "+blockForm.getBlockId()+" and "+ blockForm.getBlockName());
						log.debug("Block Already exist \n"+blockBeans);
						throw new MISException(MISExceptionCodes.MIS001 , message.toString());		
					}
				}
			}
				System.out.println("Size is "+blockBeans.size());
				if(blockBeans.size()%10==0){
					blockBean2.setBlockId(blockForm.getDistrictId()+"B"+(blockBeans.size()+1));
				}
				else{
					BlockBean bean = blockBeans.get(blockBeans.size()-1);
					String id = bean.getConstituencyId().substring(3);
					int lastId = blockBeans.size()+1;
					System.out.println("String Id is "+id+" lastId Created "+lastId);
					if(lastId<10){
						blockBean2.setBlockId(blockForm.getDistrictId()+"B0"+lastId);
					}
					else{
						blockBean2.setBlockId(blockForm.getDistrictId()+"B"+lastId);	
					}
				}
			
			blockBean2.setBlockName(blockForm.getBlockName());			
			blockBean2.setLocationId(blockForm.getDistrictId());
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			blockBean2.setMisAuditBean(misAuditBean);
			status = blockDao.saveBlock(blockBean2);
		} catch (DataAccessException e) {
			throw new MISException(e);
		}		
		return status;	
		
	}

	@Override
	public boolean updateBlock(BlockForm blockForm, MISSessionBean misSessionBean,List<String> statusList) throws MISException {
		boolean status = false;
		List<BlockBean> blockBeans = null;
		try {
			BlockBean blockBean = populateBlockBean(blockForm);		
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(statusList.get(0));
			blockBean.setMisAuditBean(misAuditBean);
			
			BlockBean bean = new BlockBean();
			bean.setBlockId("");
			bean.setBlockName("");
			
			List<String> statusList1 = new ArrayList<String>();
			statusList1.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList1.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList1.add(MISConstants.MASTER_STATUS_DELETED);
			blockBeans = blockDao.findBlock(bean, statusList1);
			if(!MisUtility.ifEmpty(blockBeans)){	
				for(BlockBean blockBean3:blockBeans){
					if(blockForm.getBlockName().equalsIgnoreCase(blockBean3.getBlockName())){
						StringBuffer message = new StringBuffer();
						message.append(" Block Name ").append(blockForm.getBlockName());
						log.debug("Duplicate Entry for "+blockForm.getBlockId()+" and "+ blockForm.getBlockName());
						log.debug("Block Already exist \n"+blockBeans);
						
						throw new MISException(MISExceptionCodes.MIS001 , message.toString());		
					}
				}
			}			
			
			status = blockDao.updateBlock(blockBean);
		} catch (DataAccessException e) {
			throw new MISException(e);
		}	
		return status;
	}

	@Override
	public boolean deleteBlock(BlockForm blockForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false; 
		try {
			BlockBean bean = new BlockBean();
			bean.setBlockId(blockForm.getBlockId());
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			List<BlockBean> blockBeans = blockDao.findBlock(bean,statusList);
			for (BlockBean blockBean : blockBeans) {
				MISAuditBean misAuditBean = new MISAuditBean();
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				blockBean.setMisAuditBean(misAuditBean);
				}
			status = blockDao.updateBlock(blockBeans);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}

		return status;
	}

	@Override
	public boolean postBlock(BlockForm blockForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false; 
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		BlockBean bean = new BlockBean();
		bean.setBlockId(blockForm.getBlockId());
		LocationBean locationBean = null;
		try {
			List<BlockBean> blockBeans = blockDao.findBlock(bean,statusList);
			for (BlockBean blockBean : blockBeans) {
				MISAuditBean misAuditBean = new MISAuditBean();
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
				blockBean.setMisAuditBean(misAuditBean);
				}
			status = blockDao.updateBlock(blockBeans);
			for (BlockBean blockBean : blockBeans) {
				locationBean = new LocationBean();
				locationBean.setLocationId(blockBean.getBlockId());
				locationBean.setLocationName(blockBean.getBlockName());
				locationBean.setParentLocation(blockBean.getLocationId());
				locationBean.setLocationType("BLOCK");
				MISAuditBean auditBean = new MISAuditBean();
				auditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				auditBean.setEntBy(misSessionBean.getEnteredBy());
				auditBean.setEntDate(misSessionBean.getEnteredDate());
				locationBean.setMisAuditBean(auditBean);
				locationDao.saveLocation(locationBean);
			}	
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}

		return status;
	}
	
	private BlockBean populateBlockBean(BlockForm blockForm){
		
		BlockBean blockBean = new BlockBean();
		
		blockBean.setBlockId(blockForm.getBlockId());
		blockBean.setBlockName(blockForm.getBlockName());
		DistrictBean district = new DistrictBean();
		district.setDistrictId(blockForm.getDistrictId());
		blockBean.setLocationId(district.getDistrictId());
		
		return blockBean;
	}

}
