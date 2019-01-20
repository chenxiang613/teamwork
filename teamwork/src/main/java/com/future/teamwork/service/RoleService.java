package com.future.teamwork.service;

import java.util.List;
import com.future.teamwork.domain.Role;

public interface RoleService extends BaseService<Role, Integer> {

    List<Role> getRoles();
    
    
    Role updateRole(Role role);

}
