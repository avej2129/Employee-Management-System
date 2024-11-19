package com.employees.application.serviceImpl;

import com.employees.application.model.User;
import com.employees.application.repository.UserRepository;
import com.employees.application.securityConfig.MySecurityConfig;
import com.employees.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MySecurityConfig config;

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        User exsiting = new User();
        exsiting.setName(user.getName());
        exsiting.setUsername(user.getUsername());
        exsiting.setPassword(config.passwordEncoder().encode(user.getPassword()));
        exsiting.setRoles(user.getRoles());
        exsiting.setConfirmPassword(config.passwordEncoder().encode(user.getConfirmPassword()));
        return userRepository.save(exsiting);
    }
}
