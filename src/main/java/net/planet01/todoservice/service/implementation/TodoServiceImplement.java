package net.planet01.todoservice.service.implementation;

import net.planet01.todoservice.model.dto.TodoDto;
import net.planet01.todoservice.model.entity.Todo;
import net.planet01.todoservice.model.entity.User;
import net.planet01.todoservice.model.request.TaskCompleteRequest;
import net.planet01.todoservice.model.request.TodoRequest;
import net.planet01.todoservice.repository.TodoRepository;
import net.planet01.todoservice.repository.UserRepository;
import net.planet01.todoservice.security.IAuthenticationFacade;
import net.planet01.todoservice.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class TodoServiceImplement implements TodoService {

    @Autowired
    TodoRepository todoRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Override
    public List<TodoDto> getTodos(String username) {
        List<TodoDto> returnValue = new ArrayList<>();
        List<Todo> todoEntity = todoRepository.findAllByUserUsername(username);
        for(Todo todo:todoEntity){
            returnValue.add( modelMapper.map(todo, TodoDto.class) );
        }
        return returnValue;
    }
//    @PostAuthorize("returnObject.user.username == authentication.name")
    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository
                .findByUserUsernameAndId(authenticationFacade.getAuthentication().getName(),id)
                .orElse(null);
        if(todo == null)
            return null;
        return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public TodoDto addTodo(TodoDto todo) {
        Todo todoEntity = modelMapper.map(todo,Todo.class);
        User user = userRepository.findByUsername(authenticationFacade.getAuthentication().getName()).get();
        todoEntity.setUser(user);
        todoEntity.setCreatedAt(LocalDate.now());
        todoEntity.setCompleted(false);
        todoEntity.setCompletedAt(null);
        Todo resultEntity = todoRepository.save(todoEntity);
        return modelMapper.map(resultEntity,TodoDto.class); //todoRepository.save(todoEntity);
    }

    @Override
    public TodoDto taskCompleted(Long id) throws UnsupportedOperationException{
        Todo todo = todoRepository.findById(id).orElse(null);
        if(todo == null)
            throw new UnsupportedOperationException("task not found");
        todo.setCompleted(true);
        todo.setCompletedAt(LocalDate.now());
        todoRepository.save(todo);
        return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public TodoDto editTodo(TodoDto todo, Long id) throws UnsupportedOperationException {
        Todo todoEntity = todoRepository.findById(id).orElse(null);
        if(todoEntity == null)
            throw new UnsupportedOperationException("task not found");
        todoEntity.setTask(todo.getTask());
        todoEntity.setDescription(todo.getDescription());
        todoRepository.save(todoEntity);
        return modelMapper.map(todoEntity,TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) throws UnsupportedOperationException {
        Todo todoEntity = todoRepository.findById(id).orElse(null);
        if(todoEntity == null)
            throw new UnsupportedOperationException("task not found");
        todoRepository.deleteById(id);
    }
}
