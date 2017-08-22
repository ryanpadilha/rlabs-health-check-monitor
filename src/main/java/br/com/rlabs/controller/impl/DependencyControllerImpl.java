package br.com.rlabs.controller.impl;

import java.util.Collection;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.rlabs.controller.DependencyController;
import br.com.rlabs.entity.model.Dependency;
import br.com.rlabs.service.DependencyService;
import br.com.rlabs.service.ProductService;

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

	private static final String FORM = "modules/platform/dependency-form";
	private static final String FORM_LIST = "modules/platform/dependency-list";
	private static final String REDIRECT = "redirect:/platform/dependency";

	@Override
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView form() {
		final ModelAndView modelAndView = new ModelAndView(FORM);
		modelAndView.addObject("dependency", new Dependency());
		modelAndView.addObject("products", productService.list());

		return modelAndView;
	}

	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView modelAndView = new ModelAndView(FORM_LIST);
		Collection<Dependency> dependencies = service.list();
		modelAndView.addObject("dependencies", dependencies);
		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/form/{internal}", method = RequestMethod.GET)
	public ModelAndView getByInternal(@PathVariable("internal") UUID internal) {
		final ModelAndView modelAndView = new ModelAndView(FORM);
		Dependency dependency = service.getByInternal(internal);
		modelAndView.addObject("dependency", dependency);
		modelAndView.addObject("products", productService.list());

		return modelAndView;
	}

	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public ModelAndView persist(@Valid Dependency dependency, BindingResult result) {
		String htmlView = FORM;

		if (!result.hasErrors()) {
			service.persist(dependency);
			htmlView = REDIRECT;
		}

		final ModelAndView modelAndView = new ModelAndView(htmlView);
		modelAndView.addObject("products", productService.list());
		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("internal") UUID internal) {
		final ModelAndView modelAndView = new ModelAndView(REDIRECT);
		service.delete(internal);

		return modelAndView;
	}

}
