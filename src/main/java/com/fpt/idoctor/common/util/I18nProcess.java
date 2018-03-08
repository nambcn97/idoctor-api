package com.fpt.idoctor.common.util;

import org.springframework.context.MessageSource;

public interface I18nProcess {
	public void i18nProcess(MessageSource database, MessageSource property) throws Exception;
}
