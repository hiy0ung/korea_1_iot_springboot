package org.example.springbootdeveloper.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB의 auto_increment 설정
    private Long id;

    @NotBlank
    private String task;

    @Column(nullable = false)
    private boolean status;

    @Builder
    public Todo(Long id, String task, boolean status) {
        this.id = id;
        this.task = task;
        this.status = status;
    }
}
/*
    1. jakarta.validation 패키지
        : 자바 애플리케이션의 비즈니스 로직 수준

        @NotBlank
            : task 필드에 공백이 아닌 값이 포함되도록 강제
            > ''빈 문자열, ' '공백 문자만 있는 문자열이 들어갈 경우 유효성 검사에서 오류 발생
                +) null 값도 방지

        @NotNull
            : null 값만을 방지 (null 값이 저장되지 않도록 함)

    2. @Column(nullable = false)
        : 데이터베이스 수준
        > DB에서 null 금지
        (DB에 default 값이 지정되어 있더라도 nullable = false가 없는 경우
            삽입 시 Null 값 삽입을 100% 방지하는 것은 아님)

        cf) DB의 default 속성은 새로운 레코드 생성 시에만 적용!
*/
