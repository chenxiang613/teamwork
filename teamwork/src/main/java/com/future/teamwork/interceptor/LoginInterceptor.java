package com.future.teamwork.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * 进入controller层之前拦截请求
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
    	System.err.println("---------------------开始进入请求地址拦截----------------------------");
    	HttpSession session = request.getSession();
    	if( session != null){
    		String sessionId = (String) session.getAttribute("user_session_id");
    		if( sessionId != null ){
    			System.err.println("session id " + sessionId);
    		}else{
    			System.err.println("session id 为空");
    		}
//			response.sendRedirect("/login");
			return true;
    	}
        return true;
    }

    //处理请求完成后视图渲染之前的处理操作
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//    	System.err.println("--------------处理请求完成后视图渲染之前的处理操作---------------");
    }

    //视图渲染之后的操作
    
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//    	System.err.println("---------------视图渲染之后的操作-------------------------0");
    }

}