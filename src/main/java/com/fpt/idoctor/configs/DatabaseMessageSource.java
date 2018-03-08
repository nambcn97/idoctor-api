package com.fpt.idoctor.configs;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.ResourceLoader;

public class DatabaseMessageSource extends AbstractMessageSource implements ResourceLoaderAware{

	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		return null;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		
	}

}
