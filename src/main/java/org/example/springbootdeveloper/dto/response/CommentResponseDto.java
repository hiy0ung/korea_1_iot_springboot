package org.example.springbootdeveloper.dto.response;

import lombok.Data;

@Data
public class CommentResponseDto {
    private Long id;
    private Long postId;
    private String content;
    private String commenter;
}
