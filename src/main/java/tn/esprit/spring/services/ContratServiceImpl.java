package tn.esprit.spring.services;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;

@Service
public class ContratServiceImpl implements IContratService {

	
	@Autowired
	ContratRepository contratRepository;
	private static final Logger l = LogManager.getLogger(ContratServiceImpl.class);
	
	public Integer ajouterContrat(Contrat contrat) {
		
		l.info("In ajouterContrat()");
		try{
			l.debug("je vais ajouter le contrat  ");
			contratRepository.save(contrat);
			l.debug("contrat ajouté et portant la ref  = "+contrat.getReference());
			l.info("Out ajouterContrat()");
			return contrat.getReference();
		} catch (Exception e) {
			l.error("erreur dans la methode ajouterContrat() :"+e);
			return null;
		}
       
	}
	
	public int deleteContratById(int contratId) {
		l.info("In deleteContratById() ");
		try {
			if (contratRepository.findById(contratId).orElse(null) != null) {
				l.debug("contrat portant l'id :  "+contratId +" est recupére avec succés");
			contratRepository.delete(contratRepository.findById(contratId).orElse(null));	
			l.debug("suppression de contrat portant l'id: "+contratId+ " avec success ");
			l.info("Out deleteContratById() ");
			return 1;
			} 
		} catch (Exception e) {
			l.error("erreur methode deleteContratById() :" + e);
			return 0;
		}
		return -1;

	}
	
	public List<Contrat> getAllContrats() {
        l.info("In getAllContrats() : ");
		l.debug("Récupération des contrats");
		l.info("Out getAllContrats() ");
		return (List<Contrat>) contratRepository.findAll();
	}
	
	public void deleteAllContratJPQL() {
		   l.info("In deleteAllContratJPQL() : ");
			l.debug("acces a la base des données");
			contratRepository.deleteAllContratJPQL();
			l.debug("Suppression des contrats avec succes ");
			l.info("Out deleteAllContratJPQL() : ");
	}


}
