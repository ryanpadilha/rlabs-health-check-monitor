package br.com.rlabs.repository.dao;

import br.com.rlabs.entity.model.Developer;

/**
 * Developer DAO Interface.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface DeveloperDAO {

	boolean existsEmail(Developer developer);
}
