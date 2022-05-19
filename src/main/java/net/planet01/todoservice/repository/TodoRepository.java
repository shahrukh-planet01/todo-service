package net.planet01.todoservice.repository;

import net.planet01.todoservice.model.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,Long> {

    List<Todo> findAllByUserUsername(String username);
    Optional<Todo> findByUserUsernameAndId(String username,Long id);
}
