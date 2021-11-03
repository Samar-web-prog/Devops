package tn.esprit.spring;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import tn.esprit.spring.dto.EmployeDTo;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class EmployeServiceImplTest {
	@Autowired
	IEmployeService iempserv;
	
	@Autowired
    EmployeRepository empr;
	
	

	@Autowired
	IEntrepriseService entrs;
	
	
	
	@Autowired
   ContratRepository contratrepo;
	
	@Autowired
	   DepartementRepository deprepo;
		
	Integer idE=9;
	private static final Logger l =LogManager.getLogger(EmployeServiceImplTest.class);
	
	@Test
	public void testAjouterEmploye()  {      
		 idE=iempserv.addOrUpdateEmploye(new EmployeDTo("siwar","hassen", "siwar.hassen@gmail.com","xx", true, Role.ADMINISTRATEUR));
		 l.info("employe ajouté avec succes");
		 Assert.notNull(idE,"employe added failed");
	}
	

	@Test
	public void testgetAllEmployeByEntreprise() {
		Entreprise e =entrs.getEntrepriseById(2);
		List<Employe> employes = iempserv.getAllEmployeByEntreprise(e);
	 l.info(employes);
	 Assert.notNull(employes,"get employe failed");
	}

	
	@Test
	public void testgetAllEmployeNamesJPQL(){
	
		
		List<String> le =iempserv.getAllEmployeNamesJPQL();
		 l.info(le);
		 Assert.notNull(le,"employes by names error");
	}

	
	@Test
	public void testgetAllEmployes(){
		List<Employe> employes =iempserv.getAllEmployes();
	      l.info(employes);
	      Assert.notNull(employes,"get employes null");
	}

	@Test
	public void testaffecterContratAEmploye() {
		int employeId = 1 ;
		int contratId = 2; 
		Contrat cont = contratrepo.findById(contratId).orElse(null);
		Employe emp = empr.findById(employeId).orElse(null);
		if (cont != null){
		cont.setEmploye(emp);
		iempserv.affecterContratAEmploye(contratId, employeId);
		l.info("contrat affecte a l'employe avec succes");
		  Assert.notNull(cont,"contrat null");
		}
			
	}
	
	@Test
	public void testgetEmployePrenomById() {
		
		 String prenom = iempserv.getEmployePrenomById(1);
		 l.info("prénom de l'employe recupere avec succes");
		  Assert.notNull(prenom,"prenom failed");
	}
	
	
	
	@Test
	public void testdesaffecterEmployeDuDepartement() {
		int entId = 1;
		int depId =2;
		iempserv.desaffecterEmployeDuDepartement(entId, depId);
	    Employe emp = empr.findById(entId).orElse(null);

	    if(emp != null){
			 l.info("employe recupere avec succes");
			 Assert.notNull(emp.getDepartements(),"emp failed");
		
	    }
	}
	
	@Test
	public void testmettreAjourEmailByEmployeIdJPQL() {
		 Integer nombre = iempserv.getNombreEmployeJPQL();
		 Assert.notNull(nombre,"nombre null");
	}
	
	
	@Test
	public void testaffecterEmployeADepartement() {
		int departementId = 1 ;
		int empId = 8;  // +1  update this id  by one each time I  run Junit test 
		Employe employe = empr.findById(empId).orElse(null);
		if (employe != null){
		 l.info("employe recupere avec succes");
		iempserv.affecterEmployeADepartement(empId, departementId);
		 l.info("employe affecté au département avec succes");
		 Assert.notNull(employe,"employe failed");
		}
			
	}
	
	@Test
	public void testauthenticate() {
		Employe e =iempserv.authenticate("siwar","xx");
		if(e!=null)
		{
			l.info("authentification terminée avec succes");
			 Assert.notNull(e,"authentication failed");
		}
		}
			
	
		@Test
	public void testDeleteEmployeById()
	{ 
			if(idE!=null)
			{
			int i = iempserv.deleteEmployeById(idE);
			l.info("suppression  avec succes");
	assertEquals(0, i);
			}
			else {
				int i = iempserv.deleteEmployeById(2);
				l.info("suppression  avec succes");
				assertEquals(0, i);

			}
	}
	
	
}