package com.wmb2.controller.interceptor;

import com.wmb2.exception.UnauthorizedException;
import com.wmb2.utils.JwtUtil;
import com.wmb2.utils.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyHeaderInterceptor implements HandlerInterceptor {
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().contains(UrlMapping.REGISTER) ||
                request.getRequestURI().contains(UrlMapping.LOGIN)) {
            return true;
        }
        String token = request.getHeader("Authorization");
        System.out.println("TOKEN " + token);
        if (token == null) throw new UnauthorizedException();
        String[] bearerToken = token.split(" ");
        return jwtUtil.validateJwtToken(bearerToken[1]);
//        return true;
    }
}
