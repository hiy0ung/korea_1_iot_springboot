package org.example.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.dto.request.BookRequestDto;
import org.example.springbootdeveloper.dto.request.BookRequestUpdateDto;
import org.example.springbootdeveloper.dto.response.BookResponseDto;
import org.example.springbootdeveloper.entity.Category;
import org.example.springbootdeveloper.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
// 초기화 되지 않은 final 필드나 @NonNull이 붙은 필드에 대해 생성자를 생성
public class BookController {
    // Service 객체를 주입 받아 사용하는 변수
    private final BookService bookService;

    // 생성자 주입 - @RequiredArgsConstructor로 대체
//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//    }

    // 책 생성
    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto requestDto) {
        BookResponseDto createBook = bookService.createBook(requestDto);
        return ResponseEntity.ok(createBook);
    }

    // 전체 책 조회
    @GetMapping
    // ResponseEntity: 그냥 배열을 가져와도 되지만 HTTP 표준 형식? 으로 가져와줌 (포장지)
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        List<BookResponseDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // 단건 책 조회
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        BookResponseDto book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    // 제목에 특정 단어가 포함된 게시글 조회
    @GetMapping("/search/title")
    public ResponseEntity<List<BookResponseDto>> getBooksByTitleContaining(@RequestParam String keyword) {
        List<BookResponseDto> books = bookService.getBookByTitleContaining(keyword);
        return ResponseEntity.ok(books);
    }

    // 카테고리별 책 조회
    @GetMapping("/category/{category}")
    public ResponseEntity<List<BookResponseDto>> getBooksByCategory(@PathVariable Category category) {
        List<BookResponseDto> books = bookService.getBooksByCategory(category);
        return ResponseEntity.ok(books);
    }

    // 카테고리와 작성자별 책 조회
    @GetMapping("/search/category-writer")
    public ResponseEntity<List<BookResponseDto>> getBooksByCategoryAndWriter(
            @RequestParam(required = false) Category category,
            @RequestParam String writer
    ) {
        List<BookResponseDto> books = bookService.getBooksByCategoryAndWriter(category, writer);
            return ResponseEntity.ok(books);
    }

    // 특정 ID 책 수정
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(
            @PathVariable Long id, @RequestBody BookRequestUpdateDto requestDto
    ) {
        BookResponseDto updatedBook = bookService.updateBook(id, requestDto);
        return ResponseEntity.ok(updatedBook);
    }

    // 특정 ID 책 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
