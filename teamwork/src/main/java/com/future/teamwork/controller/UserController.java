package com.future.teamwork.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.future.teamwork.utils.CopyUtils;
import com.future.teamwork.utils.PageDataUtil;
import com.future.teamwork.utils.redis.JedisClientSingle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.future.teamwork.annotation.Log;
import com.future.teamwork.dao.system.mapper.UserMapper;
import com.future.teamwork.domain.ResultInfo;
import com.future.teamwork.domain.User;
import com.future.teamwork.service.RoleService;
import com.future.teamwork.service.UserService;
import com.github.pagehelper.PageHelper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JedisClientSingle jedisClientSingle;

    @PostMapping("/login")
    @ResponseBody
    @Log(operationType="operationType",operationName="login")
    public Map<String,Object> login(HttpServletRequest request,HttpServletResponse response, User user, HttpSession session,String captcha){
        Map<String,Object> data = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();

        String userName = user.getUserName().trim();
        String password = user.getPassword().trim();
        String rememberMe = user.getRememberMe();

        String sessionCaptcha = (String) SecurityUtils.getSubject().getSession().getAttribute(CaptchaController.KEY_CAPTCHA);
        
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        
//        if (null == captcha || !captcha.equalsIgnoreCase(sessionCaptcha)) {
//        	data.put("code", 0);
//            data.put("message","验证码错误！");
//            return data;
//        }

        if (rememberMe != null && rememberMe.equals("on")) {
            token.setRememberMe(true);
        }

        try {
            subject.login(token);
            user = (User) subject.getPrincipal();
            String session_id = session.getId();
            session.setAttribute("user_session_id", session_id);
            Cookie cookie = new Cookie("JSESSIONID", session_id);//session_id默认是存放在一个name为JSESSIOINID里面的
            cookie.setMaxAge(30 * 60);// 30 分钟
            response.addCookie(cookie);
            System.err.println("登陆成功，session id 为：" + session_id);
            
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
    	
    	PageHelper.startPage(pageNum, pageSize);
    	Example<User> example = Example.of(user);
    	Pageable pageInfo = PageRequest.of(pageNum-1,pageSize, Sort.Direction.DESC,"userName");
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
    
    @RequestMapping(value = "/jedis", method = RequestMethod.POST)
    @ResponseBody
    public boolean testDeleteUser(User user) {
    	
    	try ( Jedis jedis = jedisPool.getResource() ) {
    		jedis.set(user.getClass().getName()+ user.getUserName(), user.getPhone());
    		System.err.println(jedis.get(user.getUserName()));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

		return true;
    }
    
    @RequestMapping(value = "/add/user", method = RequestMethod.GET)
    @ResponseBody
    public User addUserBy() {
    	User u1 = new User("chendongfang", "10086", "10086");
    	User u2 = new User("chendongfang", "1000.", "10000");
    	List<User> userList = new ArrayList<User>();
    	userList.add(u1);
    	userList.add(u2);
//    	System.err.println(userMapper.insertBatch(userList));
    	
    	try ( Jedis jedis = jedisPool.getResource() ) {
    		for (User u : userList) {
    			jedis.sadd("User" + u.getUserName(), JSON.toJSONString(u));
			}
    		Set<String> setUser = jedis.sdiff("Userchendongfang");
    		for (String string : setUser) {
    			User u = (User)JSON.parseObject(string, User.class);
    			System.out.println(u);
			}
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    	return u1;
    }
    

}
