package com.prwss.mis.procurement.goodspackage.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.procurement.goodspackage.PackageGoodsBean;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderBean;

public interface PackageGoodsDao {
	
	public  List<PackageGoodsBean> findGoodsPackage(PackageGoodsBean packageGoodsBean ,List<String> statusList) throws DataAccessException;

	public boolean saveGoodsPackage(PackageGoodsBean packageGoodsBean) throws DataAccessException;

	public boolean updateGoodsPackage(PackageGoodsBean packageGoodsBean) throws DataAccessException;
	
	public Set<PackageGoodsBean> getPackageIds(String schemeId)  throws DataAccessException;

}
