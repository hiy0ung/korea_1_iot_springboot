package org.example.springbootdeveloper.repository;

import org.example.springbootdeveloper.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
