package org.example.springbootdeveloper.controller;

import org.example.springbootdeveloper.dto.request.PostRequestDto;
import org.example.springbootdeveloper.dto.response.PostResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // CRUD 기능 명시

    @PostMapping
    public ResponseDto<PostResponseDto> createPost(@RequestBody PostRequestDto dto) {
        return postService.createPost(dto);
    }

    @GetMapping
    public ResponseDto<List<PostResponseDto>> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public ResponseDto<PostResponseDto> getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    // 작성자를 사용하여 게시글 조회 - 필터링
    @GetMapping("/search/author")
    public ResponseDto<List<PostResponseDto>> getPostByAuthor(@RequestParam String Author) {
        return postService.getPostByAuthor(Author);

    }

    @PutMapping("/{postId}")
    public ResponseDto<PostResponseDto> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto dto) {
        return postService.updatePost(postId, dto);
    }

    @DeleteMapping("/{postId}")
    public ResponseDto<Void> deletePost(@PathVariable Long postId) {
        return postService.deletePost(postId);
    }
}
