package com.prwss.mis.masters.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.SpringContextLoader;
import com.prwss.mis.masters.employee.EmployeeBO;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeQualificationBean;
import com.prwss.mis.masters.employee.dao.EmploymentHistoryBean;
import com.prwss.mis.masters.employee.struts.EmployeeForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class TestEmployee extends TestCase {

	EmployeeBO employeeBO;
	MISSessionBean misAuditBean;

	@Before
	public void setUp() throws Exception {
		employeeBO = (EmployeeBO)SpringContextLoader.getBean("employeeBO");
		misAuditBean = new MISSessionBean();
	}

//	public void testFindEmployee() {
//		EmployeeForm employeeForm = new EmployeeForm();
//		employeeForm.setEmployeeId(employeeForm.getEmployeeId());
//		employeeForm.setEmployeeName("vinay");
//		try {
//			System.out.println(employeeBO.findEmployee(employeeForm, null));
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void testSaveEmployee() {
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.setEmployeeId(employeeForm.getEmployeeId());
		employeeForm.setEmployeeName("Admin");
		employeeForm.setEmployeeType("Permanent");
		employeeForm.setAddressLine1("3-12-117/A/6");
		employeeForm.setAddressLine2("Ganesh Nagar");
		employeeForm.setAppointmentNo("1002");
		employeeForm.setCity("Mohali");
//		employeeForm.setContractEndDate(new Date(System.currentTimeMillis()));
//		employeeForm.setContractStartDate(new Date(System.currentTimeMillis() - 10111));
//		employeeForm.setDateOfBirth(new Date(System.currentTimeMillis()));
		employeeForm.setDesignation("Consultant");
		employeeForm.setLocationId(employeeForm.getLocationId());
		employeeForm.setFatherName("G Padmanabham");
		employeeForm.setGender("M");
//		employeeForm.setJoiningDate(new Date(System.currentTimeMillis()));
		employeeForm.setMaritalStatus("Single");
		employeeForm.setMobilePhoneNo("98667L");
		employeeForm.setNationality("Indian");
		employeeForm.setOfficeEmail("vgadiraju@deloitte.com");
		employeeForm.setPanNo("JUNK JUNK");
		employeeForm.setPermanentEmployeeId(703910L);
		employeeForm.setPersonalEmail("vinaygadiraju@gmail.com");
		employeeForm.setPinCode("500013");
		employeeForm.setSanctionNo("Sanction 1");
		employeeForm.setState("PU");
		employeeForm.setStreet("Ramanthapur");
		employeeForm.setWorkPhoneNo("98667L");
		List<EmploymentHistoryBean> employeeHistoryBeans = 	new ArrayList<EmploymentHistoryBean>();
		EmploymentHistoryBean employmentHistoryBean = new EmploymentHistoryBean();
		employmentHistoryBean.setCompanyName("Sierra Atlantic Inc");
		
		EmployeeBean employeeBean = new EmployeeBean();
		employeeBean.setEmployeeId(29);
//		employmentHistoryBean.setEmployeeBean(employeeBean);
		employeeHistoryBeans.add(employmentHistoryBean);
		Datagrid employeeHistoryGrid = Datagrid.getInstance();
		employeeHistoryGrid.setData(employeeHistoryBeans);
		employeeForm.setEmployeeHistoryGrid(employeeHistoryGrid);
		List<EmployeeQualificationBean> employeeQualificationBeans = 	new ArrayList<EmployeeQualificationBean>();
		EmployeeQualificationBean emQualificationBean = new EmployeeQualificationBean();
//		emQualificationBean.setEmployeeBean(employeeBean);
		emQualificationBean.setDegree("BTEch");
		employeeQualificationBeans.add(emQualificationBean);
		Datagrid employeeQualificationGrid = Datagrid.getInstance();
		employeeQualificationGrid.setData(employeeQualificationBeans);
		employeeForm.setEmployeeQualificationGrid(employeeQualificationGrid);
		
		try {
			System.out.println(employeeBO.saveEmployee(employeeForm, misAuditBean));
		} catch (MISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public void testUpdateEmployee() {
//		fail("Not yet implemented"); // TODO
//	}

//	public void testDeleteEmployee() {
//		fail("Not yet implemented"); // TODO
//	}

//	public void testPostEmployee() {
//		fail("Not yet implemented"); // TODO
//	}

}
