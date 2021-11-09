package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.converter.EmployeConverter;
import tn.esprit.spring.dto.EmployeDTo;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	 @Autowired
	 EmployeConverter converter;

	private static final Logger l = LogManager.getLogger(EmployeServiceImpl.class);

	@Override
	public Integer addOrUpdateEmploye(EmployeDTo employe) {
		
		try {
			l.info("In addOrUpdateEmploye()");
			l.debug("je vais enregistrer ou modifier l'employe");
			Employe emp=converter.empTodo(employe);
			employeRepository.save(emp);
			l.debug("je viens de finir la modification ou l'ajout de l'employe portant l'id: "+employe.getId() );
			l.info("Out addOrUpdateEmploye()");
		return employe.getId();
		}
		catch (Exception e) {
		       l.error("erreur in methode addOrUpdateEmploye() :" +e);	
		       return null;       
				}	
	}


	
	@Override
	public Employe authenticate(String login, String password) {
			
		try {
			l.info("In authenticate()");
			l.debug("je vais faire l'authentification");
			Employe authenticate=employeRepository.getEmployeByEmailAndPassword(login, password);
			if (authenticate != null) {
				l.info("success authentification");
			}else {
				if (login.equals("") || password.equals("")) {
					l.warn("login and password musn't be empty ! ");
				}else {
					l.warn(" Employe doesn't exist!");
				}
			}
			l.debug(" je viens de terminer authentication");
			l.info("Out authenticate()");
			return authenticate;
			
		}
		catch (Exception e) {
		       l.error("erreur in  authenticate() :" +e);	
		       return null;       
				}
	}
	

	

	
	public List<Employe> getAllEmployes() {
		List<Employe> listemployes=new ArrayList<>();
		try
		{
			l.info("methode getAllEmployes ");
			l.debug("je vais recuperer la liste de tous les employees");
		 listemployes=(List<Employe>) employeRepository.findAll();
			l.debug("la liste des employees est"+listemployes);
			l.info("Out getAllEmployes with success");
		}
		catch (Exception e) {	
			l.error("erreur In getAllEmployes : " +e);
		}
		return listemployes;
	}
	
	
	
	
	public float getSalaireByEmployeIdJPQL(int employeId) {
		
		try
		{
			l.info("methode getSalaireByEmployeIdJPQL ");		
			l.debug("je vais recuperer le salaire de l'employe by id");
			
			float salairebyembyid=employeRepository.getSalaireByEmployeIdJPQL(employeId);
			l.debug("le salaire de l'employe by id est"+salairebyembyid);
			l.info("Out getSalaireByEmployeIdJPQL with success");
			
			return salairebyembyid;
		}
		catch (Exception e) {
			l.error("erreur in methode getSalaireByEmployeIdJPQL : " +e);
			return 0;
		}
	
		
		
	}
	
	public int deleteEmployeById(int employeId)
	{
		try {
			l.info("In deleteEmployeById ");
			l.debug("je vais recuperer l'employe selon l'id ");
	     	Optional<Employe> employe = employeRepository.findById(employeId);
	     	if(employe.isPresent())
	     	{
	     		for(Departement dep : employe.get().getDepartements()){
	     			l.debug("je vais desaffecter l'employe d'un departement ");
	     			dep.getEmployes().remove(employe.get());
	     		}
	     		l.debug("je vais supprimer l'employe par son id"+employeId);
	     		employeRepository.delete(employe.get());
	     		l.debug("je viens de faire deleteEmployeById ");
	     		l.info("Out deleteEmployeById with success");
	     		return 1;
	     	}	
	     	else
	     	{
	     		l.warn("there is no employe");
	     		return 0;	
	     	}	
		}	
		catch (Exception e) {
			l.error("erreur methode deleteEmpolyeById() : " +e);
			return 0;
		}
	}
	
	
	public String getEmployePrenomById(int employeId) {
		String prenom="";
		try {
			
			l.info(" In getEmployePrenomById() ");
			l.debug("je vais recuperer le prenom de l'employe by id ");
		Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);
		if(employeManagedEntity.isPresent())
		{prenom=employeManagedEntity.get().getPrenom();
			l.debug("je viens de recuperer getEmployePrenomById");
			l.info("out getEmployePrenomById()"+prenom);
			
			
		}
		else
		{
			l.warn("employe doesn't exist with id"+employeId);
		}
		}
		catch (Exception e) {
			l.error("erreur in methode getEmployeeById() : " +e);
			
		}
		return prenom;

	}
	
	public Integer getNombreEmployeJPQL() {
		int nbremploye=0;
		try {
			l.info("In getNombreEmployeJPQL() ");
			l.debug("je vais recevoir le nombre des employes ");
			 nbremploye=employeRepository.countemp();
			l.debug("le nombre des employes est "+nbremploye);
			l.info("Out deleteEmployeById");
		}
		catch (Exception e) {
			l.error("erreur methode getNombreEmployeJPQL : " +e);	
		}
		return nbremploye;
	}
	
	public List<String> getAllEmployeNamesJPQL() {
		
		List <String> namesofemployes = new ArrayList<>() ; 
		try {
			l.info("In getAllEmployeNamesJPQL() ");			
			l.debug("je vais recuperer les noms des employes");
			namesofemployes = employeRepository.employeNames();
			l.debug("je viens de recuperer les noms des employes");
			l.info("out getAllEmployeNamesJPQL()");
		}catch(Exception e) {
			l.error(e.toString());
		}
		return namesofemployes;
	}
	
	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		
		if(email.equals(""))
		{
			l.warn("this email is empty ");
		}
		else
		{
			try
			{
				l.info("In mettreAjourEmailByEmployeIdJPQL() ");			
				l.debug("je vais mettre a jour email by employe");
				employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);
				l.debug("l'email est mis à ajour");
				l.info("Out mettreAjourEmailByEmployeIdJPQL() with success");
			}
			catch (Exception e) {
				l.error("erreur in methode mettreAjourEmailByEmployeIdJPQL : " +e);
			}
		}

	}
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		List<Employe> allemployees=new ArrayList<>();
		try
		{
			l.info("In getAllEmpaffeloyeByEntreprise() ");			
			l.debug("je vais recuperer des employes By entreprise");
			allemployees=employeRepository.getAllEmployeByEntreprisec(entreprise);
			l.debug("la liste des employeesByentreprise"+allemployees);
			l.info("Out getAllEmployeByEntreprise() with success");
		
		}
		catch (Exception e) {
			
			l.error("erreur in methode getAllEmployeByEntreprise:" +e);
		
		}
		return allemployees;
	}
	
	
	
	public Double getSalaireMoyenByDepartementId(int departementId) {
		
		try
		{
			l.info("methode getSalaireMoyenByDepartementId() ");
			l.debug("je vais recuperer le salaire moyenBydepartement");
			double salaire=employeRepository.getSalaireMoyenByDepartementId(departementId);
			l.debug("le salaire de l'employe by departement est"+salaire);
			l.info("Out getSalaireMoyenByDepartementId() with success");
			
			return salaire;
		}
		catch (Exception e) {
			l.error("erreur in methode getSalaireMoyenByDepartementId : " +e);
			return null;
		}
		
		
	}

	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		
