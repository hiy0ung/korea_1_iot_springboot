package org.example.springbootdeveloper.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

// ENUM: NOTICE(공지사항), FREE(자유게시판), QNA(문의), EVENT(이벤트)
@Entity
public class EnumPractice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
        @Entity 클래스에서 컬렉션 | Enum 타입을 데이터베이스와 매핑하는 JPA 어노테이션
        : @ElementCollection(fetch = FetchType.EAGER)
    */
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Category> categories = new HashSet<>();
}
