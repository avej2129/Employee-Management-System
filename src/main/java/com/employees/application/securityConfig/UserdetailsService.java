package com.employees.application.securityConfig;

import com.employees.application.model.User;
import com.employees.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserdetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<User> user =  userRepository.findByUsername(username);
        return user.map(UserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("User Not Found"+username));

    }
}
