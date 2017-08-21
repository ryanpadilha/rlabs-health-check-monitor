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

import br.com.rlabs.controller.OrganizationController;
import br.com.rlabs.entity.model.Organization;
import br.com.rlabs.service.OrganizationService;

/**
 * The Organization WEB-MVC Controller.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Controller
@RequestMapping(value = "/configuration/organization")
public class OrganizationControllerImpl implements OrganizationController {

	@Autowired
	private OrganizationService service;

	private static final String FORM = "modules/configuration/organization-form";
	private static final String FORM_LIST = "modules/configuration/organization-list";
	private static final String REDIRECT = "redirect:/configuration/organization";

	@Override
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView form() {
		final ModelAndView modelAndView = new ModelAndView(FORM);
		modelAndView.addObject(new Organization());
		return modelAndView;
	}

	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView modelAndView = new ModelAndView(FORM_LIST);
		Collection<Organization> organizations = service.list();
		modelAndView.addObject("organizations", organizations);
		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/form/{internal}", method = RequestMethod.GET)
	public ModelAndView getByInternal(@PathVariable("internal") UUID internal) {
		final ModelAndView modelAndView = new ModelAndView(FORM);
		Organization organization = service.getByInternal(internal);
		modelAndView.addObject("organization", organization);

		return modelAndView;
	}

	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public ModelAndView persist(@Valid Organization organization, BindingResult result) {
		String htmlView = FORM;

		if (!result.hasErrors()) {
			service.persist(organization);
			htmlView = REDIRECT;
		}

		final ModelAndView modelAndView = new ModelAndView(htmlView);
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
