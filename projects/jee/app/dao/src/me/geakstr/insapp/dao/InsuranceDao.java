package me.geakstr.insapp.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import me.geakstr.insapp.dao.entities.Driver;
import me.geakstr.insapp.dao.entities.Insurance;

@Stateless
public class InsuranceDao extends AbstractDao<Insurance> {
	public InsuranceDao() {
		super(Insurance.class);
	}	
	
	public List<Driver> findDrivers(final Insurance insurance) {		
	  Query q = em.createQuery("SELECT d FROM Insurance i, InsuranceToDriver itd, Driver d WHERE i = itd.insurance AND itd.driver = d AND i.id = :insrc");
      q.setParameter("insrc", insurance.getId());
      return q.getResultList();
	}
}
