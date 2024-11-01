package org.example.springbootdeveloper.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springbootdeveloper.entity.Todo;

@Data
@NoArgsConstructor
public class PutTodoResponseDto {
    private Long id;
    private String task;
    private boolean status;

    public PutTodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.task = todo.getTask();
        this.status = todo.isStatus();
    }
}
