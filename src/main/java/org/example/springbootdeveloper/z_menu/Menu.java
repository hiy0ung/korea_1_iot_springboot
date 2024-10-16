package org.example.springbootdeveloper.z_menu;

import jakarta.persistence.*;

@Entity
@Table(name="menus")
public class Menu {
    @Id // PK 값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 기본 키 값 자동 생성 (AUTO_INCREMENT)
    private Long menu_id;

    private String menu_name;
    private String menu_price;

    protected Menu() {}

    public Menu(String menu_name, String menu_price) {
        this.menu_name = menu_name;
        this.menu_price = menu_price;
    }
}
