package com.bracu.catfood.service;

import com.bracu.catfood.entity.User;
import com.bracu.catfood.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUserByUsername(String name) {
        return userRepository.findByUsername(name);
    }
    public User addUser(User user) {
        return userRepository.save(user);
    }
    public AuthUser getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)auth.getPrincipal()).getUsername();
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) auth.getAuthorities();

        Integer userType = 0;
        Integer userId = 0;
        int pass = 0;
        for (SimpleGrantedAuthority permission : authorities) {
            if(pass == 0) userType = Integer.parseInt(permission.getAuthority());
            if(pass == 1) userId = Integer.parseInt(permission.getAuthority());
            pass++;
        }

        AuthUser u = new AuthUser();
        u.setUsername(username);
        u.setUserType(userType);
        u.setUserId(userId);

        return u;
    }
}