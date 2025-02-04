package com.tdevs.dockermastery.controllers;

import com.tdevs.dockermastery.dto.TodoResponse;
import com.tdevs.dockermastery.entities.Todo;
import com.tdevs.dockermastery.repositories.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class RootControllerTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private RootController rootController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRoot() {
        // Arrange
        Todo todo1 = new Todo();
        todo1.setId(1);
        todo1.setTitle("Todo 1");
        todo1.setDescription("Description 1");
        todo1.setStatus("pending");

        Todo todo2 = new Todo();
        todo2.setId(2);
        todo2.setTitle("Todo 2");
        todo2.setDescription("Description 2");
        todo2.setStatus("completed");

        when(todoRepository.findAll()).thenReturn(Arrays.asList(todo1, todo2));

        // Act
        ResponseEntity<?> response = rootController.getRoot();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code should be OK");
        TodoResponse body = (TodoResponse) response.getBody();
        assert body != null;
        assertEquals(2, body.getTodos().size(), "Should return two todos");
    }
}