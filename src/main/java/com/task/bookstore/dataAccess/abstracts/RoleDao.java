package com.task.bookstore.dataAccess.abstracts;

import com.task.bookstore.entity.concretes.roles.ERole;
import com.task.bookstore.entity.concretes.roles.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
