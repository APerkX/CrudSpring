package it.consoft.spring.model.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.consoft.spring.model.beans.Anagrafica;
import it.consoft.spring.model.beans.Ruolo;
import it.consoft.spring.model.dao.GenericDao;

@Service
public class RuoloServiceImp implements RuoloService {
	
	private static final Logger logger= Logger.getLogger(RuoloServiceImp.class);
	
	private GenericDao<Ruolo> ruoloDao;
	
	
	public void setRuoloDao(GenericDao<Ruolo> ruoloDao){
		this.ruoloDao= ruoloDao;
	}
	
	
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory){
		
		this.sessionFactory= sessionFactory;
	}


	@Override
	@Transactional
	public Ruolo GetElementById(Integer id) throws Exception {
		return ruoloDao.getElementById(id);
	}


	@Override
	@Transactional
	public List<Ruolo> getAll() throws Exception {
		return ruoloDao.getAll();
	}


	@Override
	@Transactional
	public Integer aggiungi(Ruolo obj) throws Exception {
		return ruoloDao.aggiungi(obj);
	}


	@Override
	@Transactional
	public Ruolo modifica(Ruolo obj) throws Exception {
		return ruoloDao.modifica(obj);
	}


	@Override
	@Transactional
	public void elimina(Ruolo obj) throws Exception {
		ruoloDao.cancella(obj);
		
	}


	@Override
	@Transactional
	public boolean controlloDesc(Ruolo ruolo) throws Exception {
		try{
			Session session= this.sessionFactory.getCurrentSession();
			Criteria criteria= session.createCriteria(Ruolo.class);
			criteria.add(Restrictions.eq("descrizione", ruolo.getDescrizione()));
			
			if(ruolo.getId() != null){
				criteria.add(Restrictions.ne("id", ruolo.getId()));
			}
			
			criteria.setProjection(Projections.rowCount());
			List<Long> result= criteria.list();
			if(result !=null && !result.isEmpty()){
				Long esito= (Long) result.get(0);
				if(esito != 0){
					return true;
				}else{
					return false;
				}
			}
		}catch (Exception e){
			logger.error("ERRORE--- La Descrizione di" + ruolo + " è già presente");
			throw e;
		}
		return true;
	}
	
	
	
	

}
