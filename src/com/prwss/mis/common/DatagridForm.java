package com.prwss.mis.common;

import org.apache.struts.action.ActionForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class DatagridForm extends ActionForm {
	
	
	/**
	 * Test Version
	 */
	private static final long serialVersionUID = -2700803704742752362L;
	private Datagrid datagrid;

	public Datagrid getDatagrid() {
		return datagrid;
	}

	public void setDatagrid(Datagrid datagrid) {
		this.datagrid = datagrid;
	}
	
	
	

}
