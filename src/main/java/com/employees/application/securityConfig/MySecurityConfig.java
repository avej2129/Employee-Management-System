package com.employees.application.securityConfig;

import com.employees.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.*;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class MySecurityConfig {

    @Autowired
    UserRepository userRepository;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public UserDetailsService userDetailsService() {

//<--------------hard Code Username and password------------------------------->
//        UserDetails admin = User.withUsername("Avej")
//                                .password(passwordEncoder.encode("12345")).build();
//        return new InMemoryUserDetailsManager(admin);

        return new UserdetailsService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.authorizeHttpRequests(auth -> auth
                .requestMatchers("/user/find", "/employee/**").authenticated()
                .requestMatchers("/api/create", "/EmployeePdf", "/logout",
                        "/api/login", "/login","/employee/home","/api/register","/data", "/employee/deletedEmployeeList","/api/username").permitAll());
        httpSecurity.formLogin(form -> form
                .loginPage("/api/login")
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/employee/home").permitAll());
        httpSecurity.logout(logout->
                logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/api/login").permitAll()
                );
        return httpSecurity.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
