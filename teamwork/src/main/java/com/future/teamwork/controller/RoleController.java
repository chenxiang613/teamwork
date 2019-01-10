package com.future.teamwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.future.teamwork.utils.PageDataUtil;
import com.future.teamwork.domain.Role;
import com.future.teamwork.service.RoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService adminRoleService;

    @RequestMapping("/roleManage")
    public String toPage() {
        return "/role/roleManage";
    }

    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
    @ResponseBody
    public PageDataUtil getRoleList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize) {
        PageDataUtil pdr = new PageDataUtil();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            pdr = adminRoleService.getRoleList(pageNum ,pageSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdr;
    }

    @GetMapping("getRoles")
    @ResponseBody
    public List<Role> getRoles(){
        return adminRoleService.getRoles();
    }

    @PostMapping("setRole")
    @ResponseBody
    public Map<String,Object> setRole(Role role) {
        Map<String,Object> data = new HashMap<String, Object>();
        if(role.getId() == null){
            data = adminRoleService.addRole(role);
        }else{
            data = adminRoleService.updateRole(role);
        }
        return data;
    }

    @PostMapping("updateRoleStatus")
    @ResponseBody
    public Map<String,Object> updateRoleStatus(@RequestParam("id") int id,@RequestParam("status") Integer status) {
        Map<String, Object> data = new HashMap<>();
        if(status == 0){
            data = adminRoleService.delRole(id,status);
        }else{
            data = adminRoleService.recoverRole(id,status);
        }
        return data;
    }

}
