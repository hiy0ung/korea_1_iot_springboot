package org.example.springbootdeveloper.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
public class MenuRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @Min(0)
    @NotNull
    private int price;
    @NotNull
    private boolean isAvailable;
    @NotNull
    private String category;
    private String size;
}
