package com.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//clase que comprueba si hay un token
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    //Para ver cual es el metodo  da el error 
    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        logger.error("fail en el método commence");
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "no autorizado");
    }
}
