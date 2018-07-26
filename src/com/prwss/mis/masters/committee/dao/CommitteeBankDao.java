/**
 * 
 */
package com.prwss.mis.masters.committee.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * @author pjha
 *
 */
public interface CommitteeBankDao {
	public List<CommitteeBankBean> findCommitteeBank(CommitteeBankBean committeeBankBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveOrUpdateCommitteeBank(Collection<CommitteeBankBean> committeeBankBeans) throws DataAccessException;


}
