package org.example.springbootdeveloper.repository;

import org.example.springbootdeveloper.dto.response.MenuResponseDto;
import org.example.springbootdeveloper.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<List<Menu>> findByCategory(String category);
}
