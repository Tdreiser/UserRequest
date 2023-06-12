package ru.shlokov.UserRequest.service;

import ru.shlokov.UserRequest.model.User;
import ru.shlokov.UserRequest.model.enums.Role;

import java.util.List;

/**
 * @author Shlokov Andrey
 */

public interface UserService {
    User findByName(String name);
    List<User> showAllUsers();

    boolean assignRoleToUser(Role role, User user);
}
