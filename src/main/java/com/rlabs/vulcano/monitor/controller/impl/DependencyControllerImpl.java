package com.rlabs.vulcano.monitor.controller.impl;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rlabs.vulcano.monitor.config.ResourceMessage;
import com.rlabs.vulcano.monitor.controller.DependencyController;
import com.rlabs.vulcano.monitor.entity.model.Dependency;
import com.rlabs.vulcano.monitor.service.DependencyService;
import com.rlabs.vulcano.monitor.service.ProductService;

/**
 * The Dependency WEB-MVC Controller.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Controller
@RequestMapping(value = "/platform/dependency")
public class DependencyControllerImpl implements DependencyController {

	@Autowired
	private DependencyService service;

	@Autowired
	private ProductService productService;

	@Autowired
	private ResourceMessage message;

	private static final String FORM = "modules/platform/dependency-form";
	private static final String FORM_LIST = "modules/platform/dependency-list";
	private static final String REDIRECT = "redirect:/platform/dependency";

	@Override
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView form() {
		final ModelAndView modelAndView = new ModelAndView(FORM);
		modelAndView.addObject("dependency", new Dependency());
		modelAndView.addObject("products", productService.list(Sort.by(Direction.ASC, "id")));
		return modelAndView;
	}

	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView modelAndView = new ModelAndView(FORM_LIST);
		modelAndView.addObject("dependencies", service.list(Sort.by(Direction.ASC, "id")));
		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/form/{internal}", method = RequestMethod.GET)
	public ModelAndView getByInternal(@PathVariable("internal") UUID internal) {
		final ModelAndView modelAndView = new ModelAndView(FORM);
		modelAndView.addObject("dependency", service.getByInternal(internal));
		modelAndView.addObject("products", productService.list(Sort.by(Direction.ASC, "id")));
		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public ModelAndView persist(@Valid Dependency dependency, BindingResult result) {
		final ModelAndView modelAndView = new ModelAndView(FORM);
		modelAndView.addObject("products", productService.list(Sort.by(Direction.ASC, "id")));

		if (!result.hasErrors()) {
			service.persist(dependency);
			modelAndView.setViewName(REDIRECT);
		}

		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("internal") UUID internal) {
		final ModelAndView modelAndView = new ModelAndView(FORM_LIST);

		try {
			service.delete(internal);
		} catch (DataIntegrityViolationException e) {
			modelAndView.addObject("error", message.getString("message.constraint.delete"));
		}

		modelAndView.addObject("dependencies", service.list(Sort.by(Direction.ASC, "id")));
		return modelAndView;
	}

}
