package org.example.springbootdeveloper.service;

import org.example.springbootdeveloper.common.constant.ResponseMessage;
import org.example.springbootdeveloper.dto.request.PostRequestDto;
import org.example.springbootdeveloper.dto.response.CommentResponseDto;
import org.example.springbootdeveloper.dto.response.PagedResponseDto;
import org.example.springbootdeveloper.dto.response.PostResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.entity.Post;
import org.example.springbootdeveloper.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public ResponseDto<PostResponseDto> createPost(PostRequestDto dto) {
        try {
            Post post = Post.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .author(dto.getAuthor())
                    .build();
            postRepository.save(post);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, convertToPostResponseDto(post));
        } catch (Exception e) {
            return ResponseDto.setFailed("게시글 등록 중 오류가 발생했습니다: " + e.getMessage());
        }

    }

    public ResponseDto<List<PostResponseDto>> getAllPosts() {
        try {
            List<Post> posts = postRepository.findAll();
            List<PostResponseDto> postResponseDtos = posts.stream()
                    .map(this::convertToPostResponseDto)
                    .collect(Collectors.toList());

            if (postResponseDtos.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_POST);
            }

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, postResponseDtos);
        } catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    /*
    getPosts
        @Param: 페이지 번호, 페이지 크기
        @return: ResponseDto - 성공 메세지와 페이징된 게시글 목록을 포함
    */
    public ResponseDto<PagedResponseDto<PostResponseDto>> getPosts(int page, int size) {
        PagedResponseDto<PostResponseDto> pagedResponse = null;

        try {
            // Page와 size 값을 사용해 PageRequest 객체를 생성
            // : 해당 객체를 통해 DB에 해당 페이지의 Post 목록을 조회
            // : 결과는 Page<Post> 타입으로 반환

            /*
                1. PageRequest: Pageable 인터페이스의 구현체
                                - 특정 페이지의 데이터 조회 요청을 정의하는 객체
                                - 페이지 번호와 크기(데이터 수)를 기반으로 페이징 요청을 설정

                    EX) PageRequest.of(int page, int size)
                          : page - 페이지 번호 (0부터 시작)
                          : size - 한 페이지에 포함할 데이터 개수
                          PageRequest.of(2, 10)

                2. Page<T>: JPA에서 제공하는 인터페이스
                    , 특정 페이지에 대한 데이터와 페이징 정보를 포함한 객체
                    - 조회된 데이터 목록뿐만 아니라 페이징과 관련된 메타정보도 함께 제공
                    - 주요 메서드
                        : getContent() - 현재 페이지 데이터 목록
                        : getNumber() - 현재 페이지 번호 반환 (0부터 시작)
                        : getSize() - 한 페이지에 포함된 데이터의 개수 반환
                        : getTotalPages() - 전체 페이지 수 반환
                        : getTotalElements() - 전체 데이터(요소) 수 반환
            */
            Page<Post> postPage = postRepository.findAll(PageRequest.of(page, size));

            List<PostResponseDto> postDtos = postPage.getContent().stream()
                    .map(PostResponseDto::new)
                    .collect(Collectors.toList());

            pagedResponse = new PagedResponseDto<>(
                    postDtos,
                    postPage.getNumber(), // 요청된 페이지 번호
                    postPage.getTotalPages(), // 전체 페이지 수
                    postPage.getTotalElements() // 전체 데이터(요소) 수
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess("게시글 목록 조회 성공", pagedResponse);
    }

    public ResponseDto<PostResponseDto> getPostById(Long postId) {
        return null;
    }


    public ResponseDto<PostResponseDto> updatePost(Long postId, PostRequestDto dto) {
        return null;
    }

    public ResponseDto<Void> deletePost(Long postId) {
        return null;
    }

    private PostResponseDto convertToPostResponseDto(Post post) {
        List<CommentResponseDto> commentDtos = post.getComments().stream()
                .map(comment -> new CommentResponseDto(comment.getId(), post.getId(), comment.getContent(), comment.getCommenter()))
                .collect(Collectors.toList());

        return new PostResponseDto(
                post.getId(), post.getTitle(), post.getContent(), post.getAuthor(), commentDtos
        );
    }

}

// 내가 작성한 거
//import org.example.springbootdeveloper.dto.request.PostRequestDto;
//import org.example.springbootdeveloper.dto.response.CommentResponseDto;
//import org.example.springbootdeveloper.dto.response.PagedResponseDto;
//import org.example.springbootdeveloper.dto.response.PostResponseDto;
//import org.example.springbootdeveloper.dto.response.ResponseDto;
//import org.example.springbootdeveloper.entity.Post;
//import org.example.springbootdeveloper.repository.CommentRepository;
//import org.example.springbootdeveloper.repository.PostRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;

//@Service
//public class PostService {
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @Autowired
//    private CommentRepository commentRepository;
//
//    // 게시글 생성
//    public ResponseDto<PostResponseDto> createPost(PostRequestDto dto) {
//        try {
//            Post post = new Post(null, dto.getTitle(), dto.getContent(), dto.getAuthor(), null);
//            /*
//                매개변수 유연성을 주기 위해 객체 생성에 사용
//                Post Entity에 @Builder 애노테이션 존재
//                Post post = Post.builder()
//                        .title(dto.getTitle())
//                        .content(dto.getContent())
//                        .author(dto.getAuthor())
//                        .build();
//            */
//            Post savedPost = postRepository.save(post);
//
//            return ResponseDto.setSuccess("게시글을 성공적으로 등록했습니다.", convertToPostDto(savedPost));
//        } catch (Exception e) {
//            return ResponseDto.setFailed("게시글 등록에 실패했습니다." + e.getMessage());
//        }
//    }
//
//    // 게시글 전체 조회
//    public ResponseDto<List<PostResponseDto>> getAllPosts() {
//        try {
//            List<Post> posts = postRepository.findAll();
//            List<PostResponseDto> postResponseDto = posts.stream()
//                    .map(this::convertToPostDto)
//                    // .map((post) -> convertToPostDto(post))
//                    .collect(Collectors.toList());
//
//            // 등록된 게시물이 없을 때
//            if (postResponseDto.isEmpty()) {
//                return ResponseDto.setFailed("등록된 게시글이 없습니다.");
//            }
//            return ResponseDto.setSuccess("게시글을 성공적으로 조회했습니다.", postResponseDto);
//        } catch (Exception e) {
//            return ResponseDto.setFailed("게시글 조회에 실패했습니다." + e.getMessage());
//        }
//    }
//
//    public ResponseDto<PagedResponseDto<PostResponseDto>> getPosts(int page, int size) {
//        Page<Post> postPage = postRepository.findAll(PageRequest.of(page, size));
//
//        List<PostResponseDto> postDtos = postPage.getContent().stream()
//                .map(PostResponseDto::new)
//                .collect(Collectors.toList());
//
//        PagedResponseDto<PostResponseDto> pagedResponse = new PagedResponseDto<>(
//                postDtos,
//                postPage.getNumber(),
//                postPage.getTotalPages(),
//                postPage.getTotalElements()
//        );
//
//
//        // 특정 ID 게시글 조회
//    public ResponseDto<PostResponseDto> getPostById(Long postId) {
//        try {
//            Post post = postRepository.findById(postId)
//                    .orElseThrow(() -> new Error("해당 게시글을 찾을 수 없습니다: " + postId));
//            return ResponseDto.setSuccess("게시글 조회에 성공했습니다.", convertToPostDto(post));
//        } catch (Exception e) {
//            return ResponseDto.setFailed("게시글 조회에 실패했습니다." + e.getMessage());
//        }
//    }
//
//    // 작성자를 사용하여 게시글 조회 - 필터링
//    public ResponseDto<List<PostResponseDto>> getPostByAuthor(String Author) {
//        try {
//            List<Post> posts = postRepository.findByAuthor(Author);
//            return ResponseDto.setSuccess(Author + "의 게시글 조회에 성공했습니다."
//                    , posts.stream()
//                            .map(this::convertToPostDto)
//                            .collect(Collectors.toList()));
//        } catch (Exception e) {
//            return ResponseDto.setFailed(Author + "의 게시글 조회에 실패했습니다." + e.getMessage());
//        }
//    }
//
//    // 특정 ID 게시글 수정
//    public ResponseDto<PostResponseDto> updatePost(Long postId, PostRequestDto dto) {
//        try {
//            Post post = postRepository.findById(postId)
//                    .orElseThrow(() -> new Error("해당 게시글을 찾을 수 없습니다: " + postId));
//            post.setTitle(dto.getTitle());
//            post.setContent(dto.getContent());
//
//            Post updatePost = postRepository.save(post);
//            return ResponseDto.setSuccess("게시글 수정을 완료했습니다.", convertToPostDto(updatePost));
//        } catch (Exception e) {
//            return ResponseDto.setFailed("게시글 조회에 실패했습니다." + e.getMessage());
//        }
//    }
//
//    // 특정 ID 게시글 삭제
//    public ResponseDto<Void> deletePost(Long postId) {
//        try {
//            postRepository.deleteById(postId);
//            return ResponseDto.setSuccess("게시글 삭제를 완료했습니다.", null);
//        } catch (Exception e) {
//            return ResponseDto.setFailed("게시글 삭제에 실패했습니다." + e.getMessage());
//        }
//    }
//
//    // Entity(Post) -> PostResponseDto 변환
//    private PostResponseDto convertToPostDto(Post post) {
//        // 게시글에 연결된 댓글들을 가져옴
//        List<CommentResponseDto> commentResponseDto = commentRepository.findByPostId(post.getId())
//                .stream()
//                .map(comment -> new CommentResponseDto(
//                        comment.getId(), post.getId(), comment.getContent(),
//                        comment.getCommenter()))
//                .collect(Collectors.toList());
//
//        return new PostResponseDto(
//                post.getId(),
//                post.getTitle(),
//                post.getContent(),
//                post.getAuthor(),
//                commentResponseDto
//        );
//    }
//}

    // Entity(Comment) -> CommentResponseDto 변환
//    private CommentResponseDto convertCommentToDto(Comment comment) {
//        return new CommentResponseDto(
//                comment.getId(), comment.getPost().getId(),
//                comment.getContent(), comment.getCommenter()
//        );
//    }
// CommentResponseDto 미리 생성하면 .map(this::convertResponseDto) 대체 가능
// == .map((comment) -> convertCommentToDto(comment))
