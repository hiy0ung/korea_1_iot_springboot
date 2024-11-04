package org.example.이론;

public class w_스프링시큐리티_보충 {
    /*
        Spring Boot Security: "자물쇠처럼 사이트의 정보를 보호"

        1. Spring Security가 하는 일
            - "로그인과 같은 자물쇠를 만드는 도구"

            1) 인증 (Authentication)
                : 누가 들어오려고 하는지 확인하는 과정

            2) 인가, 권한 (Authorization)
                : 들어오려고 하는 사람이 무슨 일을 할 수 있는지 결정
                    - 집 안에 들어온 사람이 어떤 방까지 들어갈 수 있는지 허락하는 것

        2. Spring Security의 동작 방식
            - Filter(필터)
                : 모든 요청(Request)을 검사
                - EX) 놀이공원 입구에서 티켓을 검사하는 사람

            - Authentication Manager
                : 위의 필터를 통과하면 Spring Security의 "인증 관리자"가 ID와 비밀번호가 맞는지 확인
                - EX) 티켓 인증이 완료되면 "인증 토큰"을 발급

            - Security Context
                : 입장한 사람의 정보를 저장
                - EX) 놀이공원에서 입장 고객에 대한 정보를 저장, 놀이기구 이용 제한을 확인


        ================ WebSecurityConfig ================
        1. corsFilter 메서드
            CORS 정책 적용: 외부 도메인에서의 API 접근을 허용
            모든 출처 허용: 모든 도메인에서의 요청을 허용하도록 설정
            모든 HTTP 메서드 허용: GET, POST, PUT, DELETE 등의 모든 메서드를 허용
            필터 반환: 설정된 CORS 필터를 반환하여 다른 요청에서도 적용되도록 함

        2. filterChain 메서드
            CSRF 보호 비활성화: REST API 특성상 불필요한 CSRF 보호를 비활성화
            CORS 설정 활성화: 앞서 정의한 CORS 필터 설정을 활성화
            특정 경로 접근 허용: 인증 없이 접근 가능한 경로를 지정
            JWT 필터 추가: 인증을 위해 JWT 검증 필터를 추가

        3. authenticationManager 메서드
            인증 관리 기능 제공: 사용자 인증을 처리할 수 있는 AuthenticationManager를 생성
            DaoAuthenticationProvider 사용: DB에서 사용자를 인증하는 프로바이더를 사용
            BCrypt 비밀번호 암호화: 비밀번호 검증 시 암호화를 통해 비교
            ProviderManager 반환: 다중 인증 프로바이더 관리자를 반환하여 인증 요청을 처리

        4. bCryptpasswordEncoder 메서드
            비밀번호 암호화: 비밀번호를 암호화하는 인코더를 제공
            BCrypt 사용: 단방향 암호화 알고리즘인 BCrypt를 사용
            보안 강화: 암호화된 비밀번호는 복호화할 수 없으므로 보안성이 높음
            Bean 등록: Spring에서 관리하는 Bean으로 등록되어 필요 시 자동으로 주입


        ================ JwtProvider ================
        1. generateJwtToken
            사용자 ID를 포함하는 JWT를 생성하여 반환
            현재 시간과 만료 시간 설정 및 HMAC-SHA256 서명 포함
            사용자 인증에 활용되는 토큰

        2. generateEmailValidToken
            이메일 검증용으로 5분 동안 유효한 짧은 JWT를 생성
            사용자 이름을 클레임으로 저장하며, 짧은 만료 시간으로 설정
            이메일 인증 기능에 사용

        3. removeBearer
            Bearer 접두사를 제거하여 JWT만 반환
            Authorization 헤더로부터 Bearer를 제거하는 데 사용

        4. getUserIdFromJwt
            JWT에서 사용자 ID 클레임을 추출해 반환
            인증된 사용자를 특정하기 위한 정보로 사용

        5. isValidToken
            토큰의 유효성을 검사하여 true 또는 false를 반환
            만료 여부와 올바른 형식인지 검증하는 역할 담당

        6. getClaims
            토큰의 클레임 정보를 추출하여 반환
            JWT 본문에서 사용자 정보와 같은 세부 정보를 읽어옴

        ================ JwtAuthenticationFilter ================
        1. doFilterInternal
            Authorization 헤더에서 JWT 토큰을 추출하여 검증
            유효하지 않은 토큰은 필터 체인 다음 단계로 넘김
            유효한 토큰일 경우 userId를 추출하여 인증 설정 메서드로 전달

        2. setAuthenticationContext(인증 설정 메서드)
            userId를 포함한 인증 토큰을 생성
            요청에 대한 세부 정보를 인증 토큰에 설정
            SecurityContextHolder에 사용자 인증 정보를 설정
            , 이후의 요청에서 인증된 사용자로 인식되도록 함
    */
}
