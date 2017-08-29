package com.rlabs.vulcano.monitor.service;

import java.util.Collection;
import java.util.UUID;

import com.rlabs.vulcano.monitor.entity.model.ProductStatus;

/**
 * Product Status Service Interface.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface ProductStatusService {

	Collection<ProductStatus> list();

	ProductStatus get(Long id);

	ProductStatus getByInternal(UUID internal);

	ProductStatus persist(ProductStatus productStatus);

}
