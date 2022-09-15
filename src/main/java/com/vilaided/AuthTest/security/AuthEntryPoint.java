package com.vilaided.AuthTest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vilaided.AuthTest.payload.response.ResponseCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPoint.class);
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        logger.error("Unauthorized error: {}", );
        authException.printStackTrace();
        response.setContentType("application/json");
        ResponseCustom res = new ResponseCustom("FAILED","Token is not valid", HttpStatus.UNAUTHORIZED);
        String json = new ObjectMapper().writeValueAsString(res);
        response.getWriter().write(json);
        response.setStatus(401);
    }
}
