package com.example.restapiprojectsem4.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApiAuthorizationFilter extends OncePerRequestFilter {
    static Logger LOGGER = Logger.getLogger(ApiAuthorizationFilter.class.getSimpleName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LOGGER.log(Level.WARNING,"doing filter");
        LOGGER.log(Level.WARNING,request.getServletPath());

        if (request.getServletPath().equals("/api/v1/accounts/login")
                || request.getServletPath().equals("/api/v1/accounts/register")
//                || request.getServletPath().equals("/api/v1/accounts/list")
//                || request.getServletPath().equals("/api/v1/accounts/detail/{id}")
                || request.getServletPath().equals("/api/v1/orders/cart")
                || request.getServletPath().equals("/api/v1/orders/addToCart")
//                || request.getServletPath().equals("/api/v1/orders/updateCart")
//                || request.getServletPath().equals("/api/v1/orders/remove")
//                || request.getServletPath().equals("/api/v1/orders/proceedOrder")
//                || request.getServletPath().equals("/api/v1/orders/controlValue")
//                || request.getServletPath().equals("/api/v1/orders/list")
//                || request.getServletPath().equals("/api/v1/orders/{id}")
//                || request.getServletPath().equals("/api/v1/orders/status/update")
//                || request.getServletPath().equals("/api/v1/orders/checkOut/update")
//                || request.getServletPath().equals("/api/v1/orders/delete/{id}")
                || request.getServletPath().equals("/api/v1/products/list")
//                || request.getServletPath().equals("/api/v1/products/create")
//                || request.getServletPath().equals("/api/v1/products/search/{name}")
//                || request.getServletPath().equals("/api/v1/products/detail/{id}")
//                || request.getServletPath().equals("/api/v1/products/update/{id}")
//                || request.getServletPath().equals("/api/v1/products/delete/{id}")
                || request.getServletPath().equals("/api/v1/categories/list")
//                || request.getServletPath().equals("/api/v1/categories/search/{name}")
//                || request.getServletPath().equals("/api/v1/categories/detail/{id}")
//                || request.getServletPath().equals("/api/v1/categories/create")
//                || request.getServletPath().equals("/api/v1/categories/update/{id}")
//                || request.getServletPath().equals("/api/v1/categories/delete/{id}")

        ){
            filterChain.doFilter(request,response);
            return;
        }
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        authorizationHeader = authorizationHeader.replace("Bearer", "").trim();
        JWTVerifier jwtVerifier = JWT.require(SecurityBean.algorithm()).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(authorizationHeader);
        String username = decodedJWT.getSubject();
        String role = decodedJWT.getClaim("role").asString();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(username,null,authorities);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        LOGGER.log(Level.WARNING,role);
        LOGGER.log(Level.WARNING,username);
        filterChain.doFilter(request,response);
    }
}
