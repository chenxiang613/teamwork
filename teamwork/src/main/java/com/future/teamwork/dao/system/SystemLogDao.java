package com.future.teamwork.dao.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.future.teamwork.domain.SystemLog;

@Repository
public interface SystemLogDao extends JpaRepository<SystemLog, Long>{
	

}
