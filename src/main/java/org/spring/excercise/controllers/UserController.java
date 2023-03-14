package org.spring.excercise.controllers;

import org.spring.excercise.models.User;
import org.spring.excercise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> findUsers() {
        return userRepository.findAllUsers();
    }

    @PostMapping
    public void storeUser(@RequestBody User user) {
        userRepository.storeUsers(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userRepository.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userRepository.deleteUser(id);
    }

}
