package com.future.teamwork.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.future.teamwork.domain.Permission;
import com.future.teamwork.domain.User;
import com.future.teamwork.service.PermissionService;
import com.future.teamwork.utils.PageDataUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("permission")
public class PermissionController {


    @Autowired
    private PermissionService permissionService;

    @RequestMapping("permissionManage")
    public String permissionManage() {
        return "/permission/permissionManage";
    }

    @PostMapping("permissionList")
    @ResponseBody
    public PageDataUtil permissionList(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize){
        PageDataUtil pdr = new PageDataUtil();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            pdr = permissionService.getPermissionList(pageNum ,pageSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdr;
    }

    @GetMapping("parentPermissionList")
    @ResponseBody
    public List<Permission> parentPermissionList(){
        return permissionService.parentPermissionList();
    }

    @PostMapping("setPermission")
    @ResponseBody
    public Map<String,Object> setPermission(Permission permission) {
        Map<String,Object> data = new HashMap<String, Object>();
        if(permission.getId() == null){
            data = permissionService.addPermission(permission);
        }else{
            data = permissionService.updatePermission(permission);
        }
        return data;
    }

    @PostMapping("del")
    @ResponseBody
    public Map<String, Object> del(@RequestParam("id") Integer id) {
        Map<String, Object> data = new HashMap<>();
        data = permissionService.del(id);
        return data;
    }

    @GetMapping("getUserPerms")
    @ResponseBody
    public Map<String, Object> getUserPerms(){
        Map<String, Object> data = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        data = permissionService.getUserPerms(user);
        return data;
    }

}
