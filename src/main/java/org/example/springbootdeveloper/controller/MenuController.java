package org.example.springbootdeveloper.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.common.constant.ApiMappingPattern;
import org.example.springbootdeveloper.dto.request.MenuRequestDto;
import org.example.springbootdeveloper.dto.response.MenuResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.MENU)
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    // MenuController mapping pattern 설정
    public static final String MENU_POST = "/";

    public static final String MENU_GET_MENU_ID = "/{id}";
    public static final String MENU_GET_LIST = "/list";
    public static final String MENU_GET_CATEGORY = "/search/category";

    public static final String MENU_PUT = "/{id}";

    public static final String MENU_DELETE = "/{id}";

    @PostMapping(MENU_POST)
    public ResponseEntity<ResponseDto<MenuResponseDto>> createMenu(
            @Valid @RequestBody MenuRequestDto dto,
            @AuthenticationPrincipal String userEmail) {
        // @Valid
        // : 주로 메서드의 파라미터나 객체 필드의 유효성 검사를 위해 사용
        // : 객체 필드에 설정된 Bean Validation 제약 조건 (@NotNull, @Min, @Size 등)을 검사

        // @AuthenticationPrincipal
        // : Spring Security의 인증 정보를 사용하는 애너테이션
        // : Authentication 객체에서 Principal을 추출하여 컨트롤러 메서드로 주입하는 역할

        // - @Valid는 MenuRequestDto의 객체 필드에 설정된 유효성 제약 조건을 검사
        ResponseDto<MenuResponseDto> result = menuService.createMenu(dto, userEmail);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
               //new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(MENU_GET_LIST)
    public ResponseEntity<ResponseDto<List<MenuResponseDto>>> getAllMenus() {
        ResponseDto<List<MenuResponseDto>> result = menuService.getAllMenus();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(MENU_GET_MENU_ID)
    public ResponseEntity<ResponseDto<MenuResponseDto>> getMenuById(@PathVariable Long id) {
        ResponseDto<MenuResponseDto> result = menuService.getMenuById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(MENU_GET_CATEGORY)
    public ResponseEntity<ResponseDto<List<MenuResponseDto>>> getMenusByCategory(@RequestParam String category) {
        ResponseDto<List<MenuResponseDto>> result = menuService.getMenusByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping(MENU_PUT)
    public ResponseEntity<ResponseDto<MenuResponseDto>> updateMenu(
            @PathVariable Long id,
            @AuthenticationPrincipal String userEmail,
            @Valid @RequestBody MenuRequestDto dto
    ) {
        ResponseDto<MenuResponseDto> result = menuService.updateMenu(id, userEmail, dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping(MENU_DELETE)
    public ResponseEntity<ResponseDto<Void>> deleteMenu(@PathVariable Long id) {
        ResponseDto<Void> result = menuService.deleteMenu(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }






































}
