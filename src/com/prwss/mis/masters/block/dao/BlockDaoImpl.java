package com.prwss.mis.masters.block.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.constituency.dao.ConstituencyDaoImpl;

public class BlockDaoImpl extends HibernateDaoSupport implements BlockDao {


	private Logger log = Logger.getLogger(ConstituencyDaoImpl.class);

	
	@SuppressWarnings("unchecked")
	@Override
	public Set<BlockBean> getDistinctBlockCodes(String locationId) throws DataAccessException {
	
		Set<BlockBean> blockBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BlockBean.class);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			criteria.add(Restrictions.in("misAuditBean.status",statusList));
			criteria.add(Restrictions.eq("locationId", locationId).ignoreCase());
			blockBeans = new TreeSet<BlockBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return blockBeans;
	}
	@SuppressWarnings({"unchecked" })
	@Override
	public List<BlockBean> findBlock(BlockBean blockBean, List<String> statusList)
			throws DataAccessException {
		List<BlockBean> blockBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BlockBean.class);
			if(MisUtility.ifEmpty(blockBean)){			
				if(MisUtility.ifEmpty(blockBean.getBlockId())){
					System.out.println("--------BlockId"+blockBean.getBlockId());
					criteria.add(Restrictions.eq("blockId", blockBean.getBlockId()));
				}
				if(MisUtility.ifEmpty(blockBean.getBlockName()))
					criteria.add(Restrictions.eq("blockName", blockBean.getBlockName()));
				if(MisUtility.ifEmpty(blockBean.getLocationId()) && MisUtility.ifEmpty(blockBean.getLocationId())){
					System.out.println("-------------DistrictId"+blockBean.getLocationId());
					criteria.add(Restrictions.eq("locationId", blockBean.getLocationId()));
				}
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				blockBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return blockBeans;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BlockBean> findBlock(List<String> blockIds) throws DataAccessException {
		List<BlockBean> blockBeans = null;
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BlockBean.class);
			
			if(!MisUtility.ifEmpty(blockIds)){
				criteria.add(Restrictions.in("blockId", blockIds));
				blockBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}		
		
		return blockBeans;
	}

	
	@Override
	public boolean saveBlock(BlockBean blockBean) throws DataAccessException {
		
		try {
			getHibernateTemplate().save(blockBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateBlock(BlockBean blockBean) throws DataAccessException {
		try {
			getHibernateTemplate().update(blockBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateBlock(List<BlockBean> blockBeans) throws DataAccessException {
		
		try {
			getHibernateTemplate().saveOrUpdateAll(blockBeans);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

}
