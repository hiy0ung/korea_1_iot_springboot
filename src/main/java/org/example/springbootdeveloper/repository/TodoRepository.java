package org.example.springbootdeveloper.repository;

import org.example.springbootdeveloper.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
