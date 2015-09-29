package me.geakstr.insapp.api.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import me.geakstr.insapp.api.entities.DriverInsuranceCoeff;
import me.geakstr.insapp.api.facades.InsuranceCoeffsFacade;

@ManagedBean
@ViewScoped
public class InsuranceCoeffsBean {
	@EJB
	private InsuranceCoeffsFacade insuranceCoeffsFacade;
	
	private List<DriverInsuranceCoeff> allInsuranceCoeffs;
	private DriverInsuranceCoeff coeff = new DriverInsuranceCoeff();
    private boolean edit;
	
	@PostConstruct
	public void init() {
		allInsuranceCoeffs = insuranceCoeffsFacade.getAllCoeffs();
	}
	
	public void add() {
		allInsuranceCoeffs.add(coeff);
		insuranceCoeffsFacade.add(coeff);
		coeff = new DriverInsuranceCoeff();
    }
	
	public void edit(final DriverInsuranceCoeff coeff) {
		this.coeff = coeff;
		edit = true;
	}
	
	public void save() {
		insuranceCoeffsFacade.edit(coeff);
		coeff = new DriverInsuranceCoeff();
        edit = false;
    }
	
	public void delete(final DriverInsuranceCoeff coeff) {
		insuranceCoeffsFacade.delete(coeff);
		allInsuranceCoeffs.remove(coeff);
    }

	public List<DriverInsuranceCoeff> getAllInsuranceCoeffs() {
		return allInsuranceCoeffs;
	}

	public DriverInsuranceCoeff getCoeff() {
		return coeff;
	}

	public void setCoeff(DriverInsuranceCoeff coeff) {
		this.coeff = coeff;
	}

	public boolean isEdit() {
		return edit;
	}
}
