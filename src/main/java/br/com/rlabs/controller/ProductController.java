package br.com.rlabs.controller;

import java.util.UUID;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import br.com.rlabs.entity.model.Product;

/**
 * Product Controller WEB-MVC Interface.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface ProductController {

	ModelAndView form();

	ModelAndView list();

	ModelAndView getByInternal(UUID internal);

	ModelAndView persist(Product product, BindingResult result);

	ModelAndView delete(UUID internal);

	ModelAndView details(UUID internal);
}
