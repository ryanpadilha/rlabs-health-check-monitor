package br.com.rlabs.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.rlabs.entity.model.Dependency;

/**
 * Dependency Repository.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface DependencyRepository extends JpaRepository<Dependency, Long> {

	@Query("SELECT d FROM Dependency d WHERE d.internal = :internal")
	Dependency findByInternal(@Param("internal") UUID internal);
}
