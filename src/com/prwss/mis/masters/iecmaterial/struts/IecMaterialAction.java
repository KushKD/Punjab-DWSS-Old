package com.prwss.mis.masters.iecmaterial.struts;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class IecMaterialAction extends LookupDispatchAction {

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		IecMaterialForm IecMaterialForm = (IecMaterialForm)form;
		System.out.println(IecMaterialForm.getIecMaterialName());
		
		return mapping.findForward("display");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		// TODO Auto-generated method stub
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		return mapping.findForward("display");
	}
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("Unspecified____________IEC MAterial");
		this.setAttrib(request);
		
		return mapping.findForward("display");
	}
	
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("op", "ttttttttttt");
		request.setAttribute("d__mode", request.getParameter("d__mode"));

	}
	
	

	protected Map<String, String> getKeyMethodMap() {

		Map<String, String> map = new HashMap<String, String>();

		map.put("IecMaterialForm.find", "find");
		map.put("IecMaterialForm.delete", "delete");
		map.put("IecMaterialForm.save", "save");
		map.put("IecMaterialForm.update", "update");
		// TODO Auto-generated method stub
		return map;
	}

}
