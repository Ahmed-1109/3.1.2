package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.model.UserDto;
import ru.kata.spring.boot_security.demo.reposirory.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        final String pass = passwordCode(user.getPassword());
        user.setPassword(pass);
        userRepository.saveUser(user);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteUserById(id);
    }

    @Transactional
    @Override
    public void updateUser(UserDto userDto) {

        final User updatedUser = userRepository.getById(userDto.getId());

        if (!(userRepository.getById(userDto.getId()).getPassword().equals(userDto.getPassword()))) {
            userDto.setPassword(passwordCode(userDto.getPassword()));
        }

        updatedUser.setAge(userDto.getAge());
        updatedUser.setName(userDto.getName());
        updatedUser.setEmail(userDto.getEmail());
        updatedUser.setRoles(roleService.getRolesByIds(userDto.getRoles()));
        userRepository.updateUser(updatedUser);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
    }

    public String passwordCode(String pass) {
        return passwordEncoder.encode(pass);
    }
}
