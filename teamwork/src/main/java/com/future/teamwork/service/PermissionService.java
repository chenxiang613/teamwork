package com.future.teamwork.service;

import java.util.List;
import java.util.Map;

import com.future.teamwork.domain.Permission;
import com.future.teamwork.domain.User;

public interface PermissionService extends BaseService<Permission, Long>{

    List<Permission> parentPermissionList();

    Map<String, Object> getUserPerms(User user);
    
    Permission save(Permission permission);
    
    Permission updatePermission(Permission permission);
    
    Permission deletePermission(Long id);
}
