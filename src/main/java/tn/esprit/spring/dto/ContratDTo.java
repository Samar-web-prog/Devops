package tn.esprit.spring.dto;


import java.util.Date;

import lombok.Data;
@Data
public class ContratDTo {
	private  Date dateDebut;
	private String typeContrat;
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getTypeContrat() {
		return typeContrat;
	}
	public void setTypeContrat(String typeContrat) {
		this.typeContrat = typeContrat;
	}
	public float getSalaire() {
		return salaire;
	}
	public void setSalaire(float salaire) {
		this.salaire = salaire;
	}
	private float salaire;
	public ContratDTo(Date dateDebut, String typeContrat, float salaire) {
		super();
		this.dateDebut = dateDebut;
		this.typeContrat = typeContrat;
		this.salaire = salaire;
	}
	public ContratDTo() {
		super();
		
	}
	
}
