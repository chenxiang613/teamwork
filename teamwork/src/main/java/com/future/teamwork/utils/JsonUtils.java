package com.future.teamwork.utils;

import com.alibaba.fastjson.JSON;
import com.future.teamwork.domain.User;

public class JsonUtils {

	public static void cusernvertListObject() {
        User user = new User();
        user.setId(10086L);
        user.setUserName("张三");
        //注意如果是list多个对象比需要使用JSONArray
        String jsonOutput= JSON.toJSONString(user);
        User u = (User)JSON.parseObject(jsonOutput, User.class);
        System.out.println("JsonString:"+jsonOutput);
        System.out.println(u.toString());
    }
	
	public static void main(String[] args) {
		JsonUtils.cusernvertListObject();
	}
}
