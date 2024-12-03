package com.employees.application.securityConfig;

import com.employees.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityConfig {

    @Autowired
    UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserdetailsService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.authorizeHttpRequests(auth -> auth
                .requestMatchers("/user/find", "/employee/**","/api/username").authenticated()
                .requestMatchers("/api/create", "/EmployeePdf", "/logout",
                        "/api/login", "/login","/employee/home","/api/register","/data", "/employee/deletedEmployeeList").permitAll());
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
