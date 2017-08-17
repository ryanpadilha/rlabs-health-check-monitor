package br.com.rlabs.controller;

import java.util.UUID;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import br.com.rlabs.entity.model.Developer;

public interface DeveloperController {

	ModelAndView form();

	ModelAndView list();

	ModelAndView getByInternal(UUID internal);

	ModelAndView insert(Developer developer, BindingResult result);

	ModelAndView update(UUID internal, Developer developer);

	ModelAndView delete(UUID internal);
}
