package com.future.teamwork.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * http请求工具类
 * Created by zhanghb on 2018/3/27.
 */
public class HttpRequestUtil {

	public static void main(String[] args) throws IOException {
//		String url = PropertyUtil.getProperty("serviceAddressHeader") + 
//				PropertyUtil.getProperty("applicationName") +
//				PropertyUtil.getProperty("createGroup");
		Map<String, String> param = new HashMap<>();
//		param.put("name", "李四1");
//		param.put("pageNum", "0");
//		param.put("pageSize", "10");
//		param.put("storeId", "1068754455468146690");
//		param.put("limit", "10");
		param.put("id", "4");
		System.out.println(post("http://localhost:8081/user/deleteRole", param));
//		System.out.println(AlgoUtil.post("http://192.168.3.176:8080/ssgw/app/selectIndexPageMemberAnalysis", param));
//		JSONObject jo = JSONObject.parseObject(AlgoUtil.post("http://192.168.2.176:8080/ssgw/sys/selectVIPCount", param));
//		System.out.println(jo.toString());
	}
    
    
    public static String post(String url, Map<String, String> params) {
		OkHttpClient client = new OkHttpClient();
		
		// create FormBody
		okhttp3.FormBody.Builder builder = new FormBody.Builder();
		for (Entry<String, String> entry : params.entrySet()) {
			builder.add(entry.getKey(), entry.getValue());
		}
		

		// create Request
		Request request = new Request.Builder().url(url).post(builder.build()).build();
		// call
		try {
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			throw new RuntimeException("云平台调用失败:" + e.getStackTrace());
		}
	}
}