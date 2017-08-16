package br.com.rlabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rlabs.entity.model.Developer;

/**
 * Developer Repository.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface DeveloperRepository extends JpaRepository<Developer, Long> {

}
