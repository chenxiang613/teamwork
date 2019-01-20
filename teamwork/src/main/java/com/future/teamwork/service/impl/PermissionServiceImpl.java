package com.future.teamwork.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.future.teamwork.dao.PermissionDao;
import com.future.teamwork.dao.RoleDao;
import com.future.teamwork.domain.Permission;
import com.future.teamwork.domain.Role;
import com.future.teamwork.domain.User;
import com.future.teamwork.service.PermissionService;
import com.future.teamwork.utils.DateUtil;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission , Integer> implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public Permission save(Permission permission) {
        permission.setCreateTime(DateUtil.getCurrentDate());
        permission.setUpdateTime(DateUtil.getCurrentDate());
        permission.setDelFlag(1);
        return super.save(permission);
    }

    @Override
    public Permission updatePermission(Permission permission) {
        permission.setUpdateTime(DateUtil.getCurrentDate());
        return super.update(permission, permission.getId());
    }

    @Override
    public Permission deletePermission(Integer id) {
    	super.deleteById(id);
    	return null;
    }

    @Override
    public Map <String, Object> getUserPerms(User user) {
        Map<String, Object> data = new HashMap<>();
        Set<Role> roleId = user.getRoleSet();
        for (Role role : roleId) {
        	if (null != role ) {
                String permissions = role.getPermissions();
                String[] ids = permissions.split(",");
                List<Permission> permissionList = new ArrayList <>();
                for (String id : ids) {
                    Permission perm = permissionDao.getPermissionById(Integer.valueOf(id));
                    if (null != perm ) {
                        Permission permission = new Permission();
                        BeanUtils.copyProperties(perm,permission);
                        List<Permission> childrens = permissionDao.getPermissionListByPid(perm.getId().toString());
                        permission.setChildrens(childrens);
                        permissionList.add(permission);
                    }
                }
                data.put("perm",permissionList);
            }
		}
        return data;
    }

	@Override
	public List<Permission> parentPermissionList() {
		Permission permission = new Permission();
		return permissionDao.getPermissionListByPid(permission.getPid());
	}
}
