package br.com.rlabs.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.rlabs.entity.model.Developer;
import br.com.rlabs.repository.dao.DeveloperDAO;

/**
 * Developer Repository.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface DeveloperRepository extends JpaRepository<Developer, Long>, DeveloperDAO {

	@Query("SELECT d FROM Developer d WHERE d.internal = :internal")
	Developer findByInternal(@Param("internal") UUID internal);
}
