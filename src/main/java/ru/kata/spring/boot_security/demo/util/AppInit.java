//package ru.kata.spring.boot_security.demo.util;
package ru.kata.spring.boot_security.demo.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.LinkedHashSet;
import java.util.Set;

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

        Set<Role> roles = new LinkedHashSet<>();
        Set<Role> rolesAdm = new LinkedHashSet<>();

        roles.add(userRole);
        rolesAdm.add(adminRole);

        User user = new User("ivan", "20", "ivan", "12345", roles);
        User admin = new User("kirill", "24", "kirill", "admin", rolesAdm);

        us.saveUser(user);
        us.saveUser(admin);
    }
}

