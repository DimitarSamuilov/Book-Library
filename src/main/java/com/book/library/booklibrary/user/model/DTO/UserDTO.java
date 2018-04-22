package com.book.library.booklibrary.user.model.DTO;

import com.book.library.booklibrary.user.enums.UserType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

    private static final String EMPTY_FIELD_EMAIL = "Email cannot be empty";
    private static final String EMPTY_FIELD_PASSWORD = "Password cannot be empty";
    private static final String EMPTY_FIELD_USERNAME = "Username cannot be empty";
    private static final String EMPTY_PROFILE_TYPE = "Please select profile type";
    private static final String PASSWORD_LENGTH="Password must be at least 3 characters";

    private Long id;

    @NotEmpty(message = EMPTY_FIELD_USERNAME)
    private String username;

    @NotEmpty(message = EMPTY_FIELD_PASSWORD)
    @Size(min = 3, message = PASSWORD_LENGTH)
    private String password;

    @NotEmpty(message = EMPTY_FIELD_EMAIL)
    private String email;

    @NotNull(message = EMPTY_PROFILE_TYPE)
    private UserType userType;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
