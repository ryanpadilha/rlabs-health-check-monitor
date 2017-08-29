package com.rlabs.vulcano.monitor.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rlabs.vulcano.monitor.entity.model.ProductStatus;

/**
 * Product Status Repository.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface ProductStatusRepository extends JpaRepository<ProductStatus, Long> {

	@Query("SELECT p FROM ProductStatus p WHERE p.internal = :internal")
	ProductStatus findByInternal(@Param("internal") UUID internal);

	@Query("SELECT p FROM ProductStatus p WHERE p.product.id = :productId")
	List<ProductStatus> findWithPageable(@Param("productId") Long productId, Pageable pageable);
}
