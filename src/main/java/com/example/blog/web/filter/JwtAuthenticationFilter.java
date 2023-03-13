package com.example.blog.web.filter;

import com.example.blog.auth.JwtService;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements Filter {

    private final JwtService jwtService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String jwt = Arrays.stream(httpRequest.getCookies())
                .filter(cookie -> cookie.getName().equals("jwt"))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);


        try {
            if (jwt != null && jwtService.isTokenValid(jwt)){
                chain.doFilter(request, response);
            }
            else {
                ((HttpServletResponse) response).sendRedirect("/admin");
            }
        }
        catch (Exception e){
            log.error(e.getMessage());
            ((HttpServletResponse) response).sendRedirect("/admin");
        }
    }
}
