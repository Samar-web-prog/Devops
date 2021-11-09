package tn.esprit.spring.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.converter.DepartementConverter;
import tn.esprit.spring.dto.DepartementDTo;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.services.IDepartementService;

@RestController
public class RestControlDepartement {
	@Autowired
	IDepartementService idepartementservice;
	@Autowired
	DepartementConverter converter;

	@Autowired
	DepartementRepository deptRepoistory;
	

	@GetMapping("/getDepartement")
	public List<Departement> getAllDepartement(){
		return idepartementservice.getAllDepartements();
	}
	@PostMapping("/ajouterDepartement")
 	@ResponseBody
	public Integer  ajouterDepartement(@RequestBody DepartementDTo dep) {
		return idepartementservice.ajouterDepartement(dep);
	}
 // http://localhost:8081/SpringMVC/servlet/affecterDepartementAEntreprise/1/1
    @PutMapping(value = "/affecterDepartementAEntreprise/{iddept}/{identreprise}") 
	public void affecterDepartementAEntreprise(@PathVariable("iddept")int depId, @PathVariable("identreprise")int entrepriseId) {
		idepartementservice.affecterDepartementAEntreprise(depId, entrepriseId);
	}
 // URL : http://localhost:8081/SpringMVC/servlet/deleteDepartementById/3
    @DeleteMapping("/deleteDepartementById/{iddept}") 
	@ResponseBody 
	public void deleteDepartementById(@PathVariable("iddept") int depId) {
    	idepartementservice.deleteDepartementById(depId);
	} 
    @PutMapping(value = "/desaffecterDepartementAEntreprise/{iddept}/{identreprise}") 
    public void desaffecterDepartementEntreprise(@PathVariable("iddept")int depId, @PathVariable("identreprise")int entrepriseId){
    	idepartementservice.desaffecterDepartementDuEntreprise(depId, entrepriseId);
    
    }

}
