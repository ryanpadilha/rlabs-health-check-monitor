package com.rlabs.vulcano.monitor.service.impl;

import java.util.Collection;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rlabs.vulcano.monitor.config.ResourceMessage;
import com.rlabs.vulcano.monitor.entity.model.Developer;
import com.rlabs.vulcano.monitor.exceptions.UniqueConstraintException;
import com.rlabs.vulcano.monitor.repository.DeveloperRepository;
import com.rlabs.vulcano.monitor.service.DeveloperService;

/**
 * Developer Service Implementation.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Service
public class DeveloperServiceImpl implements DeveloperService {

	@Autowired
	private DeveloperRepository repository;

	@Autowired
	private ResourceMessage message;

	@Override
	public Collection<Developer> list(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public Developer get(Long id) {
		return repository.getOne(id);
	}

	@Override
	public Developer getByInternal(UUID internal) {
		return repository.findByInternal(internal);
	}

	@Transactional
	@Override
	public Developer persist(Developer developer) throws UniqueConstraintException {
		Developer persisted = null;

		if (null == developer.getInternal()) {
			validateConstraints(developer);
			persisted = repository.save(developer);
		} else {
			persisted = update(developer.getInternal(), developer);
		}

		return persisted;
	}

	@Transactional
	@Override
	public Developer update(Long id, Developer developer) throws UniqueConstraintException {
		return updateFields(repository.getOne(id), developer);
	}

	@Transactional
	@Override
	public Developer update(UUID internal, Developer developer) throws UniqueConstraintException {
		return updateFields(repository.findByInternal(internal), developer);
	}

	/**
	 * Update fields
	 *
	 * @param persisted
	 * @param developer
	 * @return
	 * @throws UniqueConstraintException
	 */
	private Developer updateFields(Developer persisted, Developer developer) throws UniqueConstraintException {
		if (null == persisted)
			return null;

		validateConstraints(developer);
		persisted.setName(developer.getName());
		persisted.setActive(developer.isActive());
		return repository.save(persisted);
	}

	@Transactional
	@Override
	public Developer delete(Long id) {
		return delete(repository.getOne(id));
	}

	@Transactional
	@Override
	public Developer delete(UUID internal) {
		return delete(repository.findByInternal(internal));
	}

	private Developer delete(Developer entity) {
		if (null == entity)
			return null;

		repository.delete(entity);
		return entity;
	}

	@Override
	public void validateConstraints(Developer developer) throws UniqueConstraintException {
		if (repository.existsEmail(developer))
			throw new UniqueConstraintException(message.getString("developer.email.exists"));
	}

}
