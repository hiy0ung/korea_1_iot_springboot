package org.example.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.common.constant.ResponseMessage;
import org.example.springbootdeveloper.dto.request.MenuRequestDto;
import org.example.springbootdeveloper.dto.response.MenuResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.entity.Menu;
import org.example.springbootdeveloper.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public ResponseDto<MenuResponseDto> createMenu(MenuRequestDto dto, String userEmail) {
        MenuResponseDto data = null;

        try {
            Menu menu = Menu.builder()
                    .name(dto.getName())
                    .userEmail(userEmail)
                    .description(dto.getDescription())
                    .price(dto.getPrice())
                    .isAvailable(dto.isAvailable())
                    .category(dto.getCategory())
                    .size(dto.getSize())
                    .build();

            menuRepository.save(menu);

            data = new MenuResponseDto(menu);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<List<MenuResponseDto>> getAllMenus() {
        List<MenuResponseDto> data = null;

        try {
            List<Menu> menus = menuRepository.findAll();

            data = menus.stream()
                    .map(MenuResponseDto::new)
                    // .map((menu) -> new MenuResponseDto(menu))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<MenuResponseDto> getMenuById(Long id) {
        MenuResponseDto data = null;
        Long menuId = id;

        try {
            // Optional
            // : <T> 제네릭 타입의 구조를 선택적으로 받아오는 클래스
            // : 데이터가 있을 수도 있고 없을 수도 있는 경우 사용
            // - 해당하는 데이터가 있을 경우 Optional 안에 데이터 객체를 담고 (찾을 땐 .get() 사용)
            //      , 없을 경우에는 Optional.empty()를 반환

            Optional<Menu> menuOptional = menuRepository.findById(menuId);

            // 옵셔널.isPresent()
            // : Optional 안에 값이 존재하는지 확인
            if (menuOptional.isPresent()) {
                data = new MenuResponseDto(menuOptional.get());
            } else {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }


    public ResponseDto<List<MenuResponseDto>> getMenusByCategory(String category) {
        List<MenuResponseDto> data = null;
        String menuCategory = category;

        try {
            Optional<List<Menu>> optionalMenus = menuRepository.findByCategory(menuCategory);

            if (optionalMenus.isPresent()) {
                List<Menu> menus = optionalMenus.get();
                data = menus.stream()
                        .map((menu) -> new MenuResponseDto(menu))
                        .collect(Collectors.toList());
            } else{
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<MenuResponseDto> updateMenu(Long id, String userEmail, MenuRequestDto dto) {
        MenuResponseDto data = null;
        Long menuId = id;
        String email = userEmail;

        try {
            Optional<Menu> menuOptional = menuRepository.findById(menuId);

            if (menuOptional.isPresent()) {
                Menu menu = Menu.builder()
                        .userEmail(email)
                        .name(dto.getName())
                        .description(dto.getDescription())
                        .price(dto.getPrice())
                        .isAvailable(dto.isAvailable())
                        .category(dto.getCategory())
                        .size(dto.getSize())
                        .build();

                menuRepository.save(menu);
                data = new MenuResponseDto(menu);
            } else {
                ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<Void> deleteMenu(Long id) {
        Long menuId = id;

        try {
            if (!menuRepository.existsById(menuId)) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_MENU);
            }

            menuRepository.deleteById(menuId);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }
}
