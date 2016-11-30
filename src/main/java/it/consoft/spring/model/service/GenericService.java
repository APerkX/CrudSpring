package it.consoft.spring.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
public interface GenericService<E> {
	
	@Transactional
	public E GetElementById(Integer id)throws Exception;
	
	@Transactional
	public List<E> getAll()throws Exception;
	
	@Transactional
	public Integer aggiungi(E obj)throws Exception;
	
	@Transactional
	public E modifica(E obj) throws Exception;
	
	@Transactional
	public void elimina(E obj) throws Exception;
	

}
