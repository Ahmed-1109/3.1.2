package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.model.UserDto;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAll();

    User getById(Long id);

    void saveUser(User user);

    void deleteUserById(Long id);

    void updateUser(UserDto userDto);

    User findByEmail(String email);


}
