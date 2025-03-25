package com.wardrobehub.repository;

import com.wardrobehub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  public User findByEmail(String email);
}
