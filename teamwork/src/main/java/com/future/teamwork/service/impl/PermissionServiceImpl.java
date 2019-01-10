package com.future.teamwork.service.impl;

import com.future.teamwork.dao.PermissionDao;
import com.future.teamwork.dao.RoleDao;
import com.future.teamwork.domain.Permission;
import com.future.teamwork.domain.Role;
import com.future.teamwork.domain.User;
import com.future.teamwork.service.PermissionService;
import com.future.teamwork.utils.DateUtil;
import com.future.teamwork.utils.PageDataUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public Map<String, Object> addPermission(Permission permission) {
        Map<String,Object> data = new HashMap<String, Object>();
        try {
            permission.setCreateTime(DateUtil.getCurrentDate());
            permission.setUpdateTime(DateUtil.getCurrentDate());
            permission.setDelFlag(1);
            int result = permissionDao.updatePermission(permission);
            if(result == 0){
                data.put("code",0);
                data.put("msg","新增失败！");
                return data;
            }
            data.put("code",1);
            data.put("msg","新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
        return data;
    }

    @Override
    public Map <String, Object> updatePermission(Permission permission) {
        Map<String,Object> data = new HashMap<String, Object>();
        try{
            permission.setUpdateTime(DateUtil.getCurrentDate());
            int result = permissionDao.updatePermission(permission);
            if(result == 0){
                data.put("code",0);
                data.put("msg","更新失败！");
                return data;
            }
            data.put("code",1);
            data.put("msg","更新成功！");
        }catch (Exception e) {
            e.printStackTrace();
            return data;
        }
        return data;
    }


    @Override
    public PageDataUtil getPermissionList(Integer pageNum, Integer pageSize) {
        PageDataUtil pageDataResult = new PageDataUtil();
        List<Permission> permissions = permissionDao.getPermissionList();

        PageHelper.startPage(pageNum, pageSize);

        if(permissions.size() != 0){
            PageInfo<Permission> pageInfo = new PageInfo<>(permissions);
            pageDataResult.setList(permissions);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }
        return pageDataResult;
    }

    @Override
    public List<Permission> parentPermissionList() {
        return permissionDao.parentPermissionList();
    }

    @Override
    public Map <String, Object> del(Integer id) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = permissionDao.deletePermissionById(id);
            if(result == 0){
                data.put("code",0);
                data.put("msg","删除失败");
                return data;
            }
            data.put("code",1);
            data.put("msg","删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


    @Override
    public Permission getById(Integer id) {
        return permissionDao.getById(id);
    }


    @Override
    public Map <String, Object> getUserPerms(User user) {
        Map<String, Object> data = new HashMap<>();
        Integer roleId = user.getRoleId();

        Role role = roleDao.getByRoleId(roleId);
        if (null != role ) {
            String permissions = role.getPermissions();

            String[] ids = permissions.split(",");
            List<Permission> permissionList = new ArrayList <>();
            for (String id : ids) {
                Permission perm = permissionDao.getById(id);
                if (null != perm ) {
                    Permission permission = new Permission();
                    BeanUtils.copyProperties(perm,permission);
                    List<Permission> childrens = permissionDao.getPermissionListByPid(perm.getId());
                    permission.setChildrens(childrens);
                    permissionList.add(permission);
                }
            }
            data.put("perm",permissionList);
        }

        return data;
    }
}
