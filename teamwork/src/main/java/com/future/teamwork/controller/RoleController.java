package com.future.teamwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.future.teamwork.utils.CopyUtils;
import com.future.teamwork.utils.PageDataUtil;
import com.future.teamwork.domain.ResultInfo;
import com.future.teamwork.domain.Role;
import com.future.teamwork.service.RoleService;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/roleManage")
    public String toPage() {
        return "/role/roleManage";
    }

    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
    @ResponseBody
    public PageDataUtil getRoleList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize,Role role) {
    	Example<Role> example = Example.of(role);
    	Pageable pageInfo = PageRequest.of(pageNum-1,pageSize);
    	Page<Role> r = roleService.findAll(example,pageInfo);
    	PageDataUtil result = CopyUtils.coyp(r);
    	return result;
    }

    @GetMapping("getRoles")
    @ResponseBody
    public List<Role> getRoles(){
        return roleService.getRoles();
    }

    @PostMapping("setRole")
    @ResponseBody
    public ResultInfo setRole(Role role) {
        ResultInfo data = new ResultInfo();
        Role result = null;
        if(role.getId() == null){
        	result = roleService.save(role);
        }else{
        	result = roleService.updateRole(role);
        }
        if( result != null){
        	data.setMessage("Success");
    		data.setResult(1);
        }else{
        	data.setMessage("Failed");
        	data.setResult(0);
        }
        return data;
    }

    @PostMapping("updateRoleStatus")
    @ResponseBody
    public ResultInfo updateRoleStatus(Role role) {
        ResultInfo data = new ResultInfo();
        if( roleService.updateRole(role) != null ){
    		data.setMessage("Success");
    		data.setResult(1);
    		return data;
        }else{
        	data.setMessage("Failed");
        	data.setResult(0);
        	return data;
        }
    }

}
