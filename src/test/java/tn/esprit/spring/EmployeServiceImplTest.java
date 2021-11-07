package tn.esprit.spring;

import static org.junit.Assert.assertNotEquals;
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
		
	Integer idE=8;
	private static final Logger l =LogManager.getLogger(EmployeServiceImplTest.class);
	
	@Test
	public void testaddOrUpdateEmploye()  {      
		 l.info("in testaddOrUpdateEmploye()");
		 idE=iempserv.addOrUpdateEmploye(new EmployeDTo("test","test1", "siw@gmail.com","xx", true, Role.ADMINISTRATEUR));
	
		 Assert.notNull(idE,"employe added failed");
	}
	
	@Test
public void testauthenticate() {
	Employe e =iempserv.authenticate("siwar.hassen@gmail.com","xx");
	if(e!=null)
	{
		l.info("authentification terminée avec succes");
		 
	}
	Assert.notNull(e,"authentication failed");
	}
	
	@Test
	public void testgetAllEmployes(){
		List<Employe> employes =iempserv.getAllEmployes();
	      l.info(employes);
	      Assert.notNull(employes,"get employes null");
	}
	
	@Test
	
	public void testGetSalaireByEmployeIdJPQL() {


		assertNotEquals(0,iempserv.getSalaireByEmployeIdJPQL(idE));
	}
	
	
	
	@Test
public void testDeleteEmployeById()
{ 
		assertNotEquals(0, iempserv.deleteEmployeById(1));
	
}


	
	@Test
public void testgetEmployePrenomById() {
		assertNotEquals("hassen",iempserv.getEmployePrenomById(idE));
}


	
	@Test
	public void testgetNombreEmployeJPQL() {
		 Integer nombre = iempserv.getNombreEmployeJPQL();
		 Assert.notNull(nombre,"nombre null");
		 
		 
	}
	
	
		@Test
	public void testgetAllEmployeNamesJPQL(){
	
		
		List<String> le =iempserv.getAllEmployeNamesJPQL();
		 l.info("resultat de test getAllEmployeNamesJPQL : "+le);
		 Assert.notNull(le,"employes by names error");
	}
	

		@Test
		public void testaffecterContratAEmploye() {
			int employeId = 8 ;
			int contratId = 1; 
			Contrat cont = contratrepo.findById(contratId).orElse(null);
			Employe emp = empr.findById(employeId).orElse(null);
			if(cont!=null)
			{
				cont.setEmploye(emp);
				iempserv.affecterContratAEmploye(contratId, employeId);
				
			}
			  Assert.notNull(cont,"contrat null");
		}
		

	@Test
	public void testgetAllEmployeByEntreprise() {
		Entreprise e =entrs.getEntrepriseById(2);
		List<Employe> employes = iempserv.getAllEmployeByEntreprise(e);
	 l.info(employes);
	 Assert.notNull(employes,"get employe failed");
	}
	

	
		@Test
	public void testdesaffecterEmployeDuDepartement() {
		int entId = 1;
		int depId =2;
		iempserv.desaffecterEmployeDuDepartement(entId, depId);
	    Employe emp = empr.findById(entId).orElse(null);
			 Assert.notNull(emp,"emp failed");
		
	    
	}
	
		@Test
	public void testaffecterEmployeADepartement() {
		int departementId = 1 ;
		int empId = 8;  // +1  update this id  by one each time I  run Junit test 
		Employe employe = empr.findById(empId).orElse(null);
		iempserv.affecterEmployeADepartement(empId, departementId);

		 Assert.notNull(employe,"employe failed");
		
			
	}
	
	
		@Test
		public void testMettreAjourEmailByEmployeId() {
			iempserv.mettreAjourEmailByEmployeId("modifier@mail.com", idE);
		}
		
		@Test
		public void testMettreAjourEmailByEmployeIdJPQL() {
		
			iempserv.mettreAjourEmailByEmployeIdJPQL("modifier@mail.com", idE);
		}

	
}