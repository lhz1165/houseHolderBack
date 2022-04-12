package com.example.householderback.utils;


import com.example.householderback.entity.User;
import com.example.householderback.service.AdminUserService;
import com.example.householderback.utils.jwt.JwtUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {
    private AdminUserService userService;
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //参数是否有@loginUser注解
        //参数是否是User
        return (methodParameter.hasParameterAnnotation(LoginUser.class) && methodParameter.getParameterType().equals(User.class));
    }

    /**
     * 解析
     * @param methodParameter
     * @param modelAndViewContainer
     * @param nativeWebRequest
     * @param webDataBinderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        if (userService == null) {
            userService = SpringUtils.getBean(AdminUserService.class);
        }
        String token = nativeWebRequest.getHeader("token");
        // 处理生态用户注入
        LoginUser memberAnnotation = methodParameter.getParameterAnnotation(LoginUser.class);
        if (memberAnnotation != null) {
            User user = userService.getById(JwtUtil.parseUserId(token));
            if (user != null) {
                return user;
            }

        }
        return null;
    }
}
