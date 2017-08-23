package br.com.rlabs.service.impl;

import java.util.Collection;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rlabs.config.ResourceMessage;
import br.com.rlabs.entity.model.Organization;
import br.com.rlabs.exceptions.UniqueConstraintException;
import br.com.rlabs.repository.OrganizationRepository;
import br.com.rlabs.service.OrganizationService;

/**
 * Organization Service Implementation.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationRepository repository;

	@Autowired
	private ResourceMessage message;

	@Override
	public Collection<Organization> list() {
		return repository.findAll();
	}

	@Override
	public Organization get(Long id) {
		return repository.getOne(id);
	}

	@Override
	public Organization getByInternal(UUID internal) {
		return repository.findByInternal(internal);
	}

	@Transactional
	@Override
	public Organization persist(Organization organization) throws UniqueConstraintException {
		Organization persisted = null;
		organization.setAlias(organization.getAlias().toLowerCase());

		if (null == organization.getInternal()) {
			validateConstraints(organization);
			persisted = repository.save(organization);
		} else {
			persisted = update(organization.getInternal(), organization);
		}

		return persisted;
	}

	@Transactional
	@Override
	public Organization update(Long id, Organization organization) throws UniqueConstraintException {
		return updateFields(repository.getOne(id), organization);
	}

	@Transactional
	@Override
	public Organization update(UUID internal, Organization organization) throws UniqueConstraintException {
		return updateFields(repository.findByInternal(internal), organization);
	}

	/**
	 * Update fields
	 *
	 * @param persisted
	 * @param organization
	 * @return
	 * @throws UniqueConstraintException
	 */
	private Organization updateFields(Organization persisted, Organization organization)
			throws UniqueConstraintException {
		if (null == persisted)
			return null;

		validateConstraints(organization);
		persisted.setName(organization.getName());
		persisted.setActive(organization.isActive());
		return repository.save(persisted);
	}

	@Transactional
	@Override
	public Organization delete(Long id) {
		return delete(repository.getOne(id));
	}

	@Transactional
	@Override
	public Organization delete(UUID internal) {
		return delete(repository.findByInternal(internal));
	}

	private Organization delete(Organization entity) {
		if (null == entity)
			return null;

		repository.delete(entity);
		return entity;
	}

	@Override
	public void validateConstraints(Organization organization) throws UniqueConstraintException {
		if (repository.existsAlias(organization))
			throw new UniqueConstraintException(message.getString("organization.alias.exists"));
	}

}
