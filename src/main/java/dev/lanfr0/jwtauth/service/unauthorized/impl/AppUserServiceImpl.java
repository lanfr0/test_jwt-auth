package dev.lanfr0.jwtauth.service.unauthorized.impl;

import dev.lanfr0.jwtauth.mapper.AppUserMapper;
import dev.lanfr0.jwtauth.model.User;
import dev.lanfr0.jwtauth.service.unauthorized.AppUserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserMapper appUserMapper;

    public AppUserServiceImpl(AppUserMapper appUserMapper) {
        this.appUserMapper = appUserMapper;
    }

    @Override
    public User findByEmail(String email) {
        return appUserMapper.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return appUserMapper.getAllUsers();
    }

    @Override
    public void saveUser(User user) {
        appUserMapper.saveUser(user);
    }
}
