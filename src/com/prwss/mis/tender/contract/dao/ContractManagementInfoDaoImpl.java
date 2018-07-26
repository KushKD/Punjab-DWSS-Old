package com.prwss.mis.tender.contract.dao;

  
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

 
import com.prwss.mis.common.util.MisUtility;

public class ContractManagementInfoDaoImpl extends HibernateDaoSupport implements ContractManagementInfoDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractManagementInfoBean> getContractCodes(String locationId) throws DataAccessException {
		List<ContractManagementInfoBean> contractManagementInfoBeans = null;
		try {
		//	System.out.println("locaton+++++++++++++++"+locationId);
				System.out.println("heloo");
				DetachedCriteria criteria = DetachedCriteria.forClass(ContractManagementInfoBean.class);
			//	DetachedCriteria criteria = DetachedCriteria.forClass(ContractManagementInfoBean.class);
				if(MisUtility.ifEmpty(locationId)){
					//System.out.println("rrrrrrrrrrrr");
					criteria.add(Restrictions.eq("locationId",locationId));
					//System.out.println("tttttt");
				}
			//	System.out.println(2222);
				contractManagementInfoBeans = new ArrayList<ContractManagementInfoBean>(getHibernateTemplate().findByCriteria(criteria));
				
				//System.out.println(111111111);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
	//	System.out.println(contractManagementInfoBeans);
		return contractManagementInfoBeans;
 
	}

}
