package com.tdevs.dockermastery.controllers;

import com.tdevs.dockermastery.dto.TodoDTO;
import com.tdevs.dockermastery.dto.TodoResponse;
import com.tdevs.dockermastery.entities.Todo;
import com.tdevs.dockermastery.mappers.TodoMapper;
import com.tdevs.dockermastery.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RootController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/todos")
    public ResponseEntity<?> getRoot() {
        List<Todo> allTodos = todoRepository.findAll();
        List<TodoDTO> todoDTOs = allTodos.stream()
                .map(TodoMapper::convertToDto)
                .toList();
        return new ResponseEntity<TodoResponse>(new TodoResponse(todoDTOs), HttpStatus.OK);
    }

}
