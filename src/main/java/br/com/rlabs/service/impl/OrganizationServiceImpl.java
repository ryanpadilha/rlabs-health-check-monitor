package br.com.rlabs.service.impl;

import java.util.Collection;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rlabs.entity.model.Organization;
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
	public Organization persist(Organization organization) {
		Organization persisted = null;

		organization.setAlias(organization.getAlias().toLowerCase());

		if (null == organization.getInternal()) {
			persisted = repository.save(organization);
		} else {
			persisted = update(organization.getInternal(), organization);
		}

		return persisted;
	}

	@Transactional
	@Override
	public Organization update(Long id, Organization organization) {
		Organization persisted = repository.getOne(id);

		if (null == persisted)
			return null;

		// just update name
		persisted.setName(organization.getName());

		return repository.save(persisted);
	}

	@Transactional
	@Override
	public Organization update(UUID internal, Organization organization) {
		Organization persisted = repository.findByInternal(internal);

		if (null == persisted)
			return null;

		// just update name
		persisted.setName(organization.getName());

		return repository.save(persisted);
	}

	@Transactional
	@Override
	public Organization delete(Long id) {
		Organization persisted = repository.getOne(id);

		if (null == persisted)
			return null;

		repository.delete(persisted);
		return persisted;
	}

	@Transactional
	@Override
	public Organization delete(UUID internal) {
		Organization persisted = repository.findByInternal(internal);

		if (null == persisted)
			return null;

		repository.delete(persisted);
		return persisted;
	}

}
