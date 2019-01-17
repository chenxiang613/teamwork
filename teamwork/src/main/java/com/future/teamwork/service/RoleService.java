package com.future.teamwork.service;

import java.util.List;
import java.util.Map;

import com.future.teamwork.domain.Role;
import com.future.teamwork.utils.PageDataUtil;

public interface RoleService extends BaseService<Role, Integer> {

    List<Role> getRoles();
    
    Role save(Role role);
    
    Role updateRole(Role role);

}
