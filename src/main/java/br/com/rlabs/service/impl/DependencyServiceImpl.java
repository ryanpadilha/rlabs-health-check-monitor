package br.com.rlabs.service.impl;

import java.util.Collection;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rlabs.entity.model.Dependency;
import br.com.rlabs.repository.DependencyRepository;
import br.com.rlabs.service.DependencyService;

/**
 * Dependency Service Implementation.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Service
public class DependencyServiceImpl implements DependencyService {

	@Autowired
	private DependencyRepository repository;

	@Override
	public Collection<Dependency> list() {
		return repository.findAll();
	}

	@Override
	public Dependency get(Long id) {
		return repository.getOne(id);
	}

	@Override
	public Dependency getByInternal(UUID internal) {
		return repository.findByInternal(internal);
	}

	@Transactional
	@Override
	public Dependency persist(Dependency dependency) {
		Dependency persisted = null;

		if (null == dependency.getInternal()) {
			persisted = repository.save(dependency);
		} else {
			persisted = update(dependency.getInternal(), dependency);
		}

		return persisted;
	}

	@Transactional
	@Override
	public Dependency update(Long id, Dependency dependency) {
		Dependency persisted = repository.getOne(id);

		if (null == persisted)
			return null;

		//

		return repository.save(persisted);
	}

	@Transactional
	@Override
	public Dependency update(UUID internal, Dependency dependency) {
		Dependency persisted = repository.findByInternal(internal);

		if (null == persisted)
			return null;

		//

		return repository.save(persisted);
	}

	@Transactional
	@Override
	public Dependency delete(Long id) {
		Dependency persisted = repository.getOne(id);

		if (null == persisted)
			return null;

		repository.delete(persisted);
		return persisted;
	}

	@Transactional
	@Override
	public Dependency delete(UUID internal) {
		Dependency persisted = repository.findByInternal(internal);

		if (null == persisted)
			return null;

		repository.delete(persisted);
		return persisted;
	}

}
