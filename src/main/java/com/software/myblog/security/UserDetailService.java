package com.software.myblog.security;

import com.software.myblog.entity.User;
import com.software.myblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // spring'e bu methodu kullandığın da elimizde ki user datasını set edicek
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found."));
        return new UserDetail(user.getUsername(), user.getPassword(), user.getRoles(), user.getUserStatus());
    }
}
