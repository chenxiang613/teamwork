package com.future.teamwork.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.future.teamwork.domain.User;
import com.future.teamwork.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private Log log = LogFactory.getLog(UserController.class) ;
	
    @Autowired
    private UserService userService;
    
    @RequestMapping(value="/addUser")
    public @ResponseBody Object addUser(User user) {
    	user.setAge(10);
    	user.setUsername("Cranberries");
    	System.err.println("addUser");
        return userService.save(user);
    }

    @DeleteMapping(path = "/deleteUser")
    public void deleteUser(Integer id) {
        userService.deleteById(id);
    }
}
