package org.example.springbootdeveloper.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.springbootdeveloper.entity.Comment;
import org.example.springbootdeveloper.entity.Post;

import java.util.List;

@Data
@AllArgsConstructor
public class PostResponseDto {
    private long id;
    private String title;
    private String content;
    private String author;

    // 해당 게시글의 댓글 리스트를 포함
    private List<CommentResponseDto> comments;

    // Post 엔티티 객체를 받아서 DTO로 변환하는 생성자
    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        // comments 필드는 별도로 생성하지 않으므로 기본값인 null 지정
    }
}
