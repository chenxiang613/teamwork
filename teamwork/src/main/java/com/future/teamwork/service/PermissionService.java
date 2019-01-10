package com.future.teamwork.service;

import java.util.List;
import java.util.Map;

import com.future.teamwork.domain.Permission;
import com.future.teamwork.domain.User;
import com.future.teamwork.utils.PageDataUtil;

public interface PermissionService {

    Map<String,Object> addPermission(Permission permission);

    Map<String,Object> updatePermission(Permission permission);

    PageDataUtil getPermissionList(Integer pageNum, Integer pageSize);

    List<Permission> parentPermissionList();

    Map<String, Object> del(Integer id);

    Permission getById(Integer id);

    Map<String, Object> getUserPerms(User user);

}
