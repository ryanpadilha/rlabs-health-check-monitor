package com.rlabs.vulcano.monitor.service;

import java.util.Collection;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.rlabs.vulcano.monitor.entity.model.ProductStatusDetails;

/**
 * Product Status Details Service Interface.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface ProductStatusDetailsService {

	Collection<ProductStatusDetails> list();

	ProductStatusDetails get(Long id);

	ProductStatusDetails getByInternal(UUID internal);

	ProductStatusDetails persist(ProductStatusDetails productStatusDetails);

	Collection<ProductStatusDetails> persist(Collection<ProductStatusDetails> collection);

	Collection<ProductStatusDetails> findWithPageable(Long dependencyId, Pageable pageable);
}
