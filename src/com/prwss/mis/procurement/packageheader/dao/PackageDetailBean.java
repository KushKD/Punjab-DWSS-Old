package com.prwss.mis.procurement.packageheader.dao;

import java.io.Serializable;
import java.util.List;

import com.prwss.mis.procurement.goodspackage.PackageGoodsBean;
import com.prwss.mis.procurement.nonconsultancypackage.PackageNonConsultancyBean;
import com.prwss.mis.procurement.packagecomponents.dao.PackageComponentsBean;
import com.prwss.mis.procurement.servicespackage.PackageServicesBean;
import com.prwss.mis.procurement.workspackage.PackageWorksBean;

//This class is to hold details of 
//package header,works,goods,services,non-concultancy and package components
public class PackageDetailBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9171232129002463202L;
	
	private List<PackageHeaderBean> packageHeaderBeans;
	private List<PackageServicesBean> packageServicesBeans;
	private List<PackageWorksBean> packageWorksBeans;
	private List<PackageGoodsBean> packageGoodsBeans;
	private List<PackageComponentsBean> packageComponentsBeans;
	private List<PackageNonConsultancyBean> packageNonConsultancyBeans;
	
	public List<PackageNonConsultancyBean> getPackageNonConsultancyBeans() {
		return packageNonConsultancyBeans;
	}
	public void setPackageNonConsultancyBeans(
			List<PackageNonConsultancyBean> packageNonConsultancyBeans) {
		this.packageNonConsultancyBeans = packageNonConsultancyBeans;
	}
	public List<PackageHeaderBean> getPackageHeaderBeans() {
		return packageHeaderBeans;
	}
	public void setPackageHeaderBeans(List<PackageHeaderBean> packageHeaderBeans) {
		this.packageHeaderBeans = packageHeaderBeans;
	}
	public List<PackageServicesBean> getPackageServicesBeans() {
		return packageServicesBeans;
	}
	public void setPackageServicesBeans(
			List<PackageServicesBean> packageServicesBeans) {
		this.packageServicesBeans = packageServicesBeans;
	}
	public List<PackageWorksBean> getPackageWorksBeans() {
		return packageWorksBeans;
	}
	public void setPackageWorksBeans(List<PackageWorksBean> packageWorksBeans) {
		this.packageWorksBeans = packageWorksBeans;
	}
	public List<PackageGoodsBean> getPackageGoodsBeans() {
		return packageGoodsBeans;
	}
	public void setPackageGoodsBeans(List<PackageGoodsBean> packageGoodsBeans) {
		this.packageGoodsBeans = packageGoodsBeans;
	}
	public List<PackageComponentsBean> getPackageComponentsBeans() {
		return packageComponentsBeans;
	}
	public void setPackageComponentsBeans(
			List<PackageComponentsBean> packageComponentsBeans) {
		this.packageComponentsBeans = packageComponentsBeans;
	}
	@Override
	public String toString() {
		return "PackageDetailBean [packageHeaderBeans=" + packageHeaderBeans
				+ ", packageServicesBeans=" + packageServicesBeans
				+ ", packageWorksBeans=" + packageWorksBeans
				+ ", packageGoodsBeans=" + packageGoodsBeans
				+ ", packageComponentsBeans=" + packageComponentsBeans + "]";
	}
	
	
	 

}
