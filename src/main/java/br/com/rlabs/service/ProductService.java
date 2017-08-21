package br.com.rlabs.service;

import java.util.Collection;
import java.util.UUID;

import br.com.rlabs.entity.model.Product;

/**
 * The Product Service Interface.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface ProductService {

	Collection<Product> list();

	Product get(Long id);

	Product getByInternal(UUID internal);

	Product persist(Product product);

	Product update(Long id, Product product);

	Product update(UUID internal, Product product);

	Product delete(Long id);

	Product delete(UUID internal);
}
