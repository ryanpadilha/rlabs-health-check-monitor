package com.rlabs.vulcano.monitor.service;

import java.util.Collection;
import java.util.UUID;

import org.springframework.data.domain.Sort;

import com.rlabs.vulcano.monitor.entity.model.Organization;
import com.rlabs.vulcano.monitor.exceptions.UniqueConstraintException;

/**
 * The Organization Service Interface.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface OrganizationService {

	Collection<Organization> list(Sort sort);

	Organization get(Long id);

	Organization getByInternal(UUID internal);

	Organization persist(Organization organization) throws UniqueConstraintException;

	Organization update(Long id, Organization organization) throws UniqueConstraintException;

	Organization update(UUID internal, Organization organization) throws UniqueConstraintException;

	Organization delete(Long id);

	Organization delete(UUID internal);

	void validateConstraints(Organization organization) throws UniqueConstraintException;
}
