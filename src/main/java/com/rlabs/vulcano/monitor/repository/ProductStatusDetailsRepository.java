package com.rlabs.vulcano.monitor.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rlabs.vulcano.monitor.entity.model.ProductStatusDetails;

/**
 * Product Status Details Repository.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface ProductStatusDetailsRepository extends JpaRepository<ProductStatusDetails, Long> {

	@Query("SELECT p FROM ProductStatusDetails p WHERE p.internal = :internal")
	ProductStatusDetails findByInternal(@Param("internal") UUID internal);

	@Query("SELECT p FROM ProductStatusDetails p WHERE p.dependency.id = :dependencyId")
	List<ProductStatusDetails> findWithPageable(@Param("dependencyId") Long dependencyId, Pageable pageable);
}
