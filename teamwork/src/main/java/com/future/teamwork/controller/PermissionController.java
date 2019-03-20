package com.future.teamwork.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.future.teamwork.domain.Permission;
import com.future.teamwork.domain.ResultInfo;
import com.future.teamwork.domain.User;
import com.future.teamwork.service.PermissionService;
import com.future.teamwork.utils.CopyUtils;
import com.future.teamwork.utils.PageDataUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("permission")
public class PermissionController {


    @Autowired
    private PermissionService permissionService;
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("permissionManage")
    public String permissionManage() {
        return "/permission/permissionManage";
    }

    @PostMapping("permissionList")
    @ResponseBody
    public PageDataUtil permissionList(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize,Permission permission){
    	Example<Permission> example = Example.of(permission);
    	Pageable pageInfo = PageRequest.of(pageNum-1,pageSize);
    	Page<Permission> r = permissionService.findAll(example,pageInfo);
    	PageDataUtil result = CopyUtils.coyp(r);
    	return result;
    }
    
    @GetMapping("parentPermissionList")
    @ResponseBody
    public List<Permission> parentPermissionList(){
        return permissionService.parentPermissionList();
    }

    @PostMapping("setPermission")
    @ResponseBody
    public ResultInfo setPermission(Permission permission) {
        ResultInfo data = new ResultInfo();
        Permission result = null;
        if(permission.getId() == null){
        	result = permissionService.save(permission);
        }else{
        	result = permissionService.updatePermission(permission);
        }
        if(result!=null){
        	data.setMessage("Success");
        	data.setResult(1);
        }else{
        	data.setMessage("Failed");
        	data.setResult(0);
        }
        return data;
    }

    @PostMapping("del")
    @ResponseBody
    public ResultInfo del(@RequestParam("id") Long id) {
        ResultInfo data = new ResultInfo();
        Permission result =null;
        result = permissionService.deletePermission(id);
        if(result==null){
        	data.setMessage("Success");
        	data.setResult(1);
        	return data;
        }else{
        	data.setMessage("Failed");
        	data.setResult(0);
        	return data;
        }
    }

    @GetMapping("getUserPerms")
    @ResponseBody
    public Map<String, Object> getUserPerms(){
        Map<String, Object> data = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        data = permissionService.getUserPerms(user);
        System.out.println(data.toString());
        return data;
    }
    
    @GetMapping("getPermissions")
    @ResponseBody
    public List<Permission> getPermissions(){
    	List<Permission> data = new ArrayList<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        data = permissionService.getPermissons(user);
        System.out.println(data.toString());
        return data;
    }
    
    @PostMapping("getPermissionsTest")
    @ResponseBody
    public List<Permission> getPermissionsTest(){
    	User user = new User();
    	List<Permission> data = permissionService.getPermissons(user);
    	try ( Jedis jedis = jedisPool.getResource() ) {
    		jedis.set("10086", data.toString());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return data;
    }

}
