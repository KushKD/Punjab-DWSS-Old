package com.prwss.mis.pmm.village.population.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class VillagePopulationDaoImpl extends HibernateDaoSupport implements VillagePopulationDao {
	private Logger log = Logger.getLogger(VillagePopulationDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<VillagePopulationBean> findVillagePopulation(VillagePopulationBean villagePopulationBean,
			List<String> statusList) throws DataAccessException {
		List<VillagePopulationBean> villagePopulationBeans = new ArrayList<VillagePopulationBean>() ;
		try {
			if(MisUtility.ifEmpty(villagePopulationBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(VillagePopulationBean.class);
				if(MisUtility.ifEmpty(villagePopulationBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId", villagePopulationBean.getVillageId()));
				if(MisUtility.ifEmpty(villagePopulationBean.getPopulationId()))
					criteria.add(Restrictions.eq("populationId", villagePopulationBean.getPopulationId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				villagePopulationBeans = getHibernateTemplate().findByCriteria(criteria);
				System.out.println("Village populateion from Dao"+villagePopulationBeans);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		
		return villagePopulationBeans;
	}

	@Override
	public boolean saveVillagePopulation(Collection<VillagePopulationBean> villagePopulationBeans) throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(villagePopulationBeans);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean saveOrUpdateVillagePopulation(Collection<VillagePopulationBean> villagePopulationBeans) throws DataAccessException {

		try {
			getHibernateTemplate().saveOrUpdateAll(villagePopulationBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	

}
