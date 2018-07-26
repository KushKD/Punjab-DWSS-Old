package com.prwss.mis.tender.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.SpringContextLoader;
import com.prwss.mis.tender.ContractBO;
import com.prwss.mis.tender.contract.dao.ContractHeaderBean;
import com.prwss.mis.tender.struts.ContractManagementForm;

public class TestContract extends TestCase {
	
	private ContractBO contractBO;
	private MISSessionBean misAuditBean;

	protected void setUp() throws Exception {
		contractBO = (ContractBO)SpringContextLoader.getBean("contractBO");
		misAuditBean = new MISSessionBean();
	}

	public void testFindContract() {
		ContractManagementForm contractManagementForm = new ContractManagementForm();
		contractManagementForm.setContractNo("1");
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
//		statusList.add(MISConstants.MASTER_STATUS_FREEZED);
//		statusList.add(MISConstants.MASTER_STATUS_DELETED);
		try {
			List<ContractHeaderBean> contractHeaderBeans = contractBO.findContract(contractManagementForm, statusList);
//			for (ContractHeaderBean contractHeaderBean : contractHeaderBeans) {
//				System.out.println(contractHeaderBean.getContractDetailBeans());
//			}
			System.out.println(contractHeaderBeans.get(0).getContractDetailBeans());
//			System.out.println(contractBO.findContract(contractManagementForm, statusList));
		} catch (MISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public void testSaveContract() {
//		
//	}

//	public void testUpdateContract() {
//		Calendar cal = GregorianCalendar.getInstance();
//		cal.set(2010, 12, 07);//2010-12-07
//		Date d = new Date(cal.getTime().getTime());
//		ContractManagementForm contractManagementForm = new ContractManagementForm();
//		contractManagementForm.setContractNo("1");
//		contractManagementForm.setContractDate(d);
//		Datagrid milestoneDatagrid = Datagrid.getInstance();
//		List<ContractDetailBean> contractDetailBeans = new ArrayList<ContractDetailBean>();
//		ContractDetailBean contractDetailBean = new ContractDetailBean();
//		contractDetailBean.setContractId("1");
//		contractDetailBean.setMilestone("1");
//		contractDetailBean.setActualCompletionDate(d);
//		contractDetailBean.setAmountDue(111.00);
//		contractDetailBean.setBillAmount(222.0);
//		contractDetailBean.setBillDate(d);
//		contractDetailBeans.add(contractDetailBean);
//		milestoneDatagrid.setDataState(0, Datagrid.SELECTED);
//		milestoneDatagrid.setData(contractDetailBeans);
//		milestoneDatagrid.setDataClass(ContractDetailBean.class);
//		contractManagementForm.setMilestoneDatagrid(milestoneDatagrid);
//		contractManagementForm.setVendorId("A");
//		try {
//			contractBO.updateContract(contractManagementForm, misAuditBean);
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public void testDeleteContract() {
//		fail("Not yet implemented"); // TODO
//	}

}
