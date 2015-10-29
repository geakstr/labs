package me.geakstr.insapp.responses;

public class InsuranceCoeff {
	public String fio;
	public Double coeff;
	
	public String toString() {
		return String.format("Fio : %s; Coeff : %s", fio, coeff);
	}
}
