package org.example.springbootdeveloper.dto.response;

import lombok.*;
import org.example.springbootdeveloper.entity.Menu;

@Data
@NoArgsConstructor
public class MenuResponseDto {
    private Long id;
    private String name;
    private String description;
    private int price;
    private boolean isAvailable;
    private String category;
    private String size;

    public MenuResponseDto(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.description = menu.getDescription();
        this.price = menu.getPrice();
        this.isAvailable = menu.isAvailable();
        this.category = menu.getCategory();
        this.size = menu.getSize();
    }
}
