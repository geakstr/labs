package me.geakstr.insapp.business.facades;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import me.geakstr.insapp.dao.CarDao;
import me.geakstr.insapp.dao.entities.Car;

@Stateless
@LocalBean
public class EmployeeCarFacade implements ICrudFacade<Car> {
	@EJB
	private CarDao carDao;

	@Override
	public List<Car> findAll() {
		return carDao.findAll();
	}

	@Override
	public void delete(final Car car) {
		carDao.remove(car);
	}

	@Override
	public void add(final Car car) {
		carDao.create(car);
	}

	@Override
	public void edit(final Car car) {
		carDao.edit(car);
	}
}
