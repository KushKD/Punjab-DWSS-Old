/**
 * 
 */
package com.prwss.mis.masters.component.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;


/**
 * @author vgadiraju
 *
 */

@Entity
@Table (name="mst_component", schema="prwss_main" )
public class ComponentBean implements Serializable, Comparable<ComponentBean>{

	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -7169224713575431078L;

	@Id
	@Column(name="component_id", nullable=false)
	private String componentId;
	
	@Column(name="component_name", nullable=false)
	private String componentName;
	
	@Column(name="component_desc")
	private String componentDescription;
	@Embedded
	private MISAuditBean misAuditBean;

	
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getComponentDescription() {
		return componentDescription;
	}
	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("*********ComponentBean*********");
		buffer.append("\ncomponentId\t"+componentId);
		buffer.append("\ncomponentName\t"+componentName);
		buffer.append("\ncomponentDescription\t"+componentDescription);
//		buffer.append("\nEnteredBy\t"+entBy);
//		buffer.append("\nEnteredDate\t"+entDate);
//		buffer.append("\nauthorizedBy\t"+authBy);
//		buffer.append("\nauthorizedDate\t"+authDate);
//		buffer.append("\freezedBy\t"+freezedBy);
//		buffer.append("\freezedDate\t"+freezedDate);
//		buffer.append("\nstatus\t"+status);
		buffer.append("\n********************************\n");
		return buffer.toString();
	}
	
	@Override
	public int compareTo(ComponentBean o) {
		
		return this.componentId.compareTo(o.getComponentId());
	}
	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}
	
}
