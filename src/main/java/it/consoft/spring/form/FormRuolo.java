package it.consoft.spring.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import it.consoft.spring.model.beans.Ruolo;

public class FormRuolo {
	
	
	private Integer id;
	
	
	@NotBlank( message= "* Descrizione obbligatoria")
	@Size(max= 50, message="Sono permessi massimo 50 caratteri")
	private String descrizione;

public FormRuolo(){
		super();
	}
	 
public FormRuolo(Ruolo ruolo){
		this.id= ruolo.getId();
		this.descrizione= ruolo.getDescrizione();
	}


public FormRuolo(Integer id, String descrizione){
	this.id= id;
	this.descrizione= descrizione;
}
	 
	 public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	 
	public String getDescrizione() {
		return descrizione;
	}


	 
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
	public Ruolo getRuoloFromForm(){
		Ruolo result= new Ruolo();
		result.setId(this.id);
		result.setDescrizione(this.descrizione.toUpperCase());
		return result;
	}

	
	
	

}
