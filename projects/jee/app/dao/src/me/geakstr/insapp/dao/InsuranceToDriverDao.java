package me.geakstr.insapp.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import me.geakstr.insapp.dao.entities.Driver;
import me.geakstr.insapp.dao.entities.Insurance;
import me.geakstr.insapp.dao.entities.InsuranceToDriver;

@Stateless
public class InsuranceToDriverDao extends AbstractDao<InsuranceToDriver> {
	public InsuranceToDriverDao() {
		super(InsuranceToDriver.class);
	}	
	
	public InsuranceToDriver find(final Insurance insurance, final Driver driver) {		
	  Query q = em.createQuery("SELECT itd FROM InsuranceToDriver itd WHERE itd.driver.license = :drv AND itd.insurance.id = :ins");
      q.setParameter("drv", driver.getLicense());
      q.setParameter("ins", insurance.getId());
      
      List<InsuranceToDriver> res = q.getResultList();
      return res.isEmpty() ? null : res.get(0);
	}
}
