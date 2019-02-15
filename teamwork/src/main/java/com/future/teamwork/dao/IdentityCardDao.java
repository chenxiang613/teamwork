package com.future.teamwork.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.future.teamwork.domain.IdentityCard;
import com.future.teamwork.domain.Person;

@Repository
public interface IdentityCardDao extends JpaRepository<IdentityCard, Integer> {
	
    
}
