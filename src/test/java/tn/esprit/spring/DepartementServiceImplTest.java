package tn.esprit.spring;


import java.util.List;
import org.springframework.util.Assert;

import org.junit.Test;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.dto.DepartementDTo;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IDepartementService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DepartementServiceImplTest {
	private static final Logger l =LogManager.getLogger(DepartementServiceImplTest.class);

	@Autowired
	IDepartementService departementService; 
	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	@Test
	public void testGetAllDepartements()  {

		List <Departement> listDep = departementService.getAllDepartements();
		for(int i=0 ; i<listDep.size(); i++){
			l.info(listDep);
		}
		Assert.notNull(listDep,"List n'existe pas");

	}
	//Tester la fonction ajouterDepartement
	@Test
	public void testAjouterDepartement(){
		l.info("Je vais tester l'ajout d'une département");
		DepartementDTo d = new DepartementDTo ("IT Dep");
		Integer dAdded = departementService.ajouterDepartement(d);
		Assert.notNull(dAdded,"Département non ajouté");
		l.info("departement ajouté avec succés");
	}
	//Tester la méthode suppression d'une département
	@Test
	public void testDeleteDepartementById(){
		l.info("je vais tester la suppression d'une entreprise par son id");
		Integer depId =11 ;
		int  i = departementService.deleteDepartementById(depId);
        Assert.notNull(i, "Départment ne peut pas etre supprimé");
		
		l.info("fin de testDeleteDepartementById");
	}
	//Tester la  affecter département à une entreprise
	@Test
	public void testAffecterDepartementAEntreprise() {
		l.info("je vais tester affectation de département à une entreprise");
		int entrepriseId = 2;
		int depId = 60;  // +1  update this id  by one each time I  run Junit test 
		l.debug("Récupération de département par son id");
		Departement departement = deptRepoistory.findById(depId).orElse(null);
		l.debug("Récupération de l'entreprise par son id");
		Entreprise entreprise = entrepriseRepoistory.findById(entrepriseId).orElse(null);
		if (departement != null && entreprise!=null){
			departement.setEntreprise(entreprise);
			departementService.affecterDepartementAEntreprise(depId, entrepriseId);
		l.info("test finis avec succés");
		l.debug("Comparaison entre l'id de l'entreprise passé en paramétre et id de l'entrerise récupéré");
		Assert.notNull(departement.getId(),"Cette département e peut pas etre affecté a l'entreprise");
		l.info("fin de testAffecterDepartementAEntreprise");
		}
			
	}
	@Test
	public void testDesaffecterDepartementDuEntreprise () {
		l.info("In methode testDesaffecterDepartementDuEntreprise" );
		int entId = 2 ;
		int depId = 61 ;//+1
	    departementService.desaffecterDepartementDuEntreprise(depId, entId);
	    l.debug("je vais tester la récupération d'une département par son id" );
	    Departement departement = deptRepoistory.findById(depId).orElse(null);
	    l.info("Récupération avec succés de département");
	    if(departement != null){
		Assert.notNull(departement,"Ne peut pas etre désaffecter de l'entreprise");
	    }
	}
	@Test
	  public void testGetDepartementById(){
		int depId = 10;
		Departement dep= departementService.getDepartmentById(depId);
		Assert.notNull(dep,"departement n'exite pas");
	}
	
}