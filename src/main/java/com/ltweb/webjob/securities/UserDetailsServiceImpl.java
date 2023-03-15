package com.ltweb.webjob.securities;

import com.ltweb.webjob.entities.User;
import com.ltweb.webjob.exceptions.NotFoundException;
import com.ltweb.webjob.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Component
public class UserDetailsServiceImpl implements UserDetailsService  {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws NotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email: " + email + " not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), Set.of(new SimpleGrantedAuthority(user.getRole().name())));
    }

}
