package it.consoft.spring.model.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.consoft.spring.model.beans.Anagrafica;
import it.consoft.spring.model.dao.AnagraficaDao;
import it.consoft.spring.model.dao.GenericDao;


public class AnagraficaServiceImpl implements AnagraficaService {
	
	private static final Logger logger= Logger.getLogger(AnagraficaServiceImpl.class);
	
	private AnagraficaDao anagraficaDao;
	
	//i Set DEVONO essere pubblici altrimenti 
	//non vengono visualizzati e da errore nel servlet-context
	public void setAnagraficaDao (AnagraficaDao anagraficaDao){
		this.anagraficaDao=anagraficaDao;
	}
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory= sessionFactory;
	}

	@Override
	@Transactional
	public Anagrafica GetElementById(Integer id) throws Exception {
            return anagraficaDao.getElementById(id);
	}

	@Override
	@Transactional
	public List<Anagrafica> getAll() throws Exception {
		return anagraficaDao.getAll();
	}

	@Override
	@Transactional
	public Integer aggiungi(Anagrafica obj) throws Exception {
		return anagraficaDao.aggiungi(obj);
	}

	@Override
	@Transactional
	public Anagrafica modifica(Anagrafica obj) throws Exception {
		return anagraficaDao.modifica(obj);
	}

	@Override
	@Transactional
	public void elimina(Anagrafica obj) throws Exception {
               anagraficaDao.cancella(obj);		
	}

	@Override
	@Transactional //esegue il controllo della login e resituisce TRUE se c'è già,  altrimenti FALSE
	public boolean controllaLogin(Anagrafica anagrafica) throws Exception {
		try{
			
			//crea un oggetto criteria dove inserire la query 
			Session session= this.sessionFactory.getCurrentSession();
			
			Criteria criteria= session.createCriteria(Anagrafica.class);
			criteria.add(Restrictions.eq("login",anagrafica.getLogin()));
			if (anagrafica.getId() != null){
				criteria.add(Restrictions.ne("id", anagrafica.getId()));
			}
			
			//conta le righe del risultato della select e lo inserisce 
			//dentro una lista tipizzata <Long>
			criteria.setProjection(Projections.rowCount());
			List<Long> result = criteria.list();
			
			//se il risultato non è nullo e non è vuoto utilizza una variabile di appoggio
			//dove mettere il contenuto finale della select
			if (result!= null && !result.isEmpty()){
				Long esito= (Long) result.get(0);
				//se esito è diverso da 0 significa che ci sono dei doppioni e quindi ritorna TRUE
				if(esito!=0){
					return true;
				}else{
					return false;
				}
			}
			}catch(Exception e){
				logger.error("ERRORE--- La Login di" + anagrafica + " è già presente");
				throw e;
			}
		return true;
		
	}	
		
}
