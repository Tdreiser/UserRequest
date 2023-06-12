package ru.shlokov.UserRequest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.shlokov.UserRequest.model.User;
import ru.shlokov.UserRequest.model.enums.Role;
import ru.shlokov.UserRequest.service.UserService;

/**
 * @author Shlokov Andrey
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getUserList() {
        return new ResponseEntity<>(userService.showAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/changeState")
    public ResponseEntity<?> setUserRole(@RequestParam String username, @RequestParam Role role) {
        User user = userService.findByName(username);
        return (user != null)
                ? new ResponseEntity<>(userService.assignRoleToUser(role, user), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private boolean checkAuthority(Role role) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(role);
    }

    private User getUser() {
        return userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
