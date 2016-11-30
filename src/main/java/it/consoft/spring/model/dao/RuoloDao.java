package it.consoft.spring.model.dao;

import it.consoft.spring.model.beans.Anagrafica;
import it.consoft.spring.model.beans.Ruolo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;

@Repository
public class RuoloDao implements GenericDao<Ruolo> {

	private static final Logger logger = Logger.getLogger(RuoloDao.class);
	
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory= sessionFactory;
	}


	 
	@Override
	public Ruolo getElementById(Integer id) throws Exception {
         Ruolo element;
         try{
        	 Session session= this.sessionFactory.getCurrentSession();
        	 element=(Ruolo) session.get(Ruolo.class, id);
         }catch(Exception e){
        	 logger.error("Non è stato trovato nessun Ruolo con id:"+ id, e);
        	 throw e;
         }
		
		return element;
	}


	 
	@Override
	public List<Ruolo> getAll() throws Exception {
        List<Ruolo> listaRuoli= new ArrayList<Ruolo>();
        try{
        	Session session= this.sessionFactory.getCurrentSession();
        	Criteria criteria = session.createCriteria(Ruolo.class);
        	criteria.addOrder(Order.asc("id"));
        	listaRuoli= criteria.list();
           
        }catch (Exception e){
        	logger.error("Nessuna riga trovata nella tabella Ruolo");
        	throw e;
        }
		
		return listaRuoli;
	}


	 
	@Override
	public Integer aggiungi(Ruolo obj) throws Exception {
           Integer id= null;
           try{
        	   Session session= this.sessionFactory.getCurrentSession();
        	   id= (Integer)session.save(obj);
        	   
           }catch(Exception e){
        	   logger.error("Inserimento Ruolo:" + obj + "NON RIUSCITO", e);
        	   throw e;
           }
           
		
		return id;
	}


	 
	@Override
	public Ruolo modifica(Ruolo obj) throws Exception {
        Ruolo element = null;
        try{
         	Session session= this.sessionFactory.getCurrentSession();
        	element= (Ruolo) session.merge(obj);
        }catch(Exception e){
        	logger.error("Modifica Ruolo:" + obj + "NON RIUSCITO", e);
        	throw e;
        }
		
		return element;
	}


	 
	@Override
	public void cancella(Ruolo obj) throws Exception {

          try{
        	  Session session= this.sessionFactory.getCurrentSession();
              session.delete(obj);
            }catch(Exception e){
            	logger.error("Canacellazione Ruolo:" + obj +"NON RIUSCITA");
            	throw e;
            }
	
	}
		
}
