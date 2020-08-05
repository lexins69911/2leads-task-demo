package com.example.demo.service.component;

import com.example.demo.db.entity.LoginAttemptEntity;
import com.example.demo.db.repository.LoginAttemptRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class LoginAttemptsComponentImpl implements LoginAttemptsComponent {

    private LoginAttemptRepository loginAttemptRepository;

    public static final Long DROP_ATTEMPTS_DURATION_MS = 5 * 60 * 1000L;

    public static final Integer MAX_ATTEMPTS_COUNT = 5;

    public static final Long BAN_TIME_MS = 30 * 60 * 1000L;

    public LoginAttemptsComponentImpl(LoginAttemptRepository loginAttemptRepository) {
        this.loginAttemptRepository = loginAttemptRepository;
    }

    @Override
    public Integer commitNewAttempt(String userIp) {
        Optional<LoginAttemptEntity> attemptOpt = loginAttemptRepository.findByUserIp(userIp);
        LoginAttemptEntity entity = attemptOpt.orElse(new LoginAttemptEntity(userIp));

        Integer currentAttemptCount = getCurrentAttemptCount(entity);
        entity.setCurrentAttemptsCount(currentAttemptCount);
        entity.setLastAttemptDate(new Date());
        loginAttemptRepository.save(entity);

        return currentAttemptCount;
    }

    @Override
    public boolean isExtraAttempt(String userIp) {
        Integer attemptsCount = commitNewAttempt(userIp);

        return attemptsCount > MAX_ATTEMPTS_COUNT;
    }

    @Override
    public void ban(String userIp) {
        Optional<LoginAttemptEntity> attemptOpt = loginAttemptRepository.findByUserIp(userIp);
        LoginAttemptEntity entity = attemptOpt.orElseThrow(() -> new IllegalArgumentException("No attempts found!"));

        if (!isBanned(entity)) {
            entity.setBanDate(new Date());
            loginAttemptRepository.save(entity);
        }
    }

    private boolean isBanned(LoginAttemptEntity entity) {
        Date banDate = entity.getBanDate();
        Long currentDateMs = new Date().getTime();

        return banDate != null && banDate.getTime() + BAN_TIME_MS > currentDateMs;
    }

    private boolean ITS_TIME_TO_STOP(LoginAttemptEntity entity) {
        Long currentDateMs = new Date().getTime();
        return entity.getLastAttemptDate().getTime() + DROP_ATTEMPTS_DURATION_MS < currentDateMs;
    }

    private Integer getCurrentAttemptCount(LoginAttemptEntity entity) {
        Date currentDate = new Date();

        if (isBanned(entity)) {
            return entity.getCurrentAttemptsCount();
        }

        if (ITS_TIME_TO_STOP(entity)) {
            return 1;
        }

        return entity.getCurrentAttemptsCount() + 1;
    }

}
