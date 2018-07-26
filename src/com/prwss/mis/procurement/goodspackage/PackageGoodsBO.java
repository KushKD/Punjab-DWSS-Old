package com.prwss.mis.procurement.goodspackage;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.procurement.goodspackage.struts.PackageGoodsForm;
import com.prwss.mis.procurement.packageheader.dao.PackageDetailBean;

public interface PackageGoodsBO {
	
public PackageDetailBean findPackageGoodsFrom(PackageGoodsForm packageGoodsForm, List<String> statusList) throws MISException;
	
	public boolean savePackageGoodsFrom(PackageGoodsForm packageGoodsForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updatePackageGoodsFrom(PackageGoodsForm packageGoodsForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean deletePackageGoodsFrom(PackageGoodsForm packageGoodsForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean postPackageGoodsFrom(PackageGoodsForm packageGoodsForm, MISSessionBean misSessionBean) throws MISException;


}
