package net.planet01.todoservice.service;

import net.planet01.todoservice.model.dto.TodoDto;
import net.planet01.todoservice.model.entity.Todo;
import net.planet01.todoservice.model.request.TaskCompleteRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoService {

    List<TodoDto> getTodos(String username);
    Todo getTodo(Long id);
    Todo addTodo(TodoDto todo);
    Todo taskCompleted(TaskCompleteRequest taskCompleteRequest);
}
