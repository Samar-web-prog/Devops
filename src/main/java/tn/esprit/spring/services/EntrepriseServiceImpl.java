package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.converter.EntrepriseConverter;
import tn.esprit.spring.dto.EntrepriseDTo;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import org.apache.log4j.Logger;
import tn.esprit.spring.repository.EntrepriseRepository;
import org.apache.log4j.LogManager;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
    EntrepriseConverter converter;
	private static final Logger l = LogManager.getLogger(EntrepriseServiceImpl.class);
	
	// Méthode qui permet d'a
	
	@Transactional
	public Integer ajouterEntreprise(EntrepriseDTo entreprise) {
		
		try{
			l.info("In methode ajouterEntreprise()");
			Entreprise en = converter.entrepriseTodo(entreprise);
			entrepriseRepoistory.save(en);
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
		List<String> depNames = new ArrayList<>();
		try{
			l.info("In methode getAllDepartementsNamesByEntreprise() : ");
			Optional<Entreprise> entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId);
			if(entrepriseManagedEntity.isPresent()) {
				for(Departement dep : entrepriseManagedEntity.get().getDepartements()){
					depNames.add(dep.getName());
				}
			}
			l.debug("Récupération des departements");
			l.info("Out methode getAllDepartementsNamesByEntreprise() ");
			return depNames;
		} catch (Exception e) {
			l.error("erreur dans la methode getAllDepartementsNamesByEntreprise() :"+e);
			depNames.clear();
			return depNames;
		}
		
	}

	@Transactional
	public Integer deleteEntrepriseById(int entrepriseId) {
		try {
			l.info("In methode deleteEntrepriseById() ");
			Optional<Entreprise> entreprise=entrepriseRepoistory.findById(entrepriseId);
			if (entreprise.isPresent()) {
				entrepriseRepoistory.delete(entreprise.get());	
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
		Entreprise en = null;
		try {
			l.info("In methode getEntrepriseById() : ");
			Optional<Entreprise> entreprise = entrepriseRepoistory.findById(entrepriseId);
			if (entreprise.isPresent()) {
				en=entreprise.get();
			}
			l.debug("Récupération de l'entreprise par id");
			l.info("Out methode getEntrepriseById() ");
			return en;
		} catch (Exception e) {
			l.error("erreur methode deleteEntrepriseById() :" + e);
			return null;
		}
	}
}
