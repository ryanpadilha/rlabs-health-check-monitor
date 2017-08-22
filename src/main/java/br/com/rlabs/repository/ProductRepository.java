package br.com.rlabs.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.rlabs.entity.model.Product;

/**
 * Product Repository.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.01
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p WHERE p.internal = :internal")
	Product findByInternal(@Param("internal") UUID internal);
}