package com.forge.security;

import com.forge.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ForgeUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ForgeUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        return userRepository.findByEmail(email)
                .map(ForgeUserDetails::new)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));
    }
}