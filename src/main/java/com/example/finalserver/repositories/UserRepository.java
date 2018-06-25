package com.example.finalserver.repositories;

import com.example.finalserver.models.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer>{
  @Query("select u from User u where u.email = :email")
  Optional<User> findUserByEmail(@Param("email") String email);

  @Query("select u from User u where u.email = :email and u.password = :password")
  Optional<User> findUserByEmailAndPassword(@Param("email") String email,
                                            @Param("password") String password);
}
