package tn.esprit.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
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
	IDepartementService DepService; 
	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	@Test
	public void testGetAllDepartements()  {

		List <Departement> listDep = DepService.getAllDepartements();
		for(int i=0 ; i<listDep.size(); i++){
			l.info(listDep);
		}
		assertNotNull(listDep);
	}
	//Tester la fonction ajouterDepartement
	@Test
	public void testAjouterDepartement(){
		l.info("Je vais tester l'ajout d'une département");
		DepartementDTo d = new DepartementDTo ("IT Dep");
		Integer dAdded = DepService.ajouterDepartement(d);
		assertNotNull(dAdded);
		l.info("departement ajouté avec succés");
	}
	//Tester la méthode suppression d'une département
	@Test
	public void testDeleteDepartementById(){
		l.info("je vais tester la suppression d'une entreprise par son id");
		Integer depId =11 ;
		if(depId!=null) {
		int  i = DepService.deleteDepartementById(depId);
        assertEquals(1, i);
		}
		l.info("fin de testDeleteDepartementById");
	}
	//Tester la  affecter département à une entreprise
	@Test
	public void testAffecterDepartementAEntreprise() {
		l.info("je vais tester affectation de département à une entreprise");
		int entrepriseId = 1 ;
		int depId = 56;  // +1  update this id  by one each time I  run Junit test 
		l.debug("Récupération de département par son id");
		Departement Dep = deptRepoistory.findById(depId).orElse(null);
		l.debug("Récupération de l'entreprise par son id");
		Entreprise Ent = entrepriseRepoistory.findById(entrepriseId).orElse(null);
		if (Dep != null){
		Dep.setEntreprise(Ent);
		l.info("test finis avec succés");
		Departement dep = DepService.affecterDepartementAEntreprise(depId, entrepriseId);
		l.debug("Comparaison entre l'id de l'entreprise passé en paramétre et id de l'entrerise récupéré");
		Assert.assertEquals (Dep.getEntreprise().getId(),dep.getEntreprise().getId());
		l.info("fin de testAffecterDepartementAEntreprise");
		}
			
	}
	@Test
	public void testDesaffecterDepartementDuEntreprise () {
		l.info("In methode testDesaffecterDepartementDuEntreprise" );
		int entId = 1 ;
		int depId = 2 ;//+1
	    DepService.desaffecterDepartementDuEntreprise(depId, entId);
	    l.debug("je vais tester la récupération d'une département par son id" );
	    Departement Dep = deptRepoistory.findById(depId).orElse(null);
	    l.info("Récupération avec succés de département");
	    if(Dep != null){
		assertNull(Dep.getEntreprise());
	
		
		
	    }
	}
	@Test
	  public void testGetDepartementById(){
		int depId = 10;
		Departement dep= DepService.getDepartmentById(depId);
        assertNotNull(dep);
	}
	
}
