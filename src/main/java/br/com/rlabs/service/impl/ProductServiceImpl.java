package br.com.rlabs.service.impl;

import java.util.Collection;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rlabs.entity.model.Product;
import br.com.rlabs.repository.ProductRepository;
import br.com.rlabs.service.ProductService;

/**
 * Product Service Implementation.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Override
	public Collection<Product> list() {
		return repository.findAll();
	}

	@Override
	public Product get(Long id) {
		return repository.getOne(id);
	}

	@Override
	public Product getByInternal(UUID internal) {
		return repository.findByInternal(internal);
	}

	@Transactional
	@Override
	public Product persist(Product product) {
		Product persisted = null;

		if (null == product.getInternal()) {
			persisted = repository.save(product);
		} else {
			persisted = update(product.getInternal(), product);
		}

		return persisted;
	}

	@Transactional
	@Override
	public Product update(Long id, Product product) {
		Product persisted = repository.getOne(id);

		if (null == persisted)
			return null;

		//

		return repository.save(persisted);
	}

	@Transactional
	@Override
	public Product update(UUID internal, Product product) {
		Product persisted = repository.findByInternal(internal);

		if (null == persisted)
			return null;

		//

		return repository.save(persisted);
	}

	@Transactional
	@Override
	public Product delete(Long id) {
		Product persisted = repository.getOne(id);

		if (null == persisted)
			return null;

		repository.delete(persisted);
		return persisted;
	}

	@Transactional
	@Override
	public Product delete(UUID internal) {
		Product persisted = repository.findByInternal(internal);

		if (null == persisted)
			return null;

		repository.delete(persisted);
		return persisted;
	}

}
