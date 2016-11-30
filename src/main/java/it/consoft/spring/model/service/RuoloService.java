package it.consoft.spring.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.consoft.spring.model.beans.Ruolo;

@Service
public interface RuoloService extends GenericService<Ruolo> {
	
	
	@Transactional
	public boolean controlloDesc(Ruolo ruolo) throws Exception;

	

}

