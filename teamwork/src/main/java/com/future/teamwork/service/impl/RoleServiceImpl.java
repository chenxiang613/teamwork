package com.future.teamwork.service.impl;

import com.future.teamwork.dao.PermissionDao;
import com.future.teamwork.dao.RoleDao;
import com.future.teamwork.domain.Permission;
import com.future.teamwork.domain.ResultInfo;
import com.future.teamwork.domain.Role;
import com.future.teamwork.domain.User;
import com.future.teamwork.service.RoleService;
import com.future.teamwork.utils.DateUtil;
import com.future.teamwork.utils.PageDataUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Role save(Role role) {
    	role.setCreateTime(DateUtil.getCurrentDate());
        role.setUpdateTime(DateUtil.getCurrentDate());
        role.setStatus(1);
        return super.save(role);
    }

    @Override
    public Role updateRole(Role role) {
//            role.setUpdateTime(DateUtil.getCurrentDate());
    	Integer id = role.getId();
        return super.update(role, id);
    }

    @Override
    public List<Role> getRoles() {
        return roleDao.getRoleList();
    }
}
