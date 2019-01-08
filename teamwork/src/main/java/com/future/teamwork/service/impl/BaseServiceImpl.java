package com.future.teamwork.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.future.teamwork.service.BaseService;

public class BaseServiceImpl<T,ID extends Serializable> implements BaseService<T,ID>{

	@Autowired
	private JpaRepository<T, ID> jpaRepository;
	
	@Override
	public void delete(T entity) {
		jpaRepository.delete(entity);
		
	}

	@Override
	public void deleteById(ID id) {
		jpaRepository.deleteById(id);		
	}

	@Override
	public boolean existsById(ID id) {
		return jpaRepository.existsById(id);
	}

	@Override
	public T save(T entity) {
		return jpaRepository.save(entity);
	}




}
