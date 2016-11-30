package it.consoft.spring.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface GenericDao<E> {
	
	public E getElementById(Integer id) throws Exception;
	
	public List<E> getAll() throws Exception;
	
	public Integer aggiungi(E obj) throws Exception;
	
	public E modifica(E obj) throws Exception;
	
	public void cancella(E obj) throws Exception;
	

}
