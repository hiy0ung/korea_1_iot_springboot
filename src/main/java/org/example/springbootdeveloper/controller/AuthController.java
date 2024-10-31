package org.example.springbootdeveloper.controller;

// AuthController VS UserController

// 1) AuthoController
// : 인증(Authentication) & 인가(Authorization)와 관련된 모든 로직을 처리하는 컨트롤러
// - 로그인, 로그아웃, 토큰 발급 등 사용자 인증과 관련된 기능을 포함

// 2) UserController
// : 사용자 관리와 관련된 작업을 처리
// - 회원가입, 사용자 정보 수정, 조회, 삭제와 같은 CRUD 작업을 담당

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.common.constant.ApiMappingPattern;
import org.example.springbootdeveloper.dto.request.UserSignInRequestDto;
import org.example.springbootdeveloper.dto.request.UserSignUpRequestDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.dto.response.UserSignInResponseDto;
import org.example.springbootdeveloper.dto.response.UserSignUpResponseDto;
import org.example.springbootdeveloper.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // === AuthController mapping pattern ===
    private static final String POST_SIGN_UP = "/signUp";
    private static final String POST_SIGN_IN = "/signIn";


    // HTTP 메서드: POST
    // URI 경로: /signUp
    // - 회원 가입 로직: username, password, email
    @PostMapping(POST_SIGN_UP)
    public ResponseEntity<ResponseDto<UserSignUpResponseDto>> signUp(@Valid @RequestBody UserSignUpRequestDto dto) {
        try {
            ResponseDto<UserSignUpResponseDto> result = authService.signUp(dto);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // HTTP 메서드: POST
    // URI 경로: /signIn
    // - 로그인 로직: username, password

    // cf) 로그인 시 HTTP 메서드 사용

    // GET VS "POST"
    // : POST 사용을 권장
    // - 로그인 과정에서 사용자 이름과 비밀번호 같은 민감한 데이터를 서버로 전송하기 때문에 (@RequestBody에 담아서 가져와야함)
    // - GET 요청을 URL에 데이터가 노출됨
    //      : 데이터 조회에 사용
    @PostMapping(POST_SIGN_IN)
    public ResponseEntity<ResponseDto<UserSignInResponseDto>> signIn(@Valid @RequestBody UserSignInRequestDto dto) {
        try {
            ResponseDto<UserSignInResponseDto> result = authService.signIn(dto);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
