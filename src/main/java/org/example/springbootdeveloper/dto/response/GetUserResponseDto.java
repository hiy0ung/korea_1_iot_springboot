package org.example.springbootdeveloper.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springbootdeveloper.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponseDto {
    private Long id;
    private String email;

    public GetUserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }
}
