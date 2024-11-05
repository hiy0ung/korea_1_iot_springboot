package org.example.springbootdeveloper.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data // getter, setter 등이 포함
@AllArgsConstructor
public class PagedResponseDto<T> {
    // 제네릭 <T> 타입을 사용하여 "페이지 응답을 일반화"한 DTO 클래스를 선언
    // 페이지네이션을 사용하는 모든 것을 담아서 응답 (ResponseDto와 같은 역할)

    private List<T> content;
    // 현재 페이지에 포함된 데이터 목록을 저장 - 제네릭을 사용해 다양한 타입 목록을 처리

    private int currentPage;
    // : 현재 페이지 번호 - 요청된 페이지 번호를 나타냄

    private int totalPages; // 전체 페이지 수를 저장
    private long totalElements; // 전체 요소 수를 저장: DB의 모든 데이터 항목의 총 개수
}