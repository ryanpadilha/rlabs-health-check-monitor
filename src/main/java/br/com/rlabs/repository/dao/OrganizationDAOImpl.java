package br.com.rlabs.repository.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.rlabs.entity.model.Organization;

/**
 * Organization DAO Implementation.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Repository
public class OrganizationDAOImpl implements OrganizationDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean existsAlias(Organization organization) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(o) > 0 FROM Organization o WHERE o.alias = :value");

		if (null != organization.getInternal()) {
			sql.append(" AND o.internal != :internal ");
		}

		Query query = em.createQuery(sql.toString());
		query.setParameter("value", organization.getAlias());

		if (null != organization.getInternal()) {
			query.setParameter("internal", organization.getInternal());
		}

		return ((Boolean) query.getSingleResult());
	}

}
