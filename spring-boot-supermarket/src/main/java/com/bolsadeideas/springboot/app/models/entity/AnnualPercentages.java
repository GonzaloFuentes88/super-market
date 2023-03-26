package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "annual_percentages")
public class AnnualPercentages implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long year;

	@OneToMany(mappedBy = "annualPercentages", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Mes> meses;

	public AnnualPercentages() {
		this.meses = new ArrayList<>();
	}

	public boolean addMes(Mes mes) {
		Mes aux = meses.stream().filter(a -> a.getNombre().equalsIgnoreCase(mes.getNombre())).findFirst().orElse(null);

		if (aux != null) {
			return false;
		}

		mes.setAnnualPercentages(this);
		return meses.add(mes);
	}

	public boolean deleteMes(Long id) {
		Mes aux = meses.stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
		
		return meses.remove(aux); 

	}

	public List<Mes> getMeses() {
		return meses;
	}

	public void setMeses(List<Mes> meses) {
		this.meses = meses;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public List<Float> getMesesCarne() {
		return this.meses.stream().filter(a -> a.getCategoria().equalsIgnoreCase("carne")).map(a -> a.getValor())
				.toList();
	}

	public List<Float> getMesesVerdura() {
		return this.meses.stream().filter(a -> a.getCategoria().equalsIgnoreCase("verdura")).map(a -> a.getValor())
				.toList();
	}

}
