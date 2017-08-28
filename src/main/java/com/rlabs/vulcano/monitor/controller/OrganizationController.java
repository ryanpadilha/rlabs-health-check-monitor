package com.rlabs.vulcano.monitor.controller;

import java.util.UUID;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.rlabs.vulcano.monitor.entity.model.Organization;

/**
 * Organization Controller WEB-MVC Interface.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface OrganizationController {

	ModelAndView form();

	ModelAndView list();

	ModelAndView getByInternal(UUID internal);

	ModelAndView persist(Organization organization, BindingResult result);

	ModelAndView delete(UUID internal);
}
