package org.example.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.dto.request.BookRequestDto;
import org.example.springbootdeveloper.dto.request.BookRequestUpdateDto;
import org.example.springbootdeveloper.dto.response.BookResponseDto;
import org.example.springbootdeveloper.entity.Book;
import org.example.springbootdeveloper.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // 생성자 주입
public class BookService {

    public final BookRepository bookRepository;

    // 책 생성 (POST)
    public BookResponseDto createBook(BookRequestDto requestDto) {
        Book book = new Book(
            null, requestDto.getWriter(), requestDto.getTitle()
                , requestDto.getContent(), requestDto.getCategory()
        ); // Book에 전체 매개변수(5개)를 받아오는 생성자가 있는데 BookRequestDto에는 매개변수 4개 받아옴 (id값 X) > null로 지정

        Book savedBook = bookRepository.save(book);
        return convertToResponseDto(savedBook);
    }

    // 전체 게시글 조회 (GET)
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::convertToResponseDto)
                // .map ((book) -> convertToResponseDto(book)) 과 같음
                .collect(Collectors.toList());
    }

    // 특정 ID 책 조회
    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("책을 찾을 수 없습니다: " + id));
        return convertToResponseDto(book);
    }

    // 특정 ID 책 수정
    public BookResponseDto updateBook(Long id, BookRequestUpdateDto updateDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("책을 찾을 수 없습니다: " + id));
        book.setTitle(updateDto.getTitle());
        book.setContent(updateDto.getContent());
        book.setCategory(updateDto.getCategory());

        Book updatedBook = bookRepository.save(book);
        return convertToResponseDto(updatedBook);
    }

    // 특정 ID 책 삭제
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Entity -> Response Dto 변환
    private BookResponseDto convertToResponseDto(Book book) {
        return new BookResponseDto (
                book.getId(), book.getWriter(), book.getTitle()
                , book.getContent(), book.getCategory()
        );
    }
}
