package ru.shlokov.UserRequest.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shlokov.UserRequest.model.Request;
import ru.shlokov.UserRequest.model.User;

import java.util.List;

/**
 * @author Shlokov Andrey
 */
@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    Request findByAuthor(User user);
    List<Request> findAllByAuthor(String user);
}
