package org.example.springbootdeveloper.controller;

import org.example.springbootdeveloper.common.constant.ApiMappingPattern;
import org.example.springbootdeveloper.dto.request.PostRequestDto;
import org.example.springbootdeveloper.dto.response.PagedResponseDto;
import org.example.springbootdeveloper.dto.response.PostResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.POST)
public class PostController {

    @Autowired
    private PostService postService;

    // CRUD 기능 명시

    @PostMapping
    public ResponseEntity<ResponseDto<PostResponseDto>> createPost(@RequestBody PostRequestDto dto) {
        ResponseDto<PostResponseDto> result = postService.createPost(dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

//    @GetMapping
//    public ResponseDto<List<PostResponseDto>> getAllPosts() {
//        return postService.getAllPosts();
//    }

    @GetMapping
    public ResponseEntity<ResponseDto<PagedResponseDto<PostResponseDto>>> getPosts(
            // @RequestParam: url을 통해 key와 value 형태로 요청값을 보냄
            // page: 현재 페이지 번호, size: 페이지 당 표시할 데이터 개수
            @RequestParam int page,
            @RequestParam int size) {
        try {
            // 요청한 페이지에 대한 데이터를 반환 (응답)
            ResponseDto<PagedResponseDto<PostResponseDto>> result = postService.getPosts(page, size);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{postId}")
    public ResponseDto<PostResponseDto> getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    // 작성자를 사용하여 게시글 조회 - 필터링
//    @GetMapping("/search/author")
//    public ResponseDto<List<PostResponseDto>> getPostByAuthor(@RequestParam String Author) {
//        return postService.getPostByAuthor(Author);
//
//    }

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
