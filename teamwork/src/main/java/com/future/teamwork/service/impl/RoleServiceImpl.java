package com.future.teamwork.service.impl;

import com.future.teamwork.dao.RoleDao;
import com.future.teamwork.domain.Role;
import com.future.teamwork.service.RoleService;
import com.future.teamwork.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role save(Role role) {
        role.setStatus(1);
        return super.save(role);
    }

    @Override
    public Role updateRole(Role role) {
    	Long id = role.getId();
        return super.update(role, id);
    }

    @Override
    public List<Role> getRoles() {
        return roleDao.getRoleList();
    }
}
