package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.EntrepriseRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	private static final Logger l = LogManager.getLogger(EntrepriseServiceImpl.class);
	
	// Méthode qui permet d'a
	
	@Transactional
	public Integer ajouterEntreprise(Entreprise entreprise) {
		
		try{
			l.info("In methode ajouterEntreprise()");
			entrepriseRepoistory.save(entreprise);
			l.debug("entreprise ajouté et portant la ref = "+entreprise.getId());
			l.info("Out methode ajouterEntreprise()");
			return entreprise.getId();
		} catch (Exception e) {
			l.error("erreur dans la methode ajouterEntreprise() :"+e);
			return null;
		}	
		
	}
	
	@Transactional
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		try{
			l.info("In methode getAllDepartementsNamesByEntreprise() : ");
			Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
			List<String> depNames = new ArrayList<>();
			for(Departement dep : entrepriseManagedEntity.getDepartements()){
				depNames.add(dep.getName());
			}
			l.debug("Récupération des departements");
			l.info("Out methode getAllDepartementsNamesByEntreprise() ");
			return depNames;
		} catch (Exception e) {
			l.error("erreur dans la methode getAllDepartementsNamesByEntreprise() :"+e);
			return null;
		}
		
	}

	@Transactional
	public Integer deleteEntrepriseById(int entrepriseId) {
		try {
			l.info("In methode deleteEntrepriseById() ");
			if (entrepriseRepoistory.findById(entrepriseId).orElse(null) != null) {
				entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).orElse(null));	
			} 
			l.debug("Supprission de l'entreprise avec l'id"+entrepriseId);
			l.info("Out deleteEntrepriseById()");
			return 1;
		} catch (Exception e) {
			l.error("erreur methode deleteEntrepriseById() :" + e);
			return 0;
		}
	}

	@Transactional
	public Entreprise getEntrepriseById(int entrepriseId) {
		try {
			l.info("In methode getEntrepriseById() : ");
			Entreprise entreprise = entrepriseRepoistory.findById(entrepriseId).get();
			l.debug("Récupération de l'entreprise par id");
			l.info("Out methode getEntrepriseById() ");
			return entreprise;
		} catch (Exception e) {
			l.error("erreur methode deleteEntrepriseById() :" + e);
			return null;
		}
	}
}
