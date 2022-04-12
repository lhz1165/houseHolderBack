package com.example.householderback.config;


import com.example.householderback.commom.Result;
import com.example.householderback.entity.AdminUser;
import com.example.householderback.service.AdminUserService;
import com.example.householderback.utils.SpringUtils;
import com.example.householderback.utils.jwt.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/

public class AuthenticationInterceptor implements HandlerInterceptor {

    private AdminUserService userService = SpringUtils.getBean(AdminUserService.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token"); // 从 http 请求头中取出 token
        if (StringUtils.isEmpty(token)) {
            returnJson(response,"未登录");
            return false;
        }
        AdminUser user=null;
        try {
            user = userService.getById(JwtUtil.parseUserId(token));
        } catch (Exception e) {
            returnJson(response,"token错误");
            return false;
        }
        if (user == null) {
            returnJson(response,"token错误");
            return false;
        }

        String currentToken = (String) request.getSession().getAttribute(user.getUsername());
        if (currentToken == null) {
            returnJson(response,"未登录");
            return false;
        }

        if (!JwtUtil.validToken(token)) {
            returnJson(response,"token过期");
            return false;
        }
        return true;
    }

    private void returnJson(HttpServletResponse response,String msg){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(new ObjectMapper().writeValueAsString(Result.failed(Result.RetCode.UNAUTHORIZED,msg,null)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }
}
