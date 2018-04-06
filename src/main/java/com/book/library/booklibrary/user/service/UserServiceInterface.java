package com.book.library.booklibrary.user.service;

import com.book.library.booklibrary.user.model.DTO.UserDTO;
import com.book.library.booklibrary.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserServiceInterface {

    Long registerUser(UserDTO userDTO);

    Optional<User> getUseById(Long userId);

    Page<UserDTO> getUsersByRole(String role, Pageable pageable);
}
