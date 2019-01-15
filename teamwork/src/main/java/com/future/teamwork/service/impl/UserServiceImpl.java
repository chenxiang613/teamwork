package com.future.teamwork.service.impl;

import com.future.teamwork.dao.UserDao;
import com.future.teamwork.domain.User;
import com.future.teamwork.service.UserService;
import com.future.teamwork.utils.DateUtil;
import com.future.teamwork.utils.DigestUtil;
import com.future.teamwork.utils.PageDataUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public PageDataUtil getUserList(User user, Integer pageNum, Integer pageSize) {
        PageDataUtil pageDataResult = new PageDataUtil();
        List<User> userList = userDao.getUserList();

        PageHelper.startPage(pageNum, pageSize);

        if(userList.size() != 0){
            PageInfo<User> pageInfo = new PageInfo<>(userList);
            pageDataResult.setList(userList);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }

        return pageDataResult;
    }


    @Override
    public Map<String,Object> addUser(User user) {
        Map<String,Object> data = new HashMap<String, Object>();
        try {
            User old = userDao.getUserByUserName(user.getUserName(),null);
            if(old != null){
                data.put("code",0);
                data.put("msg","用户名已存在！");
                return data;
            }
            String phone = user.getPhone();
            if(phone.length() != 11){
                data.put("code",0);
                data.put("msg","手机号位数不对！");
                return data;
            }
            String username = user.getUserName();
            if(user.getPassword() == null){
                String password = DigestUtil.Md5(username,"123456");
                user.setPassword(password);
            }else{
                String password = DigestUtil.Md5(username,user.getPassword());
                user.setPassword(password);
            }
            user.setCreateTime(DateUtil.getCurrentDate());
            user.setStatus(1);
            int result;
            if( userDao.save(user) != null){
            	 result = 1;
            }else{
            	result = 0;
            }
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
    public Map <String, Object> updateUser(User user) {
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
        int result;
        if( userDao.save(user) != null){
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
        return data;
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getById(id);
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
