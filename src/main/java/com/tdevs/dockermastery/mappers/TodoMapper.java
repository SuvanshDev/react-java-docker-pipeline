package com.tdevs.dockermastery.mappers;


import com.tdevs.dockermastery.dto.TodoDTO;
import com.tdevs.dockermastery.entities.Todo;

public class TodoMapper {
    public static TodoDTO convertToDto(Todo todo) {
        TodoDTO dto = new TodoDTO();
        dto.setId(todo.getId());
        dto.setTitle(todo.getTitle());
        dto.setDescription(todo.getDescription());
        dto.setStatus(todo.getStatus().toString());
        return dto;
    }
}
