package it.consoft.spring.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import it.consoft.spring.model.beans.Anagrafica;
import it.consoft.spring.model.beans.Ruolo;

public class FormAnagrafica {
	
	
	private int id;
	
	@NotBlank(message= "* Login obbligatoria")
	@Size(max= 50, message= "*Sono permessi massimo 50 caratteri")
	private String login;
	
	
	@NotBlank(message= "* Password obbligatoria")
	@Size(max= 50, message= "*Sono permessi massimo 50 caratteri")
	private String password;
	
	
	@NotNull
	@Min(value=1)
	private int idRuolo;
	
	public FormAnagrafica() {
		super();
	}

	
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	
	public String getLogin() {
		return login;
	}

	
	public void setLogin(String login) {
		this.login = login;
	}

	
	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}

	
	public int getIdRuolo() {
		return idRuolo;
	}

	
	public void setIdRuolo(int idRuolo) {
		this.idRuolo = idRuolo;
	}
	
	//costruttore che inizializza le variabili locali con quelle passate come parametro
	public FormAnagrafica(Anagrafica anagrafica){
		this.id= anagrafica.getId();
		this.login= anagrafica.getLogin();
		this.password= anagrafica.getPassword();
		this.idRuolo= anagrafica.getRuolo().getId();
	}
	
	//inizializza le variabili del bean Anagrafica con quelle presenti in questa classe
	//le inserice in un oggetto Anagrafica e lo ritorna 
	public Anagrafica getAnagraficaFromForm(){
		
		Anagrafica result= new Anagrafica();
		result.setId(this.id);
		result.setLogin(this.login);
		result.setPassword(this.password);
		result.setRuolo(new Ruolo(this.idRuolo));
		return result;
	}
	
	

}
