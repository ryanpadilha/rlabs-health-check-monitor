package com.rlabs.vulcano.monitor.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rlabs.vulcano.monitor.entity.model.Organization;
import com.rlabs.vulcano.monitor.repository.dao.OrganizationDAO;

/**
 * Organization Repository.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface OrganizationRepository extends JpaRepository<Organization, Long>, OrganizationDAO {

	@Query("SELECT o FROM Organization o WHERE o.internal = :internal")
	Organization findByInternal(@Param("internal") UUID internal);

}
