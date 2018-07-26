package com.prwss.mis.common.tenderCheck;

import java.text.ParseException;

import com.prwss.mis.common.exception.MISException;

public interface TenderNotificationBO {

	public void mailNotifier() throws MISException, ParseException;

}
