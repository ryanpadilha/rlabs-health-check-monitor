package com.rlabs.vulcano.monitor.service;

import java.util.Collection;
import java.util.UUID;

import com.rlabs.vulcano.monitor.entity.model.Dependency;

/**
 * The Dependency Service Interface.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface DependencyService {

	Collection<Dependency> list();

	Dependency get(Long id);

	Dependency getByInternal(UUID internal);

	Dependency persist(Dependency dependency);

	Dependency update(Long id, Dependency dependency);

	Dependency update(UUID internal, Dependency dependency);

	Dependency delete(Long id);

	Dependency delete(UUID internal);
}
