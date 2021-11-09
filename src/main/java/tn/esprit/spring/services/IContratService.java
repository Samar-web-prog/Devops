package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.dto.ContratDTo;
import tn.esprit.spring.entities.Contrat;


public interface IContratService {
	
	
	public List<Contrat> getAllContrats();
	public Integer ajouterContrat(ContratDTo contrat);
	public int deleteContratById(int contratId);
	public void deleteAllContratJPQL();
	
	

	
}
