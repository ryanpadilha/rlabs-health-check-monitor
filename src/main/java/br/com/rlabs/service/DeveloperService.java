package br.com.rlabs.service;

import java.util.Collection;

import br.com.rlabs.entity.model.Developer;

/**
 * The Developer Interface Service.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface DeveloperService {

	Collection<Developer> list();

	Developer get(Long id);

	Developer insert(Developer developer);

	Developer update(Long id, Developer developer);

	Developer delete(Long id);
}
