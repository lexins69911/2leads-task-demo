package com.example.demo.service.filter;

import com.example.demo.error.BannedException;
import com.example.demo.service.component.LoginAttemptsComponent;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginAttemptsFilter extends OncePerRequestFilter {

    private LoginAttemptsComponent loginAttemptsComponent;

    private RequestMatcher requestMatcher = new AntPathRequestMatcher("/login**");

    public LoginAttemptsFilter(LoginAttemptsComponent loginAttemptsComponent) {
        this.loginAttemptsComponent = loginAttemptsComponent;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        if (!requestMatcher.matches(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String ip = request.getRemoteAddr();
        boolean extraAttempt = loginAttemptsComponent.isExtraAttempt(ip);
        if (extraAttempt) {
            loginAttemptsComponent.ban(ip);
            throw new BannedException();
        }

        filterChain.doFilter(request, response);
    }
}
