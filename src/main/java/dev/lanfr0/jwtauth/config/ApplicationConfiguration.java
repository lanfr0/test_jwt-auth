package dev.lanfr0.jwtauth.config;

import dev.lanfr0.jwtauth.service.unauthorized.AppUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfiguration {

    private final AppUserService appUserService;

    public ApplicationConfiguration(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    /*
     * We want to implement our custom loadByUsername function to load the username from the database
     * There are already four implementation of this because it is a Spring Security class
     * */
    @Bean
    public UserDetailsService userDetailsService() {

        /* this can be replaced by a lambda
         **/
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return appUserService.findByEmail(username);
            }
        };


//        return username -> appUserService.findByEmail(username);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /*
     * Is responsible for the authentication
     * Has a bunch of methods and one of them helps to authenticate the user by username and password
     * AuthenticationConfiguration -> already hold information about AuthenticationManager
     * */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfig) throws Exception {
        return authenticationConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
