package org.example.springbootdeveloper.z_menu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example/menu")
public class MenuController {

    // Service 객체를 주입 받음
    private final MenuService menuService;

    // 생성자 주입
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
}
