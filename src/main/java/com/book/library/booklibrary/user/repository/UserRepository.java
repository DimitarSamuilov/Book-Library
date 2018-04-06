package com.book.library.booklibrary.user.repository;

import com.book.library.booklibrary.user.model.entity.Role;
import com.book.library.booklibrary.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
//    SELECT u.id,u.email,u.password,u.username FROM User u JOIN u.roles  r WHERE r.name= :role
    //SELECT count(*) FROM User u JOIN u.roles  r WHERE r.name= :role
    @Query(value = "SELECT u.id,u.email,u.password,u.username FROM users u \n" +
            "JOIN users_roles c on u.id = c.users_id join roles r on r.id= c.roles_id \n WHERE r.name= :role",
            countQuery = "SELECT count(*) FROM users u \n" +
                    "JOIN users_roles c on u.id = c.users_id join roles r on r.id= c.roles_id \n WHERE r.name= :role",
            nativeQuery = true)
    Page<User> findUsersByRole(@Param("role") String role,Pageable pageable);
}
