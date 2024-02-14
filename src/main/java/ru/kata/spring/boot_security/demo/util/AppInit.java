/*
//package ru.kata.spring.boot_security.demo.util;
package ru.kata.spring.boot_security.demo.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppInit implements CommandLineRunner {
    private UserService us;
    private RoleService rs;

    public AppInit(UserService us, RoleService rs) {
        this.us = us;
        this.rs = rs;
    }

    @Override
    public void run(String... args) throws Exception {
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");

        rs.save(userRole);
        rs.save(adminRole);

        List<Role> roles = new ArrayList<>();
        List<Role> rolesAdm = new ArrayList<>();

        roles.add(userRole);
        rolesAdm.add(adminRole);

        User user = new User("ahmed",24,"ahmed","adasd",);
        User admin = new User("kirill", 20, "admin", rolesAdm);

        us.save(user);
        us.save(admin);
    }
}

*/
