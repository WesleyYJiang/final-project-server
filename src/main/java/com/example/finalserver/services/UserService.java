package com.example.finalserver.services;

import com.example.finalserver.models.User;
import com.example.finalserver.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

@RestController
public class UserService {
  @Autowired
  UserRepository repository;

  @GetMapping("/api/user") // /api/user?username=alice
  public List<User> findAllUser(@RequestParam(value = "username", required = false) String username) {
    if(username == null) {
      return (List<User>) repository.findAll();
    } else {
      return Arrays.asList(findUserByEmail(username));
    }
  }

  @PostMapping("/api/user")
  public User createUser(@RequestBody User user) {
    return repository.save(user);
  }

  @DeleteMapping("/api/user/{userId}")
  public void deleteUser(@PathVariable("userId") int id) {
    repository.deleteById(id);
  }

  @PutMapping("/api/user/{userId}")
  public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser)throws Exception  {
    Optional<User> data = repository.findById(userId);
    if (data.isPresent()) {
      User user = data.get();
      if (newUser.getEmail() != null){ user.setEmail(newUser.getEmail());}
      if (newUser.getBio() != null){ user.setBio(newUser.getBio());}
      if (newUser.getProfession() != null){user.setProfession(newUser.getProfession());}
      if (newUser.getEducation() != null){user.setEducation(newUser.getEducation());}
      if (newUser.getFirstName() != null){user.setFirstName(newUser.getFirstName());}
      if (newUser.getLastName() != null){user.setLastName(newUser.getLastName());}
      if (newUser.getRole() != null){user.setRole(newUser.getRole());}
      repository.save(user);
      return user;
    }
    else{
      throw new Exception("bad");
    }
  }

  private User findUserByEmail(String email) {
    Optional<User> data = repository.findUserByEmail(email);
    if (data.isPresent()) {
      return data.get();
    }
    return null;
  }


  @PostMapping("/api/register")
  public User register(@RequestBody User user, HttpSession session) throws Exception {
    if(this.findUserByEmail(user.getEmail()) == null){
      session.setAttribute("currentUser", user);
      return repository.save(user);
    }
    else{
      throw new Exception("bad");
    }
  }

  @PostMapping("/api/login")
  public User login(@RequestBody User user, HttpSession session) throws Exception{
    System.out.println(user);
    Optional<User> data = repository.findUserByEmailAndPassword(
            user.getEmail(), user.getPassword());
    if (data.isPresent()) {
      session.setAttribute("currentUser", user);
      return data.get();
    }
    else{
      throw new Exception("bad");
    }
  }

  @PostMapping("/api/verify")
  public User verify(@RequestBody User user) throws Exception{
    System.out.println(user);
    Optional<User> data = repository.findUserByEmailAndPassword(
            user.getEmail(), user.getPassword());
    if (data.isPresent()) {
      return data.get();
    }
    else{
      return user;
    }
  }


  @PostMapping("/api/logout")
  public void logout (HttpSession session) {
    session.invalidate();
  }

  @GetMapping("/api/profile")
  public User profile(HttpSession session) throws Exception {
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser != null) {
      return currentUser;

    } else {
      throw new Exception("session Timed out");
    }
  }
}
