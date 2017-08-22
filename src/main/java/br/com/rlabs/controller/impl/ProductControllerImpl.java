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

import br.com.rlabs.controller.ProductController;
import br.com.rlabs.entity.model.Product;
import br.com.rlabs.service.OrganizationService;
import br.com.rlabs.service.ProductService;

/**
 * The Product WEB-MVC Controller.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Controller
@RequestMapping(value = "/platform/product")
public class ProductControllerImpl implements ProductController {

	@Autowired
	private ProductService service;

	@Autowired
	private OrganizationService organizationService;

	private static final String FORM = "modules/platform/product-form";
	private static final String FORM_LIST = "modules/platform/product-list";
	private static final String REDIRECT = "redirect:/platform/product";

	@Override
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView form() {
		final ModelAndView modelAndView = new ModelAndView(FORM);
		modelAndView.addObject(new Product());
		modelAndView.addObject("organizations", organizationService.list());

		return modelAndView;
	}

	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView modelAndView = new ModelAndView(FORM_LIST);
		Collection<Product> products = service.list();
		modelAndView.addObject("products", products);
		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/form/{internal}", method = RequestMethod.GET)
	public ModelAndView getByInternal(@PathVariable("internal") UUID internal) {
		final ModelAndView modelAndView = new ModelAndView(FORM);
		Product product = service.getByInternal(internal);
		modelAndView.addObject("product", product);
		modelAndView.addObject("organizations", organizationService.list());

		return modelAndView;
	}

	@Override
	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public ModelAndView persist(@Valid Product product, BindingResult result) {
		String htmlView = FORM;

		if (!result.hasErrors()) {
			service.persist(product);
			htmlView = REDIRECT;
		}

		final ModelAndView modelAndView = new ModelAndView(htmlView);
		modelAndView.addObject("organizations", organizationService.list());
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