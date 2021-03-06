package com.rlabs.vulcano.monitor.controller;

import java.util.UUID;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.rlabs.vulcano.monitor.entity.model.Developer;

/**
 * Developer Controller WEB-MVC Interface.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface DeveloperController {

	ModelAndView form();

	ModelAndView list();

	ModelAndView getByInternal(UUID internal);

	ModelAndView persist(Developer developer, BindingResult result);

	ModelAndView delete(UUID internal);
}
