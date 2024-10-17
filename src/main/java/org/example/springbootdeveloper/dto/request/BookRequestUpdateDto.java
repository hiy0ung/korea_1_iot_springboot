package org.example.springbootdeveloper.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springbootdeveloper.entity.Category;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestUpdateDto {
    // 책 - id, writer, title, content, category
    // : 수정이 가능한 데이터 - title, content, category
    private String title;
    private String content;
    private Category category;
}
