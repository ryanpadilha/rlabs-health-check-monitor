package br.com.rlabs.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.rlabs.controller.DashboardController;

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

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView formIndex() {
		final ModelAndView modelAndView = new ModelAndView("dashboard-index");
		return modelAndView;
	}
}
