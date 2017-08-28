package com.rlabs.vulcano.monitor.repository.dao;

import com.rlabs.vulcano.monitor.entity.model.Developer;

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
