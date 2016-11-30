package it.consoft.spring.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.consoft.spring.model.beans.Anagrafica;

@Service
public interface AnagraficaService extends GenericService<Anagrafica> {
	

	public boolean controllaLogin(Anagrafica anagrafica)throws Exception;

}
