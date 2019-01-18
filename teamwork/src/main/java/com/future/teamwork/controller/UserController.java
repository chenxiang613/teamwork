package com.future.teamwork.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.future.teamwork.domain.ResultInfo;
import com.future.teamwork.domain.User;
import com.future.teamwork.service.UserService;
import com.future.teamwork.utils.CopyUtils;
import com.future.teamwork.utils.PageDataUtil;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, User user, HttpSession session,String captcha){
        Map<String,Object> data = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();

        String userName = user.getUserName().trim();
        String password = user.getPassword().trim();
        String rememberMe = user.getRememberMe();
//        String host = request.getRemoteAddr();

        String sessionCaptcha = (String) SecurityUtils.getSubject().getSession().getAttribute(CaptchaController.KEY_CAPTCHA);
        
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        
        if (null == captcha || !captcha.equalsIgnoreCase(sessionCaptcha)) {
        	data.put("code", 0);
            data.put("message","验证码错误！");
            return data;
        }

        if (rememberMe != null && rememberMe.equals("on")) {
            token.setRememberMe(true);
        }

        try {
            subject.login(token);
            user = (User) subject.getPrincipal();

            session.setAttribute("user", user.getUserName());
            data.put("code",1);
            data.put("url","/home");
        } catch (UnknownAccountException e) {
            data.put("code",0);
            data.put("message",userName+"账号不存在");
            return data;
        }catch (DisabledAccountException e){
            data.put("code",0);
            data.put("message",userName+"账号异常");
            return data;
        }
        catch (AuthenticationException e){
            data.put("code",0);
            data.put("message",userName+"密码错误");
            return data;
        }

        return data;
    }

    @RequestMapping("setPwd")
    @ResponseBody
    public Map<String,Object> setP(String pwd, String isPwd){
        Map<String,Object> data = new HashMap<String, Object>();
        if(!pwd.equals(isPwd)){
            data.put("code",0);
            data.put("message","两次输入的密码不一致!");
            return data;
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        int result = userService.updatePassword(user.getUserName(),pwd);
        if(result == 0){
            data.put("code",0);
            data.put("msg","修改密码失败！");
            return data;
        }
        data.put("code",1);
        data.put("msg","修改密码成功！");
        return data;
    }

    @RequestMapping("/userManage")
    public String userManage() {
        return "/user/userManage";
    }

    
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataUtil getUserList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize, User user) {
    	Example<User> example = Example.of(user);
    	Pageable pageInfo = PageRequest.of(pageNum-1,pageSize);
    	Page<User> r = userService.findAll(example,pageInfo);
    	PageDataUtil result = CopyUtils.coyp(r);
    	return result;
    }

    @RequestMapping(value = "/setUser", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo setUser(User user) {
    	ResultInfo data = new ResultInfo();
    	User result = null;
        if(user.getId() == null){
            result = userService.save(user);
        }else{
        	result = userService.updateUser(user);
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

    @RequestMapping(value = "/updateUserStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateUserStatus(User user) {
    	ResultInfo data = new ResultInfo();
    	if( userService.updateUser(user) != null ){
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
