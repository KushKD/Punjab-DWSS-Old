package com.prwss.mis.ccdu;

import java.io.Serializable;

public class MaterialUtilizationGridBean implements Serializable {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -2311903790013624254L;
	private String itemId;
	private long quantity;
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	

}
