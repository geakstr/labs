package me.geakstr.insapp.dao;

import javax.ejb.Stateless;

import me.geakstr.insapp.dao.entities.InsuranceToDriver;

@Stateless
public class InsuranceToDriverDao extends AbstractDao<InsuranceToDriver> {
	public InsuranceToDriverDao() {
		super(InsuranceToDriver.class);
	}	
}
