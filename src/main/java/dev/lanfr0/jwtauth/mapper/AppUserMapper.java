package dev.lanfr0.jwtauth.mapper;

import dev.lanfr0.jwtauth.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AppUserMapper {
    Optional<User> findByEmail(@Param("email") String email);
    List<User> getAllUsers();
    void saveUser(User user);
}
