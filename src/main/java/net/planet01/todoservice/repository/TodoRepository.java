package net.planet01.todoservice.repository;

import net.planet01.todoservice.model.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {

    List<Todo> findAllByUserAndUsername(String username);
}
