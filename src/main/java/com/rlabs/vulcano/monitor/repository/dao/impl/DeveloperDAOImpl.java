package com.rlabs.vulcano.monitor.repository.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.rlabs.vulcano.monitor.entity.model.Developer;
import com.rlabs.vulcano.monitor.repository.dao.DeveloperDAO;

/**
 * Developer DAO Implementation.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Repository
public class DeveloperDAOImpl implements DeveloperDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean existsEmail(Developer developer) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(d) > 0 FROM Developer d WHERE d.email = :value");

		if (null != developer.getInternal()) {
			sql.append(" AND d.internal != :internal");
		}

		Query query = em.createQuery(sql.toString());
		query.setParameter("value", developer.getEmail());

		if (null != developer.getInternal()) {
			query.setParameter("internal", developer.getInternal());
		}

		return ((Boolean) query.getSingleResult());
	}

}
