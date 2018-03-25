package com.book.library.booklibrary.user.service;

import com.book.library.booklibrary.user.model.DTO.UserDTO;
import com.book.library.booklibrary.user.model.entity.User;

import java.util.Optional;

public interface UserServiceInterface {

    Long registerUser(UserDTO userDTO);

    Optional<User> getUseById(Long userId);


}
