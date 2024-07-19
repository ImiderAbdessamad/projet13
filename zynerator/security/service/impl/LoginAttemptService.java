package ma.zs.zyn.zynerator.security.service.impl;

import ma.zs.zyn.zynerator.security.bean.User;
import ma.zs.zyn.zynerator.security.dao.facade.core.UserDao;
import ma.zs.zyn.zynerator.security.service.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class LoginAttemptService {
    private static final int MAX_ATTEMPTS = 3;
    private static final long LOCKOUT_DURATION = 120;  // 30 minutes

    @Autowired
    private UserDao userService;

    public void loginFailed(String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            int attempts = user.getLoginAttempts() + 1;
            user.setLoginAttempts(attempts);
            if (attempts >= MAX_ATTEMPTS) {
                user.setLockoutTime(LocalDateTime.now());
            }
            userService.save(user);
        }
    }

    public void loginSucceeded(String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            user.setLoginAttempts(0);
            user.setLockoutTime(null);
            userService.save(user);
        }
    }

    public boolean isLocked(String username) {
        User user = userService.findByUsername(username);
        return user != null && user.getLockoutTime() != null &&
                user.getLockoutTime().plusSeconds(LOCKOUT_DURATION).isAfter(LocalDateTime.now());
    }
    public Long getRemainingLockoutTime(String username) {
        User user = userService.findByUsername(username);
        if (user != null && user.getLockoutTime() != null) {
            LocalDateTime lockoutEndTime = user.getLockoutTime().plusSeconds(LOCKOUT_DURATION);
            LocalDateTime now = LocalDateTime.now();
            if (lockoutEndTime.isAfter(now)) {
                return java.time.Duration.between(now, lockoutEndTime).getSeconds();
            }
        }
        return 0L;
    }
}
