package org.example.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.dto.request.BookRequestDto;
import org.example.springbootdeveloper.dto.request.BookRequestUpdateDto;
import org.example.springbootdeveloper.dto.response.BookResponseDto;
import org.example.springbootdeveloper.entity.Book;
import org.example.springbootdeveloper.entity.Category;
import org.example.springbootdeveloper.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // 생성자 주입
public class BookService {

    public final BookRepository bookRepository;

    // 1. 책 생성 (POST)
    public BookResponseDto createBook(BookRequestDto requestDto) {
        Book book = new Book(
            null, requestDto.getWriter(), requestDto.getTitle()
                , requestDto.getContent(), requestDto.getCategory()
        ); // Book에 전체 매개변수(5개)를 받아오는 생성자가 있는데 BookRequestDto에는 매개변수 4개 받아옴 (id값 X) > null로 지정

        Book savedBook = bookRepository.save(book);
        return convertToResponseDto(savedBook);
    }

    // 2. 전체 게시글 조회 (GET)
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::convertToResponseDto)
                // .map ((book) -> convertToResponseDto(book)) 과 같음
                .collect(Collectors.toList());
    }

    // 3. 특정 ID 책 조회
    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("책을 찾을 수 없습니다: " + id));
        return convertToResponseDto(book);
    }

    // 3-1. 제목에 특정 단어가 포함된 게시글 조회
    public List<BookResponseDto> getBookByTitleContaining(String keyword) {
        List<Book> books = bookRepository.findByTitleContaining(keyword);
        return books.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    // 3-2. 카테고리별 책 조회
    public List<BookResponseDto> getBooksByCategory(Category category) {
        return bookRepository.findByCategory(category)
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    // 3-3. 카테고리 & 작성자별 책 조회
    public List<BookResponseDto> getBooksByCategoryAndWriter(Category category, String writer) {
        List<Book> books;

        if (category == null) {
            books = bookRepository.findByWriter(writer);
        } else {
            books = bookRepository.findByCategoryAndWriter(category, writer);
        }
        return books.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    // 4. 특정 ID 책 수정
    public BookResponseDto updateBook(Long id, BookRequestUpdateDto updateDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("책을 찾을 수 없습니다: " + id));
        book.setTitle(updateDto.getTitle());
        book.setContent(updateDto.getContent());
        book.setCategory(updateDto.getCategory());

        Book updatedBook = bookRepository.save(book);
        return convertToResponseDto(updatedBook);
    }

    // 5. 특정 ID 책 삭제
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
