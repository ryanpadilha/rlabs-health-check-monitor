package com.rlabs.vulcano.monitor.service;

import java.util.Collection;
import java.util.UUID;

import com.rlabs.vulcano.monitor.entity.model.Developer;
import com.rlabs.vulcano.monitor.exceptions.UniqueConstraintException;

/**
 * The Developer Service Interface.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface DeveloperService {

	Collection<Developer> list();

	Developer get(Long id);

	Developer getByInternal(UUID internal);

	Developer persist(Developer developer) throws UniqueConstraintException;

	Developer update(Long id, Developer developer) throws UniqueConstraintException;

	Developer update(UUID internal, Developer developer) throws UniqueConstraintException;

	Developer delete(Long id);

	Developer delete(UUID internal);

	void validateConstraints(Developer developer) throws UniqueConstraintException;
}
