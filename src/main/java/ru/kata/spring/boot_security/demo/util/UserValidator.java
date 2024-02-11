package ru.kata.spring.boot_security.demo.util;

/*
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Component
public class UserValidator implements Validator {
private final UserServiceImpl userService;


    public UserValidator(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;
        try {
            userService.loadUserByUsername(user.getEmail());
        } catch (UsernameNotFoundException ignored){
            return;// не забыть создать свой сервис
        }
        errors.rejectValue("username", "", "Человек с таким именем уже существует");

    }
}
*/