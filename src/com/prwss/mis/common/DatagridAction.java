package com.prwss.mis.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.prwss.mis.masters.subcomponent.dao.SubComponentBean;
import com.prwss.mis.masters.subcomponent.dao.SubComponentDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class DatagridAction extends DispatchAction {
	
	private SubComponentDao subComponentDao;
	
	public ActionForward display(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {
			DatagridForm datagridForm = (DatagridForm)form;
			List<String> subComponentIdList = new ArrayList<String>();
			subComponentIdList.add("aa");
			subComponentIdList.add("abc");
			subComponentIdList.add("bb");
			subComponentIdList.add("CC");
			subComponentIdList.add("xy");

			List<SubComponentBean> list = subComponentDao.findSubComponent(subComponentIdList);
			Datagrid datagrid = Datagrid.getInstance();
			
			datagrid.setDataClass(SubComponentBean.class);
			datagrid.setData(list);
			datagridForm.setDatagrid(datagrid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapping.findForward("jsp");
	}
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return mapping.findForward("jsp");
	}

	public void setSubComponentDao(SubComponentDao subComponentDao) {
		this.subComponentDao = subComponentDao;
	}

}
