package tn.esprit.spring.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DepartementDTo {
   private int id;
   private String name;
private int getId() {
	return id;
}

public DepartementDTo(String name) {
	super();
	this.name = name;
}
public DepartementDTo() {
	super();
	// TODO Auto-generated constructor stub
}
public void setName(String name) {
	this.name = name;
}
public String getName() {
	return name;
}

   }
