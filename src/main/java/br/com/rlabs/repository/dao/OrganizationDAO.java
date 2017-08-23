package br.com.rlabs.repository.dao;

import br.com.rlabs.entity.model.Organization;

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
