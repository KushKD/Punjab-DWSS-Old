package com.prwss.mis.common.contractCheck;

import java.text.ParseException;

import com.prwss.mis.common.exception.MISException;

public interface ContractNotificationBO {

	public void mailNotifier() throws MISException, ParseException;
}
