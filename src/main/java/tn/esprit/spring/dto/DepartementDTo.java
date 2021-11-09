package tn.esprit.spring.dto;

import lombok.Data;

@Data
public class DepartementDTo {
   private int id;
   private String name;

	  public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

public DepartementDTo(String name) {
	super();
	this.name = name;
}
public DepartementDTo() {
	super();
}
public void setName(String name) {
	this.name = name;
}
public String getName() {
	return name;
}




   }
