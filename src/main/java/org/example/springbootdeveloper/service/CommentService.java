package org.example.springbootdeveloper.service;

import org.example.springbootdeveloper.dto.request.CommentRequestDto;
import org.example.springbootdeveloper.dto.response.CommentResponseDto;
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
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // 댓글 생성
    public ResponseDto<CommentResponseDto> createComment(CommentRequestDto dto) {
        try {
            Post post = postRepository.findById(dto.getPostId())
                    .orElseThrow(() -> new Error("해당 게시글을 찾을 수 없습니다: " + dto.getPostId()));

            Comment comment = new Comment(null, post, dto.getContent(), dto.getCommenter());

            Comment savedComment = commentRepository.save(comment);
            return ResponseDto.setSuccess("댓글 생성을 성공했습니다.", convertToCommentDto(savedComment));
        } catch (Exception e) {
            return ResponseDto.setFailed("댓글 생성에 실패했습니다."+ e.getMessage());
        }
    }

    // 특정 게시물 댓글 전체 조회
    public ResponseDto<List<CommentResponseDto>> getCommentsByPost(Long postId) {
        try {
            List<Comment> comments = commentRepository.findByPostId(postId);
        List<CommentResponseDto> commentResponseDto = comments.stream()
                .map(this::convertToCommentDto)
                .collect(Collectors.toList());
        return ResponseDto.setSuccess("전체 댓글 조회에 성공했습니다.", commentResponseDto);
        } catch (Exception e) {
            return ResponseDto.setFailed("전체 댓글 조회에 실패했습니다." + e.getMessage());
        }
    }

    // 특정 ID 댓글 수정
    public ResponseDto<CommentResponseDto> updateComment(Long commentId, String newContent) {
        try {
            Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new Error("해당 댓글을 찾을 수 없습니다: " + commentId));
            comment.setContent(newContent);

            Comment updateComment = commentRepository.save(comment);
            return ResponseDto.setSuccess("댓글 수정에 성공했습니다.", convertToCommentDto(updateComment));
        } catch (Exception e) {
            return ResponseDto.setFailed("댓글 수정에 실패했습니다." + e.getMessage());
        }
    }

    // 특정 ID 댓글 삭제
    public ResponseDto<Void> deleteComment(Long commentId) {
        try {
            commentRepository.deleteById(commentId);
            return ResponseDto.setSuccess("게시글 삭제를 완료했습니다.", null);

        } catch (Exception e) {
            return ResponseDto.setFailed("댓글 삭제에 실패했습니다." + e.getMessage());
        }
    }

    // Entity -> CommentDto
    private CommentResponseDto convertToCommentDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(), comment.getPost().getId(), comment.getContent(), comment.getCommenter()
        );
    }
}
