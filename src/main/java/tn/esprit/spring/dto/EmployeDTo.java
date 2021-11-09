package tn.esprit.spring.dto;

import lombok.Data;
import tn.esprit.spring.entities.Role;

@Data
public class EmployeDTo {
   private int id;
   private String nom;
   private String prenom;
   private String email;
   private String password;
   private boolean actif;
   private Role role;
   
   

public EmployeDTo() {
	super();
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public String getPrenom() {
	return prenom;
}

public void setPrenom(String prenom) {
	this.prenom = prenom;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public boolean isActif() {
	return actif;
}

public void setActif(boolean actif) {
	this.actif = actif;
}

public Role getRole() {
	return role;
}

public void setRole(Role role) {
	this.role = role;
}

public void setId(int id) {
	this.id = id;
}

public EmployeDTo(String nom, String prenom, String email, String password, boolean actif, Role role) {
	super();
	this.nom = nom;
	this.prenom = prenom;
	this.email = email;
	this.password = password;
	this.actif = actif;
	this.role = role;
}

public EmployeDTo(int id, String nom, String prenom, String email, String password, boolean actif, Role role) {
	super();
	this.id = id;
	this.nom = nom;
	this.prenom = prenom;
	this.email = email;
	this.password = password;
	this.actif = actif;
	this.role = role;
}

public EmployeDTo(int id) {
	super();
	this.id = id;
}

public int getId() {
	return id;
}




   }
