package tn.esprit.spring.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EntrepriseDTo {
	  private int id;
	  private String name;
	  private String raisonSocial;
	  
	  public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getRaisonSocial() {
			return raisonSocial;
		}

		public void setRaisonSocial(String raisonSocial) {
			this.raisonSocial = raisonSocial;
		}

		public EntrepriseDTo(String name, String raisonSocial) {
			super();
			this.name = name;
			this.raisonSocial = raisonSocial;
		}

		public EntrepriseDTo() {
			super();
			// TODO Auto-generated constructor stub
		}
		
}

