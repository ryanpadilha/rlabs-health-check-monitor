package com.rlabs.vulcano.monitor.service.impl;

import java.util.Collection;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rlabs.vulcano.monitor.entity.model.ProductStatusDetails;
import com.rlabs.vulcano.monitor.repository.ProductStatusDetailsRepository;
import com.rlabs.vulcano.monitor.service.ProductStatusDetailsService;

/**
 * Product Status Details Service Implementation.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Service
public class ProductStatusDetailsServiceImpl implements ProductStatusDetailsService {

	@Autowired
	private ProductStatusDetailsRepository repository;

	@Override
	public Collection<ProductStatusDetails> list(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public ProductStatusDetails get(Long id) {
		return repository.getOne(id);
	}

	@Override
	public ProductStatusDetails getByInternal(UUID internal) {
		return repository.findByInternal(internal);
	}

	@Transactional
	@Override
	public ProductStatusDetails persist(ProductStatusDetails productStatusDetails) {
		return repository.save(productStatusDetails);
	}

	@Transactional
	@Override
	public Collection<ProductStatusDetails> persist(Collection<ProductStatusDetails> collection) {
		return repository.saveAll(collection);
	}

	@Override
	public Collection<ProductStatusDetails> findWithPageable(Long dependencyId, Pageable pageable) {
		return repository.findWithPageable(dependencyId, pageable);
	}

}
