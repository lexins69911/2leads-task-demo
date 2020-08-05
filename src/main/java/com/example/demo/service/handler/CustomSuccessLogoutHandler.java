package com.example.demo.service.handler;

import com.example.demo.db.entity.HistoryEntity;
import com.example.demo.db.entity.UserEntity;
import com.example.demo.db.repository.HistoryRepository;
import com.example.demo.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class CustomSuccessLogoutHandler implements LogoutSuccessHandler {

    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void onLogoutSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        String ip = request.getRemoteAddr();

        User principal = (User) authentication.getPrincipal();

        UserEntity byEmail = userRepository.findByEmail(principal.getUsername());

        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setUserIp(ip);
        historyEntity.setUser(byEmail);
        historyEntity.setActionDate(new Date());
        historyEntity.setLogin(false);

        historyRepository.save(historyEntity);

        response.setHeader("Location", "/");
        response.setStatus(302);

    }
}
