package com.prwss.mis.masters.village.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.village.VillageProgramImplementedBean;

public class VillageProgramImplementedDaoImpl extends HibernateDaoSupport implements VillageProgramImplementedDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<VillageProgramImplementedBean> findVillageImplemented(
			VillageProgramImplementedBean villageProgramImplementedBean,
			List<String> statusList) throws DataAccessException {
		List<VillageProgramImplementedBean> programImplementedBeans = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(VillageProgramImplementedBean.class);
		if(MisUtility.ifEmpty(villageProgramImplementedBean))
		{
			if(MisUtility.ifEmpty(villageProgramImplementedBean.getVillageId())){
				criteria.add(Restrictions.eq("villageId", villageProgramImplementedBean.getVillageId()));
				
					if(!MisUtility.ifEmpty(statusList)){
						System.out.println("Hello Hi..."+statusList);
						criteria.add(Restrictions.in("misAuditBean.status", statusList));}
				programImplementedBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		}
		return programImplementedBeans;
		}
		
	

	@Override
	public boolean updateVillageImplemented(
			VillageProgramImplementedBean villageProgramImplementedBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().update(villageProgramImplementedBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean saveVillageImplemented(
			VillageProgramImplementedBean villageProgramImplementedBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(villageProgramImplementedBean);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

}
