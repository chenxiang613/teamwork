package com.future.teamwork.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.future.teamwork.dao.UserDao;
import com.future.teamwork.domain.ResultInfo;
import com.future.teamwork.domain.User;
import com.future.teamwork.service.UserService;
import com.future.teamwork.utils.DateUtil;
import com.future.teamwork.utils.DigestUtil;
import com.future.teamwork.utils.PageDataUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User save(User user) {
        User old = userDao.getUserByUserName(user.getUserName(),null);
        if(old != null){
            return null;
        }
        String password = DigestUtil.Md5(user.getUserName(),user.getPassword());
        user.setPassword(password);
        user.setCreateTime(DateUtil.getCurrentDate());
        user.setStatus(1);
        return super.save(user);
    }


    @Override
    public Map<String,Object> updateUser(User user) {
    	Map<String,Object> data = new HashMap<String, Object>();
        Integer id = user.getId();
        User old = userDao.getUserByUserName(user.getUserName(),id);
        if(old != null){
        	data.put("code",0);
        	data.put("msg","用户名已存在！");
        	return data;
        }
        String username = user.getUserName();
        if(user.getPassword() != null){
            String password = DigestUtil.Md5(username,user.getPassword());
            user.setPassword(password);
        }
        if( super.update(user, user.getId()) != null){
        	data.put("code",1);
            data.put("msg","更新成功！");
            return data;
        }else{
        	data.put("code",0);
            data.put("msg","更新失败！");
            return data;
        }
    }


    @Override
    public Map <String, Object> delUser(Integer id,Integer status) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = userDao.updateUserStatus(id,status);
            if(result == 0){
                data.put("code",0);
                data.put("msg","删除用户失败");
                return data;
            }
            data.put("code",1);
            data.put("msg","删除用户成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Map <String, Object> recoverUser(Integer id, Integer status) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = userDao.updateUserStatus(id,status);
            if(result == 0){
                data.put("code",0);
            }
            data.put("code",1);
            data.put("msg", "恢复用户成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }


    @Override
    public int updatePassword(String userName, String password) {
        password = DigestUtil.Md5(userName,password);
        return userDao.updatePassword(userName,password);
    }
}
