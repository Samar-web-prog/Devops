package tn.esprit.spring.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.dto.DepartementDTo;
import tn.esprit.spring.services.IDepartementService;

@Controller
public class ControllerDepartementImpl {
	@Autowired
	IDepartementService idepartementservice;
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		idepartementservice.affecterDepartementAEntreprise(depId, entrepriseId);
	}
	public Integer ajouterDepartement(DepartementDTo dep) {
		return idepartementservice.ajouterDepartement(dep);
	}
	public void deleteDepartementById(int depId) {
		idepartementservice.deleteDepartementById(depId);

	}

}
