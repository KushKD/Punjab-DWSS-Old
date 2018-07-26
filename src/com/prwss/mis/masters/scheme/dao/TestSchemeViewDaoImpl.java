package com.prwss.mis.masters.scheme.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class TestSchemeViewDaoImpl extends HibernateDaoSupport implements TestSchemeViewDao {
private Logger log = Logger.getLogger(TestSchemeViewDaoImpl.class);
	@Override
	public List<TestSchemeViewBean> finTestView(
			TestSchemeViewBean testSchemeViewBean, List<String> statusList)
			throws DataAccessException {
		List<TestSchemeViewBean> testSchemeViewBeans = new ArrayList<TestSchemeViewBean>();
		try {
			
			DetachedCriteria criteria = DetachedCriteria.forClass(TestSchemeViewBean.class);
			if(MisUtility.ifEmpty(testSchemeViewBean.getDistrictId()))
				criteria.add(Restrictions.eq("districtId", testSchemeViewBean.getDistrictId()));
			testSchemeViewBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return testSchemeViewBeans;
	}

}
