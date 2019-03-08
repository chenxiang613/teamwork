package com.future.teamwork.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.future.teamwork.domain.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Long>{
	
	@Query(value="SELECT * FROM role",nativeQuery=true)
	List<Role> getRoleList();

	Role findRoleById(Integer id);
}
