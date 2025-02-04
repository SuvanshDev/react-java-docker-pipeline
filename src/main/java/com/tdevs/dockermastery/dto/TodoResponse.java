package com.tdevs.dockermastery.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class TodoResponse {
    private List<TodoDTO> todos;

    public TodoResponse(List<TodoDTO> todos) {
        this.todos = todos;
    }
}
