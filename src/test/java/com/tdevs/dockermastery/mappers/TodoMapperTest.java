package com.tdevs.dockermastery.mappers;

import com.tdevs.dockermastery.dto.TodoDTO;
import com.tdevs.dockermastery.entities.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

class TodoMapperTest {

    @Test
    void convertToDto() {
        // Given
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTitle("Sample Todo");
        todo.setDescription("This is a sample todo item");
        todo.setStatus("pending");

        // When
        TodoDTO dto = TodoMapper.convertToDto(todo);

        // Then
        assertEquals(todo.getId(), dto.getId(), "The ID should match");
        assertEquals(todo.getTitle(), dto.getTitle(), "The title should match");
        assertEquals(todo.getDescription(), dto.getDescription(), "The description should match");
        assertEquals(todo.getStatus().toString(), dto.getStatus(), "The status should match");
    }
}