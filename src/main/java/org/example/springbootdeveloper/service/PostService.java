package org.example.springbootdeveloper.service;

import org.example.springbootdeveloper.dto.request.PostRequestDto;
import org.example.springbootdeveloper.dto.response.CommentResponseDto;
import org.example.springbootdeveloper.dto.response.PostResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.entity.Comment;
import org.example.springbootdeveloper.entity.Post;
import org.example.springbootdeveloper.repository.CommentRepository;
import org.example.springbootdeveloper.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    // 게시글 생성
    public ResponseDto<PostResponseDto> createPost(PostRequestDto dto) {
        try {
            Post post = new Post(null, dto.getTitle(), dto.getContent(), dto.getAuthor(), null);
            /*
                매개변수 유연성을 주기 위해 객체 생성에 사용
                Post Entity에 @Builder 애노테이션 존재
                Post post = Post.builder()
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .author(dto.getAuthor())
                        .build();
            */
            Post savedPost = postRepository.save(post);

            return ResponseDto.setSuccess("게시글을 성공적으로 등록했습니다.", convertToPostDto(savedPost));
        } catch (Exception e) {
            return ResponseDto.setFailed("게시글 등록에 실패했습니다." + e.getMessage());
        }
    }

    // 게시글 전체 조회
    public ResponseDto<List<PostResponseDto>> getAllPosts() {
        try {
            List<Post> posts = postRepository.findAll();
            List<PostResponseDto> postResponseDto = posts.stream()
                    .map(this::convertToPostDto)
                    // .map((post) -> convertToPostDto(post))
                    .collect(Collectors.toList());

            // 등록된 게시물이 없을 때
            if (postResponseDto.isEmpty()) {
                return ResponseDto.setFailed("등록된 게시글이 없습니다.");
            }
            return ResponseDto.setSuccess("게시글을 성공적으로 조회했습니다.", postResponseDto);
        } catch (Exception e) {
            return ResponseDto.setFailed("게시글 조회에 실패했습니다." + e.getMessage());
        }
    }

    // 특정 ID 게시글 조회
    public ResponseDto<PostResponseDto> getPostById(Long postId) {
        try {
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new Error("해당 게시글을 찾을 수 없습니다: " + postId));
            return ResponseDto.setSuccess("게시글 조회에 성공했습니다.", convertToPostDto(post));
        } catch (Exception e) {
            return ResponseDto.setFailed("게시글 조회에 실패했습니다." + e.getMessage());
        }
    }

    // 작성자를 사용하여 게시글 조회 - 필터링
    public ResponseDto<List<PostResponseDto>> getPostByAuthor(String Author) {
        try {
            List<Post> posts = postRepository.findByAuthor(Author);
            return ResponseDto.setSuccess(Author + "의 게시글 조회에 성공했습니다."
                    , posts.stream()
                            .map(this::convertToPostDto)
                            .collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseDto.setFailed(Author + "의 게시글 조회에 실패했습니다." + e.getMessage());
        }
    }

    // 특정 ID 게시글 수정
    public ResponseDto<PostResponseDto> updatePost(Long postId, PostRequestDto dto) {
        try {
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new Error("해당 게시글을 찾을 수 없습니다: " + postId));
            post.setTitle(dto.getTitle());
            post.setContent(dto.getContent());

            Post updatePost = postRepository.save(post);
            return ResponseDto.setSuccess("게시글 수정을 완료했습니다.", convertToPostDto(updatePost));
        } catch (Exception e) {
            return ResponseDto.setFailed("게시글 조회에 실패했습니다." + e.getMessage());
        }
    }

    // 특정 ID 게시글 삭제
    public ResponseDto<Void> deletePost(Long postId) {
        try {
            postRepository.deleteById(postId);
            return ResponseDto.setSuccess("게시글 삭제를 완료했습니다.", null);
        } catch (Exception e) {
            return ResponseDto.setFailed("게시글 삭제에 실패했습니다." + e.getMessage());
        }
    }

    // Entity(Post) -> PostResponseDto 변환
    private PostResponseDto convertToPostDto(Post post) {
        // 게시글에 연결된 댓글들을 가져옴
        List<CommentResponseDto> commentResponseDto = commentRepository.findByPostId(post.getId())
                .stream()
                .map(comment -> new CommentResponseDto(
                        comment.getId(), post.getId(), comment.getContent(),
                        comment.getCommenter()))
                .collect(Collectors.toList());

        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                commentResponseDto
        );
    }
}

    // Entity(Comment) -> CommentResponseDto 변환
//    private CommentResponseDto convertCommentToDto(Comment comment) {
//        return new CommentResponseDto(
//                comment.getId(), comment.getPost().getId(),
//                comment.getContent(), comment.getCommenter()
//        );
//    }
// CommentResponseDto 미리 생성하면 .map(this::convertResponseDto) 대체 가능
// == .map((comment) -> convertCommentToDto(comment))
