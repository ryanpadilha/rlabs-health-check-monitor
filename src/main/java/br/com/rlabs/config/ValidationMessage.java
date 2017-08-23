package br.com.rlabs.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Validation Message used with JSR 303: Bean Validation.<br>
 * Reference: <a href="http://beanvalidation.org/1.0/spec/">Bean Validation
 * Spec</a>
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Configuration
public class ValidationMessage {

	@Bean
	public LocalValidatorFactoryBean validarorBean(MessageSource messageSource) {
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		factory.setValidationMessageSource(messageSource);
		return factory;
	}
}
