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
public interface  CommitteeMemberDao {
	
public List<CommitteeMemberBean> findCommitteeMember(CommitteeMemberBean committeeMemberBean, List<String> statusList) throws DataAccessException;
	
public boolean saveOrUpdateCommitteeMember(Collection<CommitteeMemberBean> committeeMemberBeans) throws DataAccessException;



}
