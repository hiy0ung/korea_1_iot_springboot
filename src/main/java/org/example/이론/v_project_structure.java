package org.example.이론;

public class v_project_structure {

    // Spring Boot 프로젝트에서 각 폴더와 파일의 역할 & 구조의 운용 방식 //

    /*
        1. common
            프로젝트에서 공통으로 사용되는 유틸리티 클래스, 상수, 예외 처리 클래스 등을 담음
            - 코드의 재사용성을 높이고 코드 중복을 줄임
            EX) DateUtils, Constants, CustomException과 같은 클래스들이 포함

        2. config
            Spring Boot 프로젝트의 설정 관련 클래스를 포함
            & 각종 설정을 정의하여 애플리케이션의 동작 방식을 제어
            EX) WebSecurityConfig는 Spring Security 설정을 담당하여 애플리케이션의 인증 및 권한 부여 방식을 설정

        3. controller
            - 클라이언트의 요청을 받아 처리하는 컨트롤러 클래스들을 포함
            - Spring의 @RestController 혹은 @Controller 애노테이션이 작성된 클래스들이 위치
                , 클라이언트가 보낸 요청을 처리하고 적절한 응답을 반환
            EX) UserController가 있다면 /user와 관련된 요청을 처리
                 , 각 요청 메서드(GET, POST, PUT, DELETE)에 맞춰 메소드를 제공 (매핑)

        4. dto (Data Transfer Object)
            - 데이터 전송 객체를 포함, 클라이언트와 서버 간의 데이터 교환을 위한 객체를 정의
            - Request와 Response 폴더로 나뉨
                , 요청 데이터를 캡슐화하여 전달하거나 응답 데이터를 구조화하여 반환하는 데 사용
            EX) SignInRequestDto & SignInResponseDto
                : 로그인 요청과 응답 데이터를 각각 캡슐화하는 역할

        5. entity
            - 데이터베이스 테이블과 매핑되는 클래스들을 포함
            EX) User 엔티티: 사용자 정보 테이블과 매핑되며, 해당 테이블의 필드를 정의

        6. filter
            - 요청이나 응답을 가로채는 필터 클래스들을 포함
            - 주로 인증, 로깅, CORS 처리 등을 위해 사용
            EX) JwtAuthenticationFilter: JWT 토큰을 검증하여 인증된 사용자인지 확인

        7. provider
            - 서비스에 필요한 특정 기능을 제공하는 클래스들을 포함하며
                , 주로 인증이나 JWT 토큰 발급 및 검증을 담당
            EX) JwtTokenProvider: JWT 토큰을 생성하고, 유효성을 검증
                , 토큰에서 사용자 정보를 추출하는 메서드를 제공

        8. repository
            - 데이터베이스와의 CRUD 작업을 담당하는 인터페이스들이 위치
            - 보통 JpaRepository를 확장하여 사용하며, 데이터베이스와 직접 통신
            EX) UserRepository: User 엔티티와 연관된 데이터를 CRUD 하는 메서드를 정의

        9. service
            - 비즈니스 로직을 담당하는 서비스 클래스들이 위치
            - 보통 @Service 애노테이션이 작성되며, 컨트롤러에서 전달받은 요청을 처리하고, 리포지토리와 연동하여 데이터를 관리
            EX) UserService: 사용자와 관련된 주요 기능(회원가입, 로그인, 정보 조회 등)을 처리하는 비즈니스 로직을 포함
    */
}
