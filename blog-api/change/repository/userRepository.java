package com.shimh.repository;

import com.shimh.entity.User;
import com.shimh.repository.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>, UserWrapper {
    User findByUsername(String username);
}