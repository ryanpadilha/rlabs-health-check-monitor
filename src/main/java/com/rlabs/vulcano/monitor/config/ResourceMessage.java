package com.rlabs.vulcano.monitor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * ResourceBundleMessageSource Wrapper Class.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Component
public class ResourceMessage {

	@Autowired
	private MessageSource messageSource;

	public String getString(String key) {
		return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
	}

	public String getString(String key, Object[] args) {
		return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
	}
}
