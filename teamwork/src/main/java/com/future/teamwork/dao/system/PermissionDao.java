package com.future.teamwork.dao.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.future.teamwork.domain.Permission;

@Repository
public interface PermissionDao extends JpaRepository<Permission, Long>{

    Permission getPermissionById(Long id);
    List<Permission> getPermissionListByPid(String pid);
    Permission deletePermissionById(Long id);
    
    @Query(value=" select p.* "
    		+ "FROM USER u, user_role ur,role_permission rp,permission p "
    		+ " WHERE u.id = ? "
    		+ " AND u.id = ur.user_id AND ur.role_id = rp.role_id AND rp.permission_id = p.id group by p.id ",nativeQuery=true)
    List<Permission> getPermissionByUserId(Long id);
}
