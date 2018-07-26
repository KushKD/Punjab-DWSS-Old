/**
 * 
 */
package com.prwss.mis.masters.committee;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.committee.dao.CommitteeBankBean;
import com.prwss.mis.masters.committee.dao.CommitteeBean;
import com.prwss.mis.masters.committee.struts.CommitteeForm;

/**
 * @author pjha
 *
 */
public interface CommitteeBO {

	public List<CommitteeBean> findCommittee(CommitteeForm committeeForm, List<String> statusList) throws MISException;
	public long saveCommittee(CommitteeForm committeeForm, MISSessionBean misSessionBean) throws MISException;	
	public boolean updateCommittee(CommitteeForm committeeForm, MISSessionBean misSessionBean) throws MISException;	
	public boolean deleteCommittee(CommitteeForm committeeForm, MISSessionBean misSessionBean) throws MISException;	
	public boolean postCommittee(CommitteeForm committeeForm, MISSessionBean misSessionBean) throws MISException;
}
