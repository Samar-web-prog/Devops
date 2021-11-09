package tn.esprit.spring.converter;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import tn.esprit.spring.dto.DepartementDTo;
import tn.esprit.spring.entities.Departement;
import java.util.stream.Collectors;

@Component
public class DepartementConverter {
	
	   //Transformer departement DTO en Departement
       public Departement depTodo(DepartementDTo departement) {
    	  // Departement d =new Departement();
    	   //d.setName(departement.getName());
    	   ModelMapper mapper =new ModelMapper();
    	   Departement map = mapper.map(departement, Departement.class);
   		return map;
       }
       
       //Transformer departement en deprtement DTO
       public DepartementDTo entityToDto(Departement dep) {
   		ModelMapper mapper =new ModelMapper();
   		DepartementDTo map = mapper.map(dep, DepartementDTo.class);
   		return map;
   		
   	}
       //Retourner la liste des departement DTO
       public  List<DepartementDTo> deplistToDto(List<Departement> Departement) {
   		return	Departement.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
   		
   	}
	
	
}
