package net.planet01.todoservice.controller;

import net.planet01.todoservice.model.dto.TodoDto;
import net.planet01.todoservice.model.request.TodoRequest;
import net.planet01.todoservice.model.response.ErrorResponse;
import net.planet01.todoservice.model.response.TodoResponse;
import net.planet01.todoservice.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/todo-service")
public class TodoController {

    @Autowired
    TodoService todoService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/")
    public ResponseEntity<List<TodoResponse>> getTodos(Authentication authentication){
        List<TodoDto> todos = todoService.getTodos(authentication.getName());
        List<TodoResponse> todosResponse = new ArrayList<>();
        todos.stream().forEach(x->{
            todosResponse.add(modelMapper.map(x,TodoResponse.class));
        });
        return new ResponseEntity<>(todosResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTodo(@PathVariable Long id){
        TodoDto todoDto = todoService.getTodo(id);
        if(todoDto == null){
            return new ResponseEntity<>("Todo Item not found",HttpStatus.NOT_FOUND);
        }
        TodoResponse todoResponse = modelMapper.map(todoDto,TodoResponse.class);
        return new ResponseEntity<>(todoResponse, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> addTodo(@Valid @RequestBody TodoRequest todoRequest){
        TodoDto todoDto = modelMapper.map(todoRequest,TodoDto.class);
        TodoDto returnDto = todoService.addTodo(todoDto);
        TodoResponse todoResponse = modelMapper.map(returnDto,TodoResponse.class);
        return new ResponseEntity<>(todoResponse, HttpStatus.CREATED);
    }

    @PutMapping("/task-complete/{id}")
    public ResponseEntity<Object> taskCompleted(@PathVariable Long id){
        try{
            TodoDto todoDto = todoService.taskCompleted(id);
            return new ResponseEntity<>(modelMapper.map(todoDto,TodoResponse.class), HttpStatus.CREATED);
        }catch(UnsupportedOperationException ex){
            return new ResponseEntity<>(new ErrorResponse(400,"task not found"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editTodo(@Valid @RequestBody TodoRequest todoRequest,@PathVariable Long id){
        try{
            TodoDto todoDto = modelMapper.map(todoRequest,TodoDto.class);
            TodoDto returnDto = todoService.editTodo(todoDto,id);
            TodoResponse todoResponse = modelMapper.map(returnDto,TodoResponse.class);
            return new ResponseEntity<>(todoResponse, HttpStatus.CREATED);
        }catch(UnsupportedOperationException ex){
            return new ResponseEntity<>(new ErrorResponse(400,"task not found"), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTodo(@PathVariable Long id){
        try{
            todoService.deleteTodo(id);
            return new ResponseEntity<>(new ErrorResponse(201,"task deleted successfully"), HttpStatus.NO_CONTENT);

        }catch(UnsupportedOperationException ex){
            return new ResponseEntity<>(new ErrorResponse(400,"task not found"), HttpStatus.BAD_REQUEST);
        }
    }
}
