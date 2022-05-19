package net.planet01.todoservice.service;

import net.planet01.todoservice.model.dto.TodoDto;
import net.planet01.todoservice.model.entity.Todo;
import net.planet01.todoservice.model.request.TaskCompleteRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TodoService {

    List<TodoDto> getTodos(String username);
    TodoDto getTodo(Long id);
    TodoDto addTodo(TodoDto todo);
    TodoDto taskCompleted(Long id);
    TodoDto editTodo(TodoDto todo, Long id);
    void deleteTodo(Long id);
}
