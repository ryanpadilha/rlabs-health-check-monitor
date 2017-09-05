package com.rlabs.vulcano.monitor.service.impl;

import java.util.Collection;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rlabs.vulcano.monitor.entity.model.ProductStatus;
import com.rlabs.vulcano.monitor.repository.ProductStatusRepository;
import com.rlabs.vulcano.monitor.service.ProductStatusService;

/**
 * Product Status Service Implementation.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Service
public class ProductStatusServiceImpl implements ProductStatusService {

	@Autowired
	private ProductStatusRepository repository;

	@Override
	public Collection<ProductStatus> list(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public ProductStatus get(Long id) {
		return repository.getOne(id);
	}

	@Override
	public ProductStatus getByInternal(UUID internal) {
		return repository.findByInternal(internal);
	}

	@Transactional
	@Override
	public ProductStatus persist(ProductStatus productStatus) {
		return repository.save(productStatus);
	}

	@Override
	public Collection<ProductStatus> findWithPageable(Long productId, Pageable pageable) {
		return repository.findWithPageable(productId, pageable);
	}

}
