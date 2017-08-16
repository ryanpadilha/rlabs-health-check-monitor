package br.com.rlabs.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import br.com.rlabs.entity.model.Developer;

public interface DeveloperController {

	ModelAndView form();

	ModelAndView list();

	ModelAndView get(Long id);

	ModelAndView insert(Developer developer, BindingResult result);

	ModelAndView update(Long id, Developer developer);

	ModelAndView delete(Long id);
}
