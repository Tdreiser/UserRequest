package ru.shlokov.UserRequest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shlokov.UserRequest.model.User;

/**
 * @author Shlokov Andrey
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
   User findByEmail(String email);
}
