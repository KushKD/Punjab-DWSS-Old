package com.prwss.mis.pmm.village.tariff.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.prwss.mis.common.util.MisUtility;

public class VillageTariffDaoImpl extends HibernateDaoSupport implements VillageTariffDao {
	private Logger log = Logger.getLogger(VillageTariffDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<VillageTariffBean> findVillageTariff(VillageTariffBean villageTariffBean, List<String> statusList)
			throws DataAccessException {
		List<VillageTariffBean> villageTariffBeans = new ArrayList<VillageTariffBean>() ;
		try {
			if(MisUtility.ifEmpty(villageTariffBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(VillageTariffBean.class);
				if(MisUtility.ifEmpty(villageTariffBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId", villageTariffBean.getVillageId()));
				if(MisUtility.ifEmpty(villageTariffBean.getTariffId()))
					criteria.add(Restrictions.eq("tariffId", villageTariffBean.getTariffId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				villageTariffBeans = getHibernateTemplate().findByCriteria(criteria);
				System.out.println("Viullage Tariff from dao"+villageTariffBeans);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		
		return villageTariffBeans;
	}

	@Override
	public boolean saveVillageTariff(Collection<VillageTariffBean> villageTariffBeans) throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(villageTariffBeans);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean saveOrUpdateVillageTariff(Collection<VillageTariffBean> villageTariffBeans) throws DataAccessException {


		try {
			getHibernateTemplate().saveOrUpdateAll(villageTariffBeans);
		} catch (DataAccessException e) {
			throw e;
		}
			
		return true;
	}

	

}
