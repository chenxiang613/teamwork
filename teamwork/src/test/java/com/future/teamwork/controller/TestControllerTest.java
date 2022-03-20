package com.future.teamwork.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;


public class TestControllerTest extends TestCase {

    public static void main(String[] args) {
        JSONObject params = new JSONObject();
        params.put("List_status", "l");
        System.out.println("params = " + params);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("api_name", "stock_basic");
        paramMap.put("token", "19e08ca3ace6ddaa46ad035c0c914da124d1aa9332b0d244c96f2308");
        paramMap.put("params", params);
        paramMap.put("fields", "");
        System.out.println("paramMap = " + JSON.toJSONString(paramMap));

        String result = HttpUtil.post("http://api.tushare.pro", JSON.toJSONString(paramMap));
        System.out.println("result = " + result);
    }

}