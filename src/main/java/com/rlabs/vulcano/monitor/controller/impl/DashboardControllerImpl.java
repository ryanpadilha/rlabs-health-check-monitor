package com.rlabs.vulcano.monitor.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rlabs.vulcano.monitor.controller.DashboardController;
import com.rlabs.vulcano.monitor.service.ProductService;

/**
 * The Dashboard WEB-MVC Controller.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Controller
@RequestMapping(value = { "", "/dashboard" })
public class DashboardControllerImpl implements DashboardController {

	@Autowired
	private ProductService productService;

	private static final String FORM_INDEX = "dashboard-index";

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView form() {
		final ModelAndView modelAndView = new ModelAndView(FORM_INDEX);
		modelAndView.addObject("products", productService.list());

		// last-status of product

		return modelAndView;
	}
}
