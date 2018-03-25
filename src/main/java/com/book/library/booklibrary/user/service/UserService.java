package com.book.library.booklibrary.user.service;

import com.book.library.booklibrary.user.model.DTO.UserDTO;
import com.book.library.booklibrary.user.model.entity.Role;
import com.book.library.booklibrary.user.model.entity.User;
import com.book.library.booklibrary.user.repository.RoleRepository;
import com.book.library.booklibrary.user.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = this.userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            Set<Role> roles = userOptional.get().getRoles();
            Set<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                    .collect(Collectors.toSet());

            return new org.springframework.security.core.userdetails.User(userOptional.get().getUsername(), userOptional.get().getPassword(), authorities);
        }
        throw new UsernameNotFoundException("No user found with that username");
    }

    @Override
    public Long registerUser(UserDTO userDTO) {
        User user = new User();
        Optional<Role> selectedRole = this.roleRepository.findByName(userDTO.getUserType().name());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        selectedRole.ifPresent(user::addRole);
        Long userId = this.userRepository.save(user).getId();
        return userId;
    }

    @Override
    public Optional<User> getUseById(Long userId) {
        return this.userRepository.findById(userId);
    }
}
