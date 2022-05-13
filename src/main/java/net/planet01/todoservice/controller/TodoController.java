package net.planet01.todoservice.controller;

import net.planet01.todoservice.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/todo-service")
public class TodoController {

    @Autowired
    TodoService todoService;


    @GetMapping("/")
    public String getTodos(Authentication authentication){
        todoService.getTodos(authentication.getName());
        return "Sample Text";
    }
}
