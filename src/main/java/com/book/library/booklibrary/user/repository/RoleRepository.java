package com.book.library.booklibrary.user.repository;

import com.book.library.booklibrary.user.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository  extends JpaRepository<Role,Long>{

    Optional<Role> findByName(String name);
}
