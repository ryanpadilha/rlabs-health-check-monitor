package com.rlabs.vulcano.monitor.repository.dao;

import com.rlabs.vulcano.monitor.entity.model.Organization;

/**
 * Organization DAO Interface.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface OrganizationDAO {

	boolean existsAlias(Organization organization);
}
