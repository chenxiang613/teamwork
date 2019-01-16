package com.future.teamwork.service.impl;

import com.future.teamwork.dao.PermissionDao;
import com.future.teamwork.dao.RoleDao;
import com.future.teamwork.domain.Permission;
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
    public PageDataUtil getRoleList(Integer pageNum,Integer pageSize) {
        PageDataUtil pageDataResult = new PageDataUtil();
        List<Role> roles = roleDao.getRoleList();

        List<Role> roleList = new ArrayList <>();
        for(Role r:roles){
            Role role =  new Role();

            String permissions = r.getPermissions();
            BeanUtils.copyProperties(r,role);
            role.setPermissions(permissions);

            if(!StringUtils.isEmpty(permissions)){
                String[] ids = permissions.split(",");
                List<String> p = new ArrayList <>();
                for(String id: ids){
                    Permission permission = this.permissionDao.getById(id);
                    String name = permission.getName();
                    p.add(name);
                }
                role.setPermissions(p.toString());
            }
            roleList.add(role);
        }

        PageHelper.startPage(pageNum, pageSize);

        if(roleList.size() != 0){
            PageInfo<Role> pageInfo = new PageInfo<>(roleList);
            pageDataResult.setList(roleList);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }
        return pageDataResult;
    }

    @Override
    public Map<String,Object> addRole(Role role) {
        Map<String,Object> data = new HashMap<String, Object>();
        try {
            role.setCreateTime(DateUtil.getCurrentDate());
            role.setUpdateTime(DateUtil.getCurrentDate());
            role.setStatus(1);
            int result;
            if( super.update(role,role.getId())!= null){
            	 result = 1;
            }else{
            	result = 0;
            }
            if(result == 0){
                data.put("code",0);
                data.put("msg","新增角色失败");
                return data;
            }
            data.put("code",1);
            data.put("msg","新增角色成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;

    }

    @Override
    public Role findRoleById(Integer id) {
        return roleDao.getById(id);
    }

    @Override
    public Map<String,Object> updateRole(Role role) {
        Map<String,Object> data = new HashMap<String, Object>();
        try{
//            role.setUpdateTime(DateUtil.getCurrentDate());
            int result;
            if( super.update(role,role.getId())!= null){
            	 result = 1;
            }else{
            	result = 0;
            }
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
    public Map<String, Object> delRole(Integer id,Integer status) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = roleDao.updateStatus(id,status);
            if(result == 0){
                data.put("code",0);
                data.put("msg","删除角色失败");
            }
            data.put("code",1);
            data.put("msg","删除角色成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Map <String, Object> recoverRole(Integer id, Integer status) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = roleDao.updateStatus(id,status);
            if(result == 0){
                data.put("code",0);
                data.put("msg","恢复角色失败");
            }
            data.put("code",1);
            data.put("msg","恢复角色成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Role> getRoles() {
        return roleDao.getRoleList();
    }
}
