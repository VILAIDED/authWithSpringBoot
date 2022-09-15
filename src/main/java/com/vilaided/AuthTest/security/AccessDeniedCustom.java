package com.vilaided.AuthTest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vilaided.AuthTest.payload.response.ResponseCustom;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AccessDeniedCustom implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json");
        ResponseCustom res = new ResponseCustom("FAILED","Access denied", HttpStatus.FORBIDDEN);
        String json = new ObjectMapper().writeValueAsString(res);
        response.getWriter().write(json);
        response.setStatus(403);
    }
}
