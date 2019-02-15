package com.future.teamwork.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.future.teamwork.domain.Person;

@Repository
public interface PersonDao extends JpaRepository<Person, Integer> {
	
    
}