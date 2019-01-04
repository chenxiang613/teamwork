package com.future.teamwork.service;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseService<T>  extends JpaRepository<T, Serializable>{
	
	
}
