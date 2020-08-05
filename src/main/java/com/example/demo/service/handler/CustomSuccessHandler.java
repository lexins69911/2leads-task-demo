package com.example.demo.service.handler;

import com.example.demo.db.entity.HistoryEntity;
import com.example.demo.db.entity.UserEntity;
import com.example.demo.db.repository.HistoryRepository;
import com.example.demo.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private UserRepository userRepository;
//
//    public CustomSuccessHandler(HistoryRepository historyRepository, UserRepository userRepository) {
//        this.historyRepository = historyRepository;
//        this.userRepository = userRepository;
//    }

    @Override
    @Transactional
    public void onAuthenticationSuccess(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Authentication authentication)
            throws IOException, ServletException
    {
        String remoteAddr = httpServletRequest.getRemoteAddr();

        User principal = (User) authentication.getPrincipal();

        UserEntity byEmail = userRepository.findByEmail(principal.getUsername());

        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setUserIp(remoteAddr);
        historyEntity.setUser(byEmail);
        historyEntity.setActionDate(new Date());
        historyEntity.setLogin(true);

        historyRepository.save(historyEntity);

        httpServletResponse.setHeader("Location", "/");
        httpServletResponse.setStatus(302);
    }
}
