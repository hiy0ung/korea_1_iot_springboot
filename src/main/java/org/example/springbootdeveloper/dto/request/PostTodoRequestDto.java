package org.example.springbootdeveloper.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostTodoRequestDto {
    @NotNull
    private String task;
    @NotNull
    private boolean status;
}
