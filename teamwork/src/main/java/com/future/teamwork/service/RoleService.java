package com.future.teamwork.service;

import java.util.List;
import com.future.teamwork.domain.Role;

public interface RoleService extends BaseService<Role, Integer> {

    List<Role> getRoles();
    
    Role save(Role role);
    
    Role updateRole(Role role);

}
