package com.prwss.mis.pmm.village.connection.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class VillageConnectionDaoImpl extends HibernateDaoSupport implements VillageConnectionDao {
	private Logger log = Logger.getLogger(VillageConnectionDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<VillageConnectionBean> findVillageConnection(VillageConnectionBean villageConnectionBean,
			List<String> statusList) throws DataAccessException {
		List<VillageConnectionBean> villageConnectionBeans = new ArrayList<VillageConnectionBean>() ;
		try {
			if(MisUtility.ifEmpty(villageConnectionBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(VillageConnectionBean.class);
				if(MisUtility.ifEmpty(villageConnectionBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId", villageConnectionBean.getVillageId()));
				if(MisUtility.ifEmpty(villageConnectionBean.getConnectionId()))
					criteria.add(Restrictions.eq("connectionId", villageConnectionBean.getConnectionId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				villageConnectionBeans = getHibernateTemplate().findByCriteria(criteria);
				System.out.println("Village Connect from Dao"+villageConnectionBeans);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return villageConnectionBeans;
	}

	@Override
	public boolean saveVillageConnection(Collection<VillageConnectionBean> villageConnectionBeans) throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(villageConnectionBeans);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean  saveOrUpdateVillageConnection(Collection <VillageConnectionBean> villageConnectionBeans) throws DataAccessException {
		
		try {
			getHibernateTemplate().saveOrUpdateAll(villageConnectionBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	/*@Override
	public boolean deleteVillageConnection(
			Collection<VillageConnectionBean> villageConnectionBeans)
			throws DataAccessException {
		
		return false;
	}*/

}
