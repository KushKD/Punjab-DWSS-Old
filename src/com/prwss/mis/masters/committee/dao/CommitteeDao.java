/**
 * 
 */
package com.prwss.mis.masters.committee.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * @author pjha
 *
 */
public interface CommitteeDao {
    public List<CommitteeBean> findCommittee(CommitteeBean committeeBean, List<String> statusList) throws DataAccessException;
	
	public List<CommitteeBean> findCommittee(List<String> committeeId) throws DataAccessException;

	public long saveCommittee(CommitteeBean committeeBean) throws DataAccessException;

	public boolean updateCommittee(CommitteeBean committeeBean)	throws DataAccessException;
	
	public boolean updateCommittee(List<CommitteeBean> committeeBeans) throws DataAccessException;

	

}
