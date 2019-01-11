package com.future.teamwork.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.future.teamwork.domain.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer>{
	
	@Query(value="SELECT * FROM role",nativeQuery=true)
	List<Role> getRoleList();
	
	@Query(value="SELECT * FROM role WHERE status = 1",nativeQuery=true)
    List<Role> getRoles();

	@Query(value="UPDATE role SET role_id=?,role_name=?,role_desc=?,update_time=? WHERE id=?",nativeQuery=true)
    int updateRole(Role role);
	
	@Query(value="UPDATE role SET status WHERE id=?",nativeQuery=true)
    int updateStatus(Integer id,Integer status);
	
	@Query(value="SELECT * FROM role WHERE id=?",nativeQuery=true)
	Role getById(Integer id);
	
	@Query(value="SELECT * FROM role WHERE id=?",nativeQuery=true)
	Role getByRoleId(Integer id);
}
