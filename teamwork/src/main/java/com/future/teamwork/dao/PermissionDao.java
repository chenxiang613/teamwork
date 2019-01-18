package com.future.teamwork.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.future.teamwork.domain.Permission;

@Repository
public interface PermissionDao extends JpaRepository<Permission, Integer>{

    Permission getPermissionById(Integer id);
    List<Permission> getPermissionListByPid(String pid);
    Permission deletePermissionById(Integer id);
}
