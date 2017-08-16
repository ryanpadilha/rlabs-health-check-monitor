package br.com.rlabs.service.impl;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rlabs.entity.model.Developer;
import br.com.rlabs.repository.DeveloperRepository;
import br.com.rlabs.service.DeveloperService;

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

	@Override
	public Collection<Developer> list() {
		return repository.findAll();
	}

	@Override
	public Developer get(Long id) {
		return repository.getOne(id);
	}

	@Transactional
	@Override
	public Developer insert(Developer developer) {
		return repository.save(developer);
	}

	@Transactional
	@Override
	public Developer update(Long id, Developer developer) {
		Developer persisted = repository.getOne(id);

		if (null == persisted)
			return null;

		// just update name
		persisted.setName(developer.getName());

		return repository.save(persisted);
	}

	@Transactional
	@Override
	public Developer delete(Long id) {
		Developer persisted = repository.getOne(id);

		if (null == persisted)
			return null;

		repository.delete(persisted);
		return persisted;
	}

}
