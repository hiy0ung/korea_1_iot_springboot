package org.example.springbootdeveloper.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PutTodoRequestDto {
    @NotNull
    private boolean status;
}
