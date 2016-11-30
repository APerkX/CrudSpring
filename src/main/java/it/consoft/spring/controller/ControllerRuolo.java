package it.consoft.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import it.consoft.spring.form.FormRuolo;

import it.consoft.spring.model.beans.Ruolo;

import it.consoft.spring.model.service.GenericService;
import it.consoft.spring.model.service.RuoloService;

@Controller
public class ControllerRuolo {
	
	private static final Logger logger= Logger.getLogger(ControllerRuolo.class);
	
	
	
	
	@Autowired
	private RuoloService ruoloService;
	
	
	public void setRuoloService(RuoloService ruoloService){
		this.ruoloService= ruoloService;
	}
	
	
	

	//Carica i ruoli che viusaulizzerà nella tabella in pagina home
	@RequestMapping(value="/homeRuoli", method= {RequestMethod.POST, RequestMethod.GET})
	public String caricaRuoli(@ModelAttribute String esito,HttpServletRequest request, Model model){
		try{
			model.addAttribute("listaRuoli", ruoloService.getAll());
			model.addAttribute("formRuolo", new FormRuolo());
		}catch(Exception e){
			logger.error("Errore nel caricamento dei Ruoli", e);
			model.addAttribute("e", e);
			return "error";
		}
		//reindirizza nella pagina principale home
		return "homeRuoli";
	}
	
	//prepara la pagina di inserimento trasferendogli un oggetto formRuolo
	//e una lista di ruoli da poter selezionare
	@RequestMapping(value= "/preparaInserimentoRuolo", method= {RequestMethod.GET})
	public String preparaInserimento(FormRuolo formRuolo, Model model){
		
		try{
			//model.addAttribute("listaRuoli", ruoloService.getAll());
			if (formRuolo== null) {
				formRuolo= new FormRuolo();
			}
			model.addAttribute("formRuolo", formRuolo);
		}catch (Exception e){
			logger.error("Errore nel caricamento dei Ruoli", e);
			return "error";
		}
		//reindirizza nella pagina inserimento
		return "inserimentoRuolo";
	}
	
	
	//esegue l'inserimento di una riga Ruolo nel DB
	//facendo prima però dei controlli
	@RequestMapping(value= "/inserimentoRuolo", method= {RequestMethod.POST})
	public String inserimentoRuolo(@Valid @ModelAttribute FormRuolo formRuolo, BindingResult result, Model model){
		
		
		try{
			//controlla che non ci siano stati errori durante l'inserimento 
			if(result.hasErrors()){
				//model.addAttribute("listaRuoli", ruoloService.getAll());
				model.addAttribute("formRuolo", formRuolo);
				return "inserimentoRuolo";
				}
			
			// si fa un controllo sulla descrizione per vedere se è gia presente in DB
			Ruolo ruolo= formRuolo.getRuoloFromForm();
			if(!ruoloService.controlloDesc(ruolo)){
				//se non è già presente fa l'inserimento e ritorna un msg. di buon esito
				ruoloService.aggiungi(ruolo);
				model.addAttribute("esito", "INSERIMENTO RIUSCITO");
			}else{
				//altrimenti ritorna nella pagina 'inserimento' 
				model.addAttribute("esito", "Descrizione già presente");
				//model.addAttribute("listaRuoli", ruoloService.getAll());
				model.addAttribute("formRuolo", formRuolo);
				return "inserimentoRuolo";
				
			}
			
			model.addAttribute("listaRuoli", ruoloService.getAll());
			model.addAttribute("formRuolo", new FormRuolo());
			return "homeRuoli";
		}catch(Exception e){
			logger.error("Errore nell'inseriemnto del Ruolo " + formRuolo ,  e);
			return "error";
		}
	}
	
	
	//prepara la jsp di modifica ricevendo un ID che verrà utilizzato per ottenere un oggetto Ruolo
	@RequestMapping(value= "/preparaModificaRuolo", method = RequestMethod.POST)
	public String preparaModifica(FormRuolo formRuolo, Model model){
		
		try{
			//trasferisce la lista dei ruoli alla pagina modifica
			//model.addAttribute("listaRuoli", ruoloService.getAll());
			
			//controlla che l'oggetto formAnagrafica non sia nullo o
			//che l'id ricevuto dalla pagina iniziale non sia 0
			if(formRuolo==null || formRuolo.getId()==0){
				logger.error("ERRORE-- Problema con 'formRuolo' o con l'id ");
				return "error";
			}
			//oggetto formRuolo inizializzato con un tipo di costruttore 
			//che passa come parametro un oggetto Ruolo 
			//(in questo caso recuperato tramite ID(getElementById) preso dalla home)
			formRuolo = new FormRuolo(ruoloService.GetElementById(formRuolo.getId()));
			model.addAttribute("formRuolo", formRuolo);
		}catch (Exception e){
			logger.error("Errore nel caricamento dei ruoli", e);
			return "error";
		}
		return "modificaRuolo";
	}
	
	//esegue modifica della riga selezionata facendo prima dei controlli sui 
	//dati inseriti
	@RequestMapping(value="/ruoloModifica", method= RequestMethod.POST)
	public String ruoloModifica(@Valid @ModelAttribute FormRuolo formRuolo, Model model, BindingResult result ){
		
		try{
			if(result.hasErrors()) {
				model.addAttribute("formRuolo", formRuolo);
				//model.addAttribute("listaRuoli", ruoloService.getAll());
			    return "modificaRuolo";
			}
			
			Ruolo ruolo = formRuolo.getRuoloFromForm();
			if(!ruoloService.controlloDesc(ruolo)){
				//se non è già presente fa l'inserimento e ritorna un msg. di buon esito
				ruoloService.modifica(ruolo);
				model.addAttribute("esito", "MODIFICA RIUSCITA");
			}else{
				//altrimenti ritorna nella pagina 'inserimento' 
				model.addAttribute("esito", "Login già presente");
				//model.addAttribute("listaRuoli", ruoloService.getAll());
				model.addAttribute("formRuolo", formRuolo);
				return "modificaRuolo";
				}
			model.addAttribute("listaRuoli", ruoloService.getAll());
			model.addAttribute("formRuolo", formRuolo);
			return "homeRuoli";
			
			}catch (Exception e){
				logger.error("ERRORE nella modifica di Ruolo" + formRuolo,  e);
				return "error";
			}
		
		
	}
	
	
	
	@RequestMapping(value="/cancellaRuolo", method= RequestMethod.POST)
	public String cancellaRuolo(int id, Model model){
		try{
			if(id != 0){
				ruoloService.elimina(new Ruolo(id));
				model.addAttribute("esito", "CANCELLAZIONE RIUSCITA");
			}else{
				logger.error("ID ricevuto non utilizzabile");
				return "error";
			}
			model.addAttribute("listaRuoli", ruoloService.getAll());
			model.addAttribute("formRuolo", new FormRuolo());
			return "homeRuoli";
		}catch (Exception e){
			logger.error("Errore nella cancellazione del ruolo" + id, e);
			return "error";
		}
	}
}
	
	