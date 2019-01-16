package com.future.teamwork.service;

import java.io.Serializable;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T,ID extends Serializable> {
	
	public void delete(T entity);
	public void deleteById(ID id);
	public boolean existsById(ID id);
	public T save(T entity);
	public T update(T entity,ID id); 
	public Page<T> findAll(Example<T> example,Pageable pageInfo); 
	public Page<T> findAll(Pageable pageInfo); 
}
