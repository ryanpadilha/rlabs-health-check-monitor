package br.com.rlabs.service;

import java.util.Collection;
import java.util.UUID;

import br.com.rlabs.entity.model.Organization;

/**
 * The Organization Service Interface.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface OrganizationService {

	Collection<Organization> list();

	Organization get(Long id);

	Organization getByInternal(UUID internal);

	Organization persist(Organization organization);

	Organization update(Long id, Organization organization);

	Organization update(UUID internal, Organization organization);

	Organization delete(Long id);

	Organization delete(UUID internal);
}
