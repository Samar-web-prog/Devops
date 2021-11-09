package tn.esprit.spring.services;

import java.util.List;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.dto.EntrepriseDTo;

public interface IEntrepriseService {
	
	public Integer ajouterEntreprise(EntrepriseDTo entreprise);
	List<String> getAllDepartementsNamesByEntreprise(int entrepriseId);
	public Integer deleteEntrepriseById(int entrepriseId);
	public Entreprise getEntrepriseById(int entrepriseId);
}
