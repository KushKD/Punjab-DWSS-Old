package com.prwss.mis.common.util;

import java.text.SimpleDateFormat;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class DateColumnDecorator implements DisplaytagColumnDecorator {

	@Override
	public Object decorate(Object columnValue, PageContext pageContext, MediaTypeEnum mediaTypeEnum) throws DecoratorException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		return simpleDateFormat.format(columnValue);
	}

}
