package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Contrat;


public interface IContratService {
	
	
	public List<Contrat> getAllContrats();
	public Integer ajouterContrat(Contrat contrat);
	public int deleteContratById(int contratId);
	public void deleteAllContratJPQL();
	
	

	
}
