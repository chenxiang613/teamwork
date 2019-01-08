package com.future.teamwork.service;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseService<T,ID extends Serializable> {
	
	public void delete(T entity);
	public void deleteById(ID id);
	public boolean existsById(ID id);
	public T save(T entity);
}
