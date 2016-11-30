package it.consoft.spring.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import it.consoft.spring.model.beans.Anagrafica;

@Repository
public class AnagraficaDao implements GenericDao<Anagrafica> {
	
	private static final Logger logger= Logger.getLogger(AnagraficaDao.class);
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory= sessionFactory;
	}

	 
	@Override
	public Anagrafica getElementById(Integer id) throws Exception {
              Anagrafica element= null;
              try{
            	  Session session= this.sessionFactory.getCurrentSession();
            	  element= (Anagrafica) session.get(Anagrafica.class, id);
              }catch (Exception e){
            	  logger.error("Non trovata Anagrafica con id="+id, e);
            	  throw e;
              }
              
		return element;
	}

	 
	@Override
	public List<Anagrafica> getAll() throws Exception {
		List<Anagrafica> listaA= new ArrayList<Anagrafica>();
		try{
			Session sess;
			try {         
			       sess = sessionFactory.getCurrentSession();  
			   } catch (org.hibernate.HibernateException he) {  
			       sess = sessionFactory.openSession();     
			   }             
			 
			
			Criteria criteria= sess.createCriteria(Anagrafica.class);
			criteria.addOrder(Order.asc("id"));
			listaA= criteria.list();
		}catch (Exception e){
			logger.error("Nessuna riga trovata nella Tabella Anagrafica", e);
			throw e;
		}
		return listaA;
	}

	 
	@Override
	public Integer aggiungi(Anagrafica obj) throws Exception {
            Integer id= null;
            try{
            	Session session= this.sessionFactory.getCurrentSession();
            	id= (Integer)session.save(obj);
            	
            }catch(Exception e){
            	logger.error("Inserimento Anagrafica:" + obj +"NON RIUSCITO", e);
            	throw e;
            }
		
		
		return id;
	}

	 
	@Override
	public Anagrafica modifica(Anagrafica obj) throws Exception {
	    Anagrafica element= null;
	    try{
	    	Session session = this.sessionFactory.getCurrentSession();
	    	element= (Anagrafica) session.merge(obj);
	    }catch(Exception e){
	    	logger.error("Modifica Anagrafica:" + obj +"NON RIUSCITO", e);
	    	throw e;
	    }
		return element;
	}

	 
	@Override
	public void cancella(Anagrafica obj) throws Exception {
           try{
        	   Session session= this.sessionFactory.getCurrentSession();
        	   session.delete(obj);
           }catch(Exception e){
        	   logger.error("Cancellazione dell'Anagrafica:"+ obj + "NON RIUSCITO", e);
        	   throw e;
           }
           
	}
	
	

}
