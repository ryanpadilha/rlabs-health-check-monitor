package br.com.rlabs.controller.impl;

import java.util.Collection;

import javax.validation.Valid;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.rlabs.controller.DeveloperController;
import br.com.rlabs.entity.model.Developer;
import br.com.rlabs.service.DeveloperService;

/**
 * The Developer WEB-MVC Controller.
 * 
 * @author ryan
 *
 */
@Controller
@RequestMapping(value = "/configuration/developer")
public class DeveloperControllerImpl implements DeveloperController {

	@Autowired
	private DeveloperService service;

	private static final String PREFIX = "modules/configuration/";

	@Override
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView form() {
		final ModelAndView modelAndView = new ModelAndView(PREFIX + "developer-form");
		modelAndView.addObject(new Developer());
		return modelAndView;
	}

	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView modelAndView = new ModelAndView(PREFIX + "developer-list");
		Collection<Developer> developers = service.list();
		modelAndView.addObject("developers", developers);

		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView get(@PathVariable("id") Long id) {
		final ModelAndView modelAndView = new ModelAndView(PREFIX + "developer-form");
		Developer developer = service.get(id);
		modelAndView.addObject("developer", developer);

		return modelAndView;
	}

	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public ModelAndView insert(@Valid Developer developer, BindingResult result) {
		String htmlview = PREFIX + "developer-form";

		if (!result.hasErrors()) {
			service.insert(developer);
			htmlview = "redirect:/configuration/developer";
		}

		final ModelAndView modelAndView = new ModelAndView(htmlview);
		return modelAndView;
	}

	@Override
	public ModelAndView update(Long id, Developer developer) {
		throw new NotYetImplementedException();
	}

	@Override
	public ModelAndView delete(Long id) {
		throw new NotYetImplementedException();
	}

}
