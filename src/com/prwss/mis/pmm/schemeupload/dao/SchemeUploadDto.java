package com.prwss.mis.pmm.schemeupload.dao;

import java.util.Arrays;

public class SchemeUploadDto {
	
	private String attachmentName;
	private byte[] attachmentFile;
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public byte[] getAttachmentFile() {
		return attachmentFile;
	}
	public void setAttachmentFile(byte[] attachmentFile) {
		this.attachmentFile = attachmentFile;
	}
	@Override
	public String toString() {
		return "SchemeUploadDto [attachmentName=" + attachmentName
				+ ", attachmentFile=" + Arrays.toString(attachmentFile) + "]";
	}
	
	
	

}