try {
	l.info("In affecterEmployeADepartement()");
	l.debug("je vais recuperer le departement");
	Optional<Departement> depManagedEntity = deptRepoistory.findById(depId);
	
	l.debug("je vais recuperer l'employe");
	Employe employeManagedEntity = employeRepository.findById(employeId).orElse(null);
	 if(depManagedEntity.isPresent())
	 {
		 
	 
		if( depManagedEntity.get().getEmployes() == null){

			List<Employe> employes = new ArrayList<>();
			l.debug("je vais remplir la liste des employes");
			employes.add(employeManagedEntity);
			l.debug("je vais modifier la liste des employes dans le departement");
			depManagedEntity.get().setEmployes(employes);
		}else{
			l.debug("je vais affecter les employes au departement");
			depManagedEntity.get().getEmployes().add(employeManagedEntity);
		}


		deptRepoistory.save(depManagedEntity.get()); 
		l.debug("je viens de finir effecterEmployeAdepartement ");
		l.info("Out effecterEmployeAdepartement()");
		}
	 else
	 {
		 l.warn("there is no department");
	 }
	 
	}
	catch (Exception e) {
		l.error("erreur in methode affecterEmployeADepartement() : " +e);
	
	}		

	}

	
	
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		
		try{
			l.info("In desaffecterEmployeDuDepartement() ");
			
			
			l.debug("je vais recuperer le departement selon l'id ");
			Departement dep = deptRepoistory.findById(depId).orElse(null);
			if(dep!=null){
				int employeNb = dep.getEmployes().size();
				l.info("supprimer Employee d'un Departement : " + dep.getName() );
				for(int index = 0; index < employeNb; index++){
					if(dep.getEmployes().get(index).getId() == employeId){
						l.debug("je vais supprimer un employe dans un departement");
						dep.getEmployes().remove(index);
						l.info("Employee supprime avec succes !");
						break;
				
					}
					l.debug("je viens de finir desaffecterEmployeDuDepartement");
					l.info("Out desaffecterEmployeDuDepartement()");
				}
					}
			else
			{
				l.warn("no department with id"+depId);
			}
		}
			catch (Exception e) {
				l.error("erreur in methode desaffecterEmployeDuDepartement() : " +e);}
	} 
	


	public void affecterContratAEmploye(int contratId, int employeId) {
	
		try {	
			l.info("In affecterContratAEmploye() ");
			l.debug("je vais recuperer le contrat by id");
		Optional<Contrat> contratManagedEntity = contratRepoistory.findById(contratId);
		l.debug("recuperation de contrat"+contratManagedEntity);
		
		
		l.debug("je vais recuperer l'employe by id");
		Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);
		if (employeManagedEntity.isPresent()) {
			l.debug("je vais affecter le contrat à un employe");
			if(contratManagedEntity.isPresent())
			{
				contratManagedEntity.get().setEmploye(employeManagedEntity.get());
				contratRepoistory.save(contratManagedEntity.get());
				l.debug(" je viens de finir  affecterContratAEmploye ");
				l.info(" out affecterContratAEmploye() ");
			}
			
			}
		else
		{
			l.warn("therie is no employe with id"+employeId);
		}
		
		}	
		catch (Exception e) {
			l.error("erreur in methode affecterContratAEmploye() : " +e);
			
		}
		

	}

	



	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		
		try {
			l.info("In mettreAjourEmailByEmployeId()");
			
			
			l.debug("je vais recuperer l'id de l'employee");
		Employe employe =employeRepository.findById(employeId).orElse(null);	
		
		
		if(employe!=null){
			if(!email .equals(""))
			{
				l.debug("je vais modifier l'email "+ email +"de l'employe portant l'id"+employe.getId());
				employe.setEmail(email);
				employeRepository.save(employe);	
				l.debug("je viens de modifier l'email de l'employe "+employe.getId());
				l.info("Out mettreAjourEmailByEmployeId avec success ");
			}
			else
			{
				l.warn("email vide");
			}
			
		
		}
		else
		{
			l.warn("employe with id "+employeId +"is not found");
		}
		
		
		
		}
		catch (Exception e) {
			l.error("erreur in methode mettreAjourEmailByEmployeId(): " +e);
		}

	}


}