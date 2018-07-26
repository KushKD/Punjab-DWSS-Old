package com.prwss.mis.masters.village.struts;

import org.apache.struts.validator.ValidatorForm;

public class VillageProgramImplForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4264434869019112429L;
	private String villageId;
	private String prgm_to_be_impl_pc_vill_habit_rws;
	private String prgm_to_be_impl_pc_vill_habit_arp;
	private String prgm_to_be_impl_pc_vill_habit_qp;
	private String prgm_to_be_impl_pc_vill_habit_wb_ada;
	private String prgm_to_be_impl_pc_vill_habit_nabard_xii;
	private String prgm_to_be_impl_nc_vill_habit_rws;
	private String prgm_to_be_impl_nc_vill_habit_arp;
	private String prgm_to_be_impl_nc_vill_habit_qp;
	private String prgm_to_be_impl_nc_vill_habit_wb_ada;
	private String prgm_to_be_impl_nc_vill_habit_nabard_xii;
	private String ncPcStatus;
	
	
	public String getNcPcStatus() {
		return ncPcStatus;
	}
	public void setNcPcStatus(String ncPcStatus) {
		this.ncPcStatus = ncPcStatus;
	}
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
		return "VillageProgramImplForm [villageId=" + villageId
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
				+ prgm_to_be_impl_nc_vill_habit_nabard_xii + ", ncPcStatus="
				+ ncPcStatus + "]";
	}
	
	
	
}
