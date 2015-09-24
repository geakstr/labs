package me.geakstr.insapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractDao<T> {
	@PersistenceContext(unitName = "insapp-dao-layer-pu")
	protected EntityManager em;
	
	private Class<T> entityClass;

	public AbstractDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void create(T entity) {
		em.persist(entity);
	}

	public void edit(T entity) {
		em.merge(entity);
	}

	public void remove(T entity) {
		em.remove(em.merge(entity));
	}

	public T find(Object id) {
		return em.find(entityClass, id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int count() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		Root<T> rt = cq.from(entityClass);
		cq.select(em.getCriteriaBuilder().count(rt));
		javax.persistence.Query q = em.createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

}
