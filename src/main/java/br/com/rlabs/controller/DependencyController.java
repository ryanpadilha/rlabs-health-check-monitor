package br.com.rlabs.controller;

import java.util.UUID;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import br.com.rlabs.entity.model.Dependency;

/**
 * Dependency Controller WEB-MBC Interface.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface DependencyController {

	ModelAndView form();

	ModelAndView list();

	ModelAndView getByInternal(UUID internal);

	ModelAndView persist(Dependency dependency, BindingResult result);

	ModelAndView delete(UUID internal);
}
