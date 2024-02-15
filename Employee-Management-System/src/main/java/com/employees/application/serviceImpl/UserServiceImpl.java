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

//    @Autowired
//    MySecurityConfig securityConfig;

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User SaveUser(User user) {
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(MySecurityConfig.passwordEncoder().encode(user.getPassword()));
        user1.setRoles(user.getRoles());
        return userRepository.save(user1);
    }
}
