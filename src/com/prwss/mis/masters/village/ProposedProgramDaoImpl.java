package com.prwss.mis.masters.village;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ProposedProgramDaoImpl extends HibernateDaoSupport implements ProposedProgramDao{

	@Override
	public boolean saveProposedProgram(ProposedProgramBean proposedProgramBean)
			throws DataAccessException {
		boolean status = false;
		try {
			System.out.println("inside Proposed Dao");
			 getHibernateTemplate().save(proposedProgramBean);
			System.out.println("--------Saved : "+status);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}

}
