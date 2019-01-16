package com.future.teamwork.service.impl;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import com.future.teamwork.service.BaseService;
import com.future.teamwork.utils.CopyUtils;

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
	@Nullable
	public T save(T entity) {
		return jpaRepository.save(entity);
	}
	
	@Override
	public T update(T source, ID id) {
		if( id == null){
			return null;
		}
		T target = jpaRepository.getOne(id);
		String[] nullPropertiesArray = CopyUtils.getNullPropertyNames(source);
		BeanUtils.copyProperties(source,target,nullPropertiesArray);
		return jpaRepository.save(target);
	}

	@Override
	public Page<T> findAll(Example<T> example, Pageable page) {
		return jpaRepository.findAll(example,page);
	}
	
	@Override
	public Page<T> findAll(Pageable page) {
		return jpaRepository.findAll(page);
	}

	public JpaRepository<T, ID> getJpaRepository() {
		return jpaRepository;
	}

	public void setJpaRepository(JpaRepository<T, ID> jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	
	
}
