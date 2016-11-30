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

import it.consoft.spring.form.FormAnagrafica;
import it.consoft.spring.model.beans.Anagrafica;
import it.consoft.spring.model.beans.Ruolo;
import it.consoft.spring.model.service.AnagraficaService;
import it.consoft.spring.model.service.GenericService;

@Controller
public class ControllerAnagrafica {
	
	private static final Logger logger= Logger.getLogger(ControllerAnagrafica.class);
	
	@Autowired
	private AnagraficaService anagraficaService;
	
	
	public void setAnagraficaService(AnagraficaService anagraficaService){
		this.anagraficaService= anagraficaService;
	}
	
	
	@Autowired
	private GenericService<Ruolo> ruoloService;
	
	
	public void setRuoloService(GenericService<Ruolo> ruoloService){
		this.ruoloService= ruoloService;
	}
	
	
	

	//Carica le anagrafiche che viusaulizzerà nella tabella in pagina home
	@RequestMapping(value="/", method= {RequestMethod.POST, RequestMethod.GET})
	public String caricaAnagrafiche(@ModelAttribute String esito,HttpServletRequest request, Model model){
		try{
			model.addAttribute("listaAnagrafiche", anagraficaService.getAll());
			model.addAttribute("formAnagrafica", new FormAnagrafica());
		}catch(Exception e){
			logger.error("Errore nel caricamento delle Anagrafiche", e);
			model.addAttribute("e", e);
			return "error";
		}
		//reindirizza nella pagina principale home
		return "home";
	}
	
	//prepara la pagina di inserimento trasferendogli un oggetto formAnagrafica
	//e una lista di ruoli da poter selezionare
	@RequestMapping(value= "/preparaInserimento", method= {RequestMethod.GET})
	public String preparaInserimento(FormAnagrafica formAnagrafica, Model model){
		
		try{
			model.addAttribute("listaRuoli", ruoloService.getAll());
			if (formAnagrafica== null) {
				formAnagrafica= new FormAnagrafica();
			}
			model.addAttribute("formAnagrafica", formAnagrafica);
		}catch (Exception e){
			logger.error("Errore nel caricamento dei Ruoli", e);
			return "error";
		}
		//reindirizza nella pagina inserimento
		return "inserimento";
	}
	
	
	//esegue l'inserimento di una riga Anagrafica nel DB
	//facendo prima però dei controlli
	@RequestMapping(value= "/inserimentoAnagrafica", method= {RequestMethod.POST})
	public String inserimentoAnagrafica (@Valid @ModelAttribute FormAnagrafica formAnagrafica, BindingResult result, Model model){
		
		
		try{
			//controlla che non ci siano stati errori durante l'inserimento 
			if(result.hasErrors()){
				model.addAttribute("listaRuoli", ruoloService.getAll());
				model.addAttribute("formAnagrafica", formAnagrafica);
				return "inserimento";
				}
			
			// si fa un controllo su la login per vedere se è gia presente in DB
			Anagrafica anagrafica= formAnagrafica.getAnagraficaFromForm();
			if(!anagraficaService.controllaLogin(anagrafica)){
				//se non è già presente fa l'inserimento e ritorna un msg. di buon esito
				anagraficaService.aggiungi(anagrafica);
				model.addAttribute("esito", "INSERIMENTO RIUSCITO");
			}else{
				//altrimenti ritorna nella pagina 'inserimento' 
				model.addAttribute("esito", "Login già presente");
				model.addAttribute("listaRuoli", ruoloService.getAll());
				model.addAttribute("formAnagrafica", formAnagrafica);
				return "inserimento";
				
			}
			
			model.addAttribute("listaAnagrafiche", anagraficaService.getAll());
			model.addAttribute("formAnagrafica", new FormAnagrafica());
			return "home";
		}catch(Exception e){
			logger.error("Errore nell'inseriemnto dell'Anagrafica" + formAnagrafica ,  e);
			return "error";
		}
	}
	
	
	//prepara la jsp di modifica ricevendo un ID che verrà utilizzato per ottenere un oggetto Anagrafica
	@RequestMapping(value= "/preparaModifica", method = RequestMethod.POST)
	public String preparaModifica(FormAnagrafica formAnagrafica, Model model){
		
		try{
			//trasferisce la lista dei ruoli alla pagina modifica
			model.addAttribute("listaRuoli", ruoloService.getAll());
			//controlla che l'oggetto formAnagrafica non sia nullo o
			//che l'id ricevuto dalla pagina iniziale non sia 0
			if(formAnagrafica==null || formAnagrafica.getId()==0){
				logger.error("ERRORE-- Problema con 'formAnagrafica' o con l'id ");
				return "error";
			}
			//oggetto formAnagrafica inizializzato con un tipo di costruttore 
			//che passa come parametro un oggetto Anagrafica 
			//(in questo caso recuperato tramite ID(getElementById) preso dalla home)
			formAnagrafica = new FormAnagrafica(anagraficaService.GetElementById(formAnagrafica.getId()));
			model.addAttribute("formAnagrafica", formAnagrafica);
		}catch (Exception e){
			logger.error("Errore nel caricamento dei ruoli", e);
			return "error";
		}
		return "modifica";
	}
	
	//esegue modifica della riga selezionata facendo prima dei controlli sui 
	//dati inseriti
	@RequestMapping(value="/anagraficaModifica", method= RequestMethod.POST)
	public String anagraficaModifica(@Valid @ModelAttribute FormAnagrafica formAnagrafica, Model model, BindingResult result ){
		
		try{
			if(result.hasErrors()) {
				model.addAttribute("formAnagrafica", formAnagrafica);
				model.addAttribute("listaRuoli", ruoloService.getAll());
			    return "modifica";
			}
			
			Anagrafica anagrafica = formAnagrafica.getAnagraficaFromForm();
			if(!anagraficaService.controllaLogin(anagrafica)){
				//se non è già presente fa l'inserimento e ritorna un msg. di buon esito
				anagraficaService.modifica(anagrafica);
				model.addAttribute("esito", "MODIFICA RIUSCITA");
			}else{
				//altrimenti ritorna nella pagina 'inserimento' 
				model.addAttribute("esito", "Login già presente");
				model.addAttribute("listaRuoli", ruoloService.getAll());
				model.addAttribute("formAnagrafica", formAnagrafica);
				return "modifica";
				}
			model.addAttribute("listaAnagrafiche", anagraficaService.getAll());
			model.addAttribute("formAnagrafica", formAnagrafica);
			return "home";
			
			}catch (Exception e){
				logger.error("ERRORE nella modifica di Anagrafica" + formAnagrafica,  e);
				return "error";
			}
		
		
	}
	
	
	
	@RequestMapping(value="/cancellaAnagrafica", method= RequestMethod.POST)
	public String cancellaAnagrafica(Integer id, Model model){
		try{
			if(id != 0){
				anagraficaService.elimina(new Anagrafica(id));
				model.addAttribute("esito", "CANCELLAZIONE RIUSCITA");
			}else{
				logger.error("ID ricevuto non utilizzabile");
			}
			model.addAttribute("listaAnagrafiche", anagraficaService.getAll());
			model.addAttribute("formAnagrafica", new FormAnagrafica());
			return "home";
		}catch (Exception e){
			logger.error("Errore nella cancellazione del ruolo" + id, e);
			return "error";
		}
	}
	
	
	
	
	
	

}
