package net.planet01.todoservice.service.implementation;

import net.planet01.todoservice.model.dto.TodoDto;
import net.planet01.todoservice.model.entity.Todo;
import net.planet01.todoservice.model.request.TaskCompleteRequest;
import net.planet01.todoservice.model.request.TodoRequest;
import net.planet01.todoservice.repository.TodoRepository;
import net.planet01.todoservice.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class TodoServiceImplement implements TodoService {

    @Autowired
    TodoRepository todoRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<TodoDto> getTodos(String username) {
//        return todoRepository.findAllByUserAndUsername(username);
        return null;
    }


    @Override
    public Todo getTodo(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    @Override
    public Todo addTodo(TodoDto todo) {
        Todo todoEntity = new Todo();
        BeanUtils.copyProperties(todo,todoEntity);
        return todoRepository.save(todoEntity);
    }

    @Override
    public Todo taskCompleted(TaskCompleteRequest taskCompleteRequest) {
        Todo todo = todoRepository.findById(taskCompleteRequest.getId()).orElse(null);
        todo.setCompleted(taskCompleteRequest.isTaskCompleted());
        todo.setCompletedAt(LocalDate.now());
        todoRepository.save(todo);
        return todo;
    }
}
