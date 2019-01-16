package com.future.teamwork.service;

import java.util.List;
import java.util.Map;

import com.future.teamwork.domain.Role;
import com.future.teamwork.utils.PageDataUtil;

public interface RoleService extends BaseService<Role, Integer> {

    PageDataUtil getRoleList(Integer pageNum, Integer pageSize);

    List<Role> getRoles();

    Role findRoleById(Integer id);

    Map<String,Object> updateRole(Role role);

    Map<String,Object> delRole(Integer id,Integer status);

    Map<String,Object> recoverRole(Integer id,Integer status);

    Map<String,Object> addRole(Role role);

}
