package br.com.rlabs.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.rlabs.entity.model.Organization;

/**
 * Organization Repository.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 * 
 */
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

	@Query("SELECT o FROM Organization o WHERE o.internal = :internal")
	Organization findByInternal(@Param("internal") UUID internal);
}
