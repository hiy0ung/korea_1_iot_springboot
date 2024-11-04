package org.example.springbootdeveloper.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.common.constant.ResponseMessage;
import org.example.springbootdeveloper.dto.request.PostTodoRequestDto;
import org.example.springbootdeveloper.dto.request.PutTodoRequestDto;
import org.example.springbootdeveloper.dto.response.GetTodoListResponseDto;
import org.example.springbootdeveloper.dto.response.PutTodoResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.dto.response.PostTodoResponseDto;
import org.example.springbootdeveloper.entity.Todo;
import org.example.springbootdeveloper.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // 할 일 생성
    public ResponseDto<PostTodoResponseDto> createTodo(@Valid PostTodoRequestDto dto) {
        PostTodoResponseDto data = null;

        try {
            Todo todo = Todo.builder()
                    .task(dto.getTask())
                    .status(dto.isStatus())
                    .build();

            todoRepository.save(todo);

            data = new PostTodoResponseDto(todo);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 전체 할 일 조회
    public ResponseDto<List<GetTodoListResponseDto>> getAllTodos() {
        List<GetTodoListResponseDto> data = null;

        try {
            List<Todo> todos = todoRepository.findAll();

            data = todos.stream()
                    .map(GetTodoListResponseDto::new)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 할 일 수정
    public ResponseDto<PutTodoResponseDto> updateTodo(Long id, @Valid PutTodoRequestDto dto) {
        PutTodoResponseDto data = null;
        Long todoId = id;

        try {
            Optional<Todo> todoOptional = todoRepository.findById(todoId);

            if (todoOptional.isPresent()) {
                Todo todo = todoOptional.get();
                todo.setStatus(dto.isStatus());

                todoRepository.save(todo);
                data = new PutTodoResponseDto(todo);
            } else {
                ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 할 일 삭제
    public ResponseDto<Void> deleteTodo(Long id) {
        Long todoId = id;

        try {
            if(!todoRepository.existsById(todoId)) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
            todoRepository.deleteById(todoId);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }
}
