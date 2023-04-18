package com.bracu.catfood.service;

import com.bracu.catfood.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

public class AuthService implements UserDetailsService {
    @Autowired
    UserService userService;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userService.getUserByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException(username);
        }
        User u = users.get(0);
        UserDetails user = org.springframework.security.core.userdetails.User.withUsername(u.getUsername())
                .password(u.getPassword())
                .authorities(new String[] {u.getType().toString(), u.getId().toString()})
                .build();

        return user;

    }


}