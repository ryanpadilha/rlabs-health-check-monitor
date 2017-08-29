package com.rlabs.vulcano.monitor.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rlabs.vulcano.monitor.commons.Constants;
import com.rlabs.vulcano.monitor.commons.Status;
import com.rlabs.vulcano.monitor.entity.model.Product;
import com.rlabs.vulcano.monitor.entity.model.ProductStatus;
import com.rlabs.vulcano.monitor.repository.ProductRepository;
import com.rlabs.vulcano.monitor.service.ProductService;
import com.rlabs.vulcano.monitor.service.ProductStatusService;

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

	@Autowired
	private ProductStatusService statusService;

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
		return updateFields(repository.getOne(id), product);
	}

	@Transactional
	@Override
	public Product update(UUID internal, Product product) {
		return updateFields(repository.findByInternal(internal), product);
	}

	/**
	 * Update fields
	 *
	 * @param persisted
	 * @param product
	 */
	private Product updateFields(Product persisted, Product product) {
		if (null == persisted)
			return null;

		persisted.setName(product.getName());
		persisted.setDescription(product.getDescription());
		persisted.setArtifactId(product.getArtifactId());
		persisted.setEnvironment(product.getEnvironment());
		persisted.setVersion(product.getVersion());
		persisted.setHostname(product.getHostname());
		persisted.setProjectRepository(product.getProjectRepository());
		persisted.setProjectPage(product.getProjectPage());
		persisted.setActive(product.isActive());
		persisted.setOrganization(product.getOrganization());
		return repository.save(persisted);
	}

	@Transactional
	@Override
	public Product delete(Long id) {
		return delete(repository.getOne(id));
	}

	@Transactional
	@Override
	public Product delete(UUID internal) {
		return delete(repository.findByInternal(internal));
	}

	private Product delete(Product entity) {
		if (null == entity)
			return null;

		repository.delete(entity);
		return entity;
	}

	@Override
	public Product findByArtifactId(String value) {
		return repository.findByArtifactId(value);
	}

	@Override
	public Collection<Product> processBaseHealthStatus() {
		final Collection<Product> collection = new ArrayList<>();

		int cOUT = 0;
		int cDOWN = 0;
		Date lastTimestamp = new Date();

		for (Product product : repository.findAll()) {
			Collection<ProductStatus> status = statusService.findWithPageable(product.getId(),
					PageRequest.of(0, 10, Direction.ASC, "id"));
			if (!status.isEmpty()) {
				for (ProductStatus ps : status) {
					if (ps.getStatus().equals(Status.DOWN)) {
						cDOWN++;
					} else if (ps.getStatus().equals(Status.OUT_OF_SERVICE)) {
						cOUT++;
					}

					lastTimestamp = ps.getCreated();
				}

				if (cDOWN > Constants.STATUS_ALERT_DOWN) {
					product.setStatusColor("bg-yellow");
				}

				if (cOUT > Constants.STATUS_ALERT_OUT) {
					product.setStatusColor("bg-red");
				}

				cOUT = 0;
				cDOWN = 0;

				product.setLastStatusTimestamp(lastTimestamp);
				collection.add(product);
			}
		}

		return collection;
	}

}
