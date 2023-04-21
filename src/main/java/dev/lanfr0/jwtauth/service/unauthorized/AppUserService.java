package dev.lanfr0.jwtauth.service.unauthorized;

import dev.lanfr0.jwtauth.model.User;
import java.util.List;

public interface AppUserService {

    User findByEmail(String email);
    List<User> getAllUsers();
    void saveUser(User user);
}
