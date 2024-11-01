package org.example.springbootdeveloper.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.common.constant.ApiMappingPattern;
import org.example.springbootdeveloper.dto.request.PostTodoRequestDto;
import org.example.springbootdeveloper.dto.request.PutTodoRequestDto;
import org.example.springbootdeveloper.dto.response.GetTodoListResponseDto;
import org.example.springbootdeveloper.dto.response.PutTodoResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.dto.response.PostTodoResponseDto;
import org.example.springbootdeveloper.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.TODO)
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // 할 일 생성
    @PostMapping
    public ResponseEntity<ResponseDto<PostTodoResponseDto>> createTodo(@Valid @RequestBody PostTodoRequestDto dto) {
        ResponseDto<PostTodoResponseDto> result = todoService.createTodo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 전체 할 일 조회
    @GetMapping
    public ResponseEntity<ResponseDto<List<GetTodoListResponseDto>>> getAllTodos() {
        ResponseDto<List<GetTodoListResponseDto>> result = todoService.getAllTodos();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 할 일 수정
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PutTodoResponseDto>> updateTodo(@PathVariable Long id, @Valid @RequestBody PutTodoRequestDto dto) {
        ResponseDto<PutTodoResponseDto> result = todoService.updateTodo(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 할 일 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteTodo(@PathVariable Long id) {
        ResponseDto<Void> result = todoService.deleteTodo(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
