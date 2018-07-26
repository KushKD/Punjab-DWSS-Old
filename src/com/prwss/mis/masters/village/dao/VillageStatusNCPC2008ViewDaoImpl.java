package com.prwss.mis.masters.village.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class VillageStatusNCPC2008ViewDaoImpl extends HibernateDaoSupport implements VillageStatusNCPC2008ViewDao {
	private Logger log = Logger.getLogger(VillageStatusNCPC2008ViewDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VillageStatusNCPCView2008Bean> find(
			VillageStatusNCPCView2008Bean bean) throws DataAccessException {
		List<VillageStatusNCPCView2008Bean> beans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(VillageStatusNCPCView2008Bean.class);
			criteria.add(Restrictions.eq("villageId", bean.getVillageId()).ignoreCase());
			//criteria.add(Restrictions.eq("blockId", blockId).ignoreCase());
			beans = getHibernateTemplate().findByCriteria(criteria);
		
			
			return beans;
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
			
		}
		
	}

}
