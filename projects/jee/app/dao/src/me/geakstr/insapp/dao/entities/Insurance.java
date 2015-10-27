package me.geakstr.insapp.dao.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "insurances")
public class Insurance implements IEntity {
	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "car_num", referencedColumnName = "car_num")
	private Car car;
	
	@Column(nullable = false, unique = false)
	private Date date_from;
	
	@Column(nullable = false, unique = false)
	private Date date_to;
	
	@Column(nullable = false, unique = false)
	private Boolean active;
	
	@Column(nullable = false, unique = false)
	private Double cost;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "insurance")
	private List<InsuranceToDriver> insuranceToDrivers = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
		
		car.getInsurances().add(this);
	}

	public Date getDate_from() {
		return date_from;
	}

	public void setDate_from(Date date_from) {
		this.date_from = date_from;
	}

	public Date getDate_to() {
		return date_to;
	}

	public void setDate_to(Date date_to) {
		this.date_to = date_to;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public List<InsuranceToDriver> getInsuranceToDrivers() {
		return insuranceToDrivers;
	}
	
	public void setInsuranceToDrivers(List<InsuranceToDriver> insuranceToDrivers) {
		this.insuranceToDrivers = insuranceToDrivers;
	}
	
	public void addInsuranceToDriver(InsuranceToDriver insuranceToDriver) {
		this.insuranceToDrivers.add(insuranceToDriver);
		insuranceToDriver.setInsurance(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Insurance other = (Insurance) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Insurance : id = %s; car_num = %s",
				id,
				car.getCar_num());
	}
}
