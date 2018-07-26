package com.prwss.mis.masters.employee.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="mst_designation", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class EmployeeDesignationBean implements Serializable{
	
	private static final long serialVersionUID = 222L;
	
@Id
@Column(name = "desig_id", nullable = false)
private int designationId;

@Column(name = "desig_name")
private String designationName;

@Column(name = "employee_type")
private String employeeType;

@Column(name = "hierarchy_level")
private long hierarchyLevel;

public int getDesignationId() {
	return designationId;
}

public void setDesignationId(int designationId) {
	this.designationId = designationId;
}

public String getDesignationName() {
	return designationName;
}

public void setDesignationName(String designationName) {
	this.designationName = designationName;
}

public String getEmployeeType() {
	return employeeType;
}

public void setEmployeeType(String employeeType) {
	this.employeeType = employeeType;
}

/*@Override
public int compareTo(EmployeeDesignationBean o) {
	 
	return this.designationId.trim().compareTo(o.designationId.trim());
}*/
 
@Override
public String toString() {
	 
	return "EmployeeDesignationBean [designationId="+ designationId +",designationName="+ designationName +",employeeType="+ employeeType +",hierarchyLevel ="+hierarchyLevel+"]";
}

public void setHierarchyLevel(long hierarchyLevel) {
	this.hierarchyLevel = hierarchyLevel;
}

public long getHierarchyLevel() {
	return hierarchyLevel;
}


}
