package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;

    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        List<User> user = userService.findAll();
        model.addAttribute("users", user);
        return "admin";
    }


    @PostMapping(value = "/admin/add")
    public String createUser(@ModelAttribute("user") User user, @RequestParam ArrayList<String> listRoleId) {
        Set<Role> userRole = new HashSet<>();
        for (String roleId : listRoleId) {
            Role role = roleService.getRoleById(Long.parseLong(roleId));
            userRole.add(role);
        }
        user.setRoles(userRole);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/add")
    public String newUser(@ModelAttribute("user") User user) {
        return "add";
    }

    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }


    @GetMapping("/admin/edit/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        Set<Role> roleList = roleService.findAll();
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roleList", roleList);
        return "edit";
    }

    @PostMapping("/admin/edit")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

}
