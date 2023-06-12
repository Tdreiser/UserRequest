package ru.shlokov.UserRequest.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.shlokov.UserRequest.model.Client;

import java.util.List;

/**
 * @author Shlokov Andrey
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "emailAddresses")
    List<Client> findByFullNameContaining(String name);
}
