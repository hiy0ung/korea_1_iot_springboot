package org.example.springbootdeveloper.service;

import org.example.springbootdeveloper.dto.BoardDto;
import org.example.springbootdeveloper.entity.Board;
import org.example.springbootdeveloper.repository.BoardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 전체 조회
    public List<BoardDto> getAllBoards() {
        try {
            List<Board> boards = boardRepository.findAll();
            List<BoardDto> boardsDto = boards.stream()
                    .map(board -> new BoardDto(
                            board.getId(),
                            board.getWriter(),
                            board.getTitle(),
                            board.getContent(),
                            board.getCategory()
                    ))
                    .collect(Collectors.toList());
            return boardsDto;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while fetching boards", e
            );
        }
    }

    // 단건 조회
    public BoardDto getBoardById(Long id) {
        try {
            Board board = boardRepository.findById(id)
                    .orElseThrow(() -> new Error("Board not found with id: " + id));
            BoardDto boardDto = new BoardDto(
                    board.getId(),
                    board.getWriter(),
                    board.getTitle(),
                    board.getContent(),
                    board.getCategory()
            );
            return boardDto;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while fetching the board", e
            );
        }
    }

    // 생성
    public BoardDto createBoard(BoardDto boardDto) {
        try {
           Board board = new Board(boardDto.getId(), boardDto.getWriter(), boardDto.getTitle(), boardDto.getContent(), boardDto.getCategory());

           Board savedBoard = boardRepository.save(board);

           return new BoardDto(savedBoard.getId()
                   , savedBoard.getWriter()
                   , savedBoard.getTitle()
                   , savedBoard.getContent()
                   , savedBoard.getCategory());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Failed to create board", e
            );
        }
    }

    // 수정
    public BoardDto updateBoard(Long id, BoardDto boardDto) {
        try {
            Board board = boardRepository.findById(id)
                    .orElseThrow(() -> new Error("Board not found with id: " + id));

            board.setWriter(boardDto.getWriter());
            board.setTitle(boardDto.getTitle());
            board.setContent(boardDto.getContent());
            board.setCategory(boardDto.getCategory());

            Board updateBoard = boardRepository.save(board);

            return new BoardDto(updateBoard.getId()
                    , updateBoard.getWriter()
                    , updateBoard.getTitle()
                    , updateBoard.getContent()
                    , updateBoard.getCategory()
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while updating student"
            );
        }
    }

    // 삭제
    public void deleteBoard(Long id) {
        try {
            Board board = boardRepository.findById(id)
                    .orElseThrow(() ->
                            new Error("Board not found with id: " + id));
            boardRepository.delete(board);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while deleting board", e
            );
        }
    }
}
