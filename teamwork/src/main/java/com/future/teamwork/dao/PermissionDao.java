package com.future.teamwork.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.future.teamwork.domain.Permission;

@Repository
public interface PermissionDao extends JpaRepository<Permission, Integer>{
	
	@Query(value="SELECT p.*,(SELECT name FROM permission WHERE id = p.pid) as pname FROM permission as p ORDER BY pid",nativeQuery=true)
	List<Permission> getPermissionList();
	
	@Query(value="SELECT * FROM permission WHERE pid = 0",nativeQuery=true)
    List<Permission> parentPermissionList();
	
	@Query(value="UPDATE permission SET name=?,descpt=?,url=? WHERE id=?",nativeQuery=true)
    int updatePermission(Permission permission);
    
    @Query(value="SELECT * FROM permission  WHERE pid = ?",nativeQuery=true)
    List<Permission> getPermissionListByPid(Integer pid);
    
    @Query(value="SELECT * FROM permission WHERE id = ?",nativeQuery=true)
    Permission getById(String id);
    
    @Query(value="SELECT * FROM permission WHERE id = ?",nativeQuery=true)
    Permission getById(Integer id);
    
    @Query(value="DELETE permissions FROM permission WHERE id=?",nativeQuery=true)
    int deletePermissionById(Integer id);
}
