package org.example.springbootdeveloper.repository;

import org.example.springbootdeveloper.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // User 타입의 엔티티 필드를 선택적으로 가져올 수 있는 타입 정의
    Optional<User> findByEmail(String email);
}
