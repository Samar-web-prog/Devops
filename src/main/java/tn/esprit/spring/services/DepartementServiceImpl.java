package tn.esprit.spring.services;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class DepartementServiceImpl implements IDepartementService {


	@Autowired
	DepartementRepository deptRepoistory;
    @Autowired
    EntrepriseRepository entrepriseRepoistory;
	private static final Logger l = LogManager.getLogger(DepartementServiceImpl.class);
	
    public List<Departement> getAllDepartements() {
    	try{
			l.info("In getAllDepartements()");
			l.debug("Je vais récupérer la liste département");
			List<Departement> depList=(List<Departement>) deptRepoistory.findAll();
			l.debug("La liste de département est récupéré avec succés");
			l.info("Out getAllDepartements()");		
			return depList;
    	} catch (Exception e) {
			l.error("erreur dans la methode getAllDepartements() :"+e);
			return null;
		}
			
	}
    	
    	
    	
	public Integer ajouterDepartement(Departement dep) {
		try{
			l.info("In ajouterDepartement()");
			deptRepoistory.save(dep);
			l.debug("departement ajouté et portant la ref  = "+dep.getId());
			l.info("Out ajouterDepartement()");
			return dep.getId();
		} catch (Exception e) {
			l.error("erreur dans la methode ajouterContrat() :"+e);
			return null;
		}
		
	}
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
				try {
					l.info("In affecterDepartementAEntreprise()");
					l.debug("je vais récupérer l'entreprise par son id");
					Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
					l.debug("entreprise récupérée avec succés avec une référence :"+entrepriseManagedEntity.getId());
					l.debug("je vais récupérer le departement par son id");
					Departement depManagedEntity = deptRepoistory.findById(depId).get();
					l.debug("je vais affecter l'entreprise récupérer au département ");
					depManagedEntity.setEntreprise(entrepriseManagedEntity);
					l.debug("entreprise affectée à l'entreprise avec succés dont l'id est de département est"+depManagedEntity.getId());
					deptRepoistory.save(depManagedEntity);	
					l.debug("entreprise est affectée a l'entreprise avec succées,id de département est   = "+depManagedEntity.getId());
					l.info("Out ajouterDepartement()");

				}
				catch (Exception e) {
					l.error("erreur dans la methode affecterDepartementAEntreprise() :"+e);
				}
		      
	}
	@Transactional
	public void deleteDepartementById(int depId) {
		try {
			l.info("In deleteDepartementById()");
			l.debug("je vais supprimer le département par son id:"+depId);
		    deptRepoistory.delete(deptRepoistory.findById(depId).get());
			l.debug("Département supprimé avec succés");
			l.info("Out deleteDepartementById()");

		}
		catch (Exception e) {
			l.error("erreur dans la methode deleteDepartementById() :"+e);
		}

		 
	}
	

}
