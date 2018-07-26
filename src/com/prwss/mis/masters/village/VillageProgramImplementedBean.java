package com.prwss.mis.masters.village;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
@Entity
@Table(name="mst_village_programe_implemented_org_as2008", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class VillageProgramImplementedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5537984001786168531L;
	
	@Id
	@Column(name="mst_village_id")
	private String villageId;
	
	@Column(name="prgm_to_be_impl_pc_vill_habit_rws")
	private String prgm_to_be_impl_pc_vill_habit_rws;
	
	@Column(name="prgm_to_be_impl_pc_vill_habit_arp")
	private String prgm_to_be_impl_pc_vill_habit_arp;
	
	@Column(name="prgm_to_be_impl_pc_vill_habit_qp")
	private String prgm_to_be_impl_pc_vill_habit_qp;
	
	@Column(name="prgm_to_be_impl_pc_vill_habit_wb_ada")
	private String prgm_to_be_impl_pc_vill_habit_wb_ada;
	
	@Column(name="prgm_to_be_impl_pc_vill_habit_nabard_xii")
	private String prgm_to_be_impl_pc_vill_habit_nabard_xii;
	
	@Column(name="prgm_to_be_impl_nc_vill_habit_rws")
	private String prgm_to_be_impl_nc_vill_habit_rws;
	
	@Column(name="prgm_to_be_impl_nc_vill_habit_arp")
	private String prgm_to_be_impl_nc_vill_habit_arp;
	
	@Column(name="prgm_to_be_impl_nc_vill_habit_qp")
	private String prgm_to_be_impl_nc_vill_habit_qp;
	
	@Column(name="prgm_to_be_impl_nc_vill_habit_wb_ada")
	private String prgm_to_be_impl_nc_vill_habit_wb_ada;
	
	@Column(name="prgm_to_be_impl_nc_vill_habit_nabard_xii")
	private String prgm_to_be_impl_nc_vill_habit_nabard_xii;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public String getPrgm_to_be_impl_pc_vill_habit_rws() {
		return prgm_to_be_impl_pc_vill_habit_rws;
	}

	public void setPrgm_to_be_impl_pc_vill_habit_rws(
			String prgm_to_be_impl_pc_vill_habit_rws) {
		this.prgm_to_be_impl_pc_vill_habit_rws = prgm_to_be_impl_pc_vill_habit_rws;
	}

	public String getPrgm_to_be_impl_pc_vill_habit_arp() {
		return prgm_to_be_impl_pc_vill_habit_arp;
	}

	public void setPrgm_to_be_impl_pc_vill_habit_arp(
			String prgm_to_be_impl_pc_vill_habit_arp) {
		this.prgm_to_be_impl_pc_vill_habit_arp = prgm_to_be_impl_pc_vill_habit_arp;
	}

	public String getPrgm_to_be_impl_pc_vill_habit_qp() {
		return prgm_to_be_impl_pc_vill_habit_qp;
	}

	public void setPrgm_to_be_impl_pc_vill_habit_qp(
			String prgm_to_be_impl_pc_vill_habit_qp) {
		this.prgm_to_be_impl_pc_vill_habit_qp = prgm_to_be_impl_pc_vill_habit_qp;
	}

	public String getPrgm_to_be_impl_pc_vill_habit_wb_ada() {
		return prgm_to_be_impl_pc_vill_habit_wb_ada;
	}

	public void setPrgm_to_be_impl_pc_vill_habit_wb_ada(
			String prgm_to_be_impl_pc_vill_habit_wb_ada) {
		this.prgm_to_be_impl_pc_vill_habit_wb_ada = prgm_to_be_impl_pc_vill_habit_wb_ada;
	}

	public String getPrgm_to_be_impl_pc_vill_habit_nabard_xii() {
		return prgm_to_be_impl_pc_vill_habit_nabard_xii;
	}

	public void setPrgm_to_be_impl_pc_vill_habit_nabard_xii(
			String prgm_to_be_impl_pc_vill_habit_nabard_xii) {
		this.prgm_to_be_impl_pc_vill_habit_nabard_xii = prgm_to_be_impl_pc_vill_habit_nabard_xii;
	}

	public String getPrgm_to_be_impl_nc_vill_habit_rws() {
		return prgm_to_be_impl_nc_vill_habit_rws;
	}

	public void setPrgm_to_be_impl_nc_vill_habit_rws(
			String prgm_to_be_impl_nc_vill_habit_rws) {
		this.prgm_to_be_impl_nc_vill_habit_rws = prgm_to_be_impl_nc_vill_habit_rws;
	}

	public String getPrgm_to_be_impl_nc_vill_habit_arp() {
		return prgm_to_be_impl_nc_vill_habit_arp;
	}

	public void setPrgm_to_be_impl_nc_vill_habit_arp(
			String prgm_to_be_impl_nc_vill_habit_arp) {
		this.prgm_to_be_impl_nc_vill_habit_arp = prgm_to_be_impl_nc_vill_habit_arp;
	}

	public String getPrgm_to_be_impl_nc_vill_habit_qp() {
		return prgm_to_be_impl_nc_vill_habit_qp;
	}

	public void setPrgm_to_be_impl_nc_vill_habit_qp(
			String prgm_to_be_impl_nc_vill_habit_qp) {
		this.prgm_to_be_impl_nc_vill_habit_qp = prgm_to_be_impl_nc_vill_habit_qp;
	}

	public String getPrgm_to_be_impl_nc_vill_habit_wb_ada() {
		return prgm_to_be_impl_nc_vill_habit_wb_ada;
	}

	public void setPrgm_to_be_impl_nc_vill_habit_wb_ada(
			String prgm_to_be_impl_nc_vill_habit_wb_ada) {
		this.prgm_to_be_impl_nc_vill_habit_wb_ada = prgm_to_be_impl_nc_vill_habit_wb_ada;
	}

	public String getPrgm_to_be_impl_nc_vill_habit_nabard_xii() {
		return prgm_to_be_impl_nc_vill_habit_nabard_xii;
	}

	public void setPrgm_to_be_impl_nc_vill_habit_nabard_xii(
			String prgm_to_be_impl_nc_vill_habit_nabard_xii) {
		this.prgm_to_be_impl_nc_vill_habit_nabard_xii = prgm_to_be_impl_nc_vill_habit_nabard_xii;
	}

	@Override
	public String toString() {
		return "VillageProgramImplementedBean [villageId=" + villageId
				+ ", prgm_to_be_impl_pc_vill_habit_rws="
				+ prgm_to_be_impl_pc_vill_habit_rws
				+ ", prgm_to_be_impl_pc_vill_habit_arp="
				+ prgm_to_be_impl_pc_vill_habit_arp
				+ ", prgm_to_be_impl_pc_vill_habit_qp="
				+ prgm_to_be_impl_pc_vill_habit_qp
				+ ", prgm_to_be_impl_pc_vill_habit_wb_ada="
				+ prgm_to_be_impl_pc_vill_habit_wb_ada
				+ ", prgm_to_be_impl_pc_vill_habit_nabard_xii="
				+ prgm_to_be_impl_pc_vill_habit_nabard_xii
				+ ", prgm_to_be_impl_nc_vill_habit_rws="
				+ prgm_to_be_impl_nc_vill_habit_rws
				+ ", prgm_to_be_impl_nc_vill_habit_arp="
				+ prgm_to_be_impl_nc_vill_habit_arp
				+ ", prgm_to_be_impl_nc_vill_habit_qp="
				+ prgm_to_be_impl_nc_vill_habit_qp
				+ ", prgm_to_be_impl_nc_vill_habit_wb_ada="
				+ prgm_to_be_impl_nc_vill_habit_wb_ada
				+ ", prgm_to_be_impl_nc_vill_habit_nabard_xii="
				+ prgm_to_be_impl_nc_vill_habit_nabard_xii + ", misAuditBean="
				+ misAuditBean + "]";
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	
}
