package com.future.teamwork.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.future.teamwork.domain.SystemLog;

@Repository
public interface SystemLogDao extends JpaRepository<SystemLog, Integer>{
	

}
