package ru.shlokov.UserRequest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.shlokov.UserRequest.model.User;
import ru.shlokov.UserRequest.model.enums.Role;
import ru.shlokov.UserRequest.repository.UserRepository;

import java.util.List;

/**
 * @author Shlokov Andrey
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByName(String name) {
        return userRepository.findByEmail(name);
    }

    @Override
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean assignRoleToUser(Role role, User user) {
        return true;
    }
}
