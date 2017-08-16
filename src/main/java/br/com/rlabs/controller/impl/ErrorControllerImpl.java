package br.com.rlabs.controller.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Erro Controller for Thymeleaf Engine.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Controller
public class ErrorControllerImpl {

	/**
	 * Error Page.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/error.html")
	public ModelAndView error(HttpServletRequest request, Model model) {
		ModelAndView modelAndView = new ModelAndView("error");
		model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));

		String errorMessage = null;
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");

		if (null != throwable) {
			errorMessage = throwable.getMessage();
		}

		model.addAttribute("errorMessage", errorMessage);
		return modelAndView;
	}
}
