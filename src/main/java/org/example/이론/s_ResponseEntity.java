package org.example.이론;

public class s_ResponseEntity {
    /*
        1. ResponseEntity
        : Spring에서 제공하는 HttpEntity를 상속받는 클래스
        : 클라이언트에게 HTTP 응답을 전송할 때 "상태 코드", "헤더 정보", "응답 본문(Body)"를 모두 포함하여 응답

        1) HTTP 상태 코드
        : 성공, 실패 등 다양한 HTTP 상태 코드를 명시적으로 설정
        2) 헤더 정보
        : Content-Type 등의 응답 헤더 설정
        3) 응답 바디
        : 응답 본문을 보낼 수 있음

        ResponseEntity 클래스 구조
        : ResponseEntity<T>의 제네릭 클래스 구조
        - <T>는 응답 본문의 타입을 지정

        ResponseEntity 상태 코드 반환
        200 OK: 성공
        201 CREATED: 새로운 리소스가 성공적으로 생성

        400 BAD REQUEST: 잘못된 요청
        403 FORBIDDEN: 권한 없음
        404 NOT FOUND: 요청한 리소스를 찾을 수 없음

        500 INTERNAL SERVER ERROR: 서버 내부 오류

        ResponseEntity 반환 방법 (헤더는 생략 가능)
        1. new ResponseEntity<>(응답 본문, 헤더, 상태코드)

        2. ResponseEntity status(HttpStatus.상태코드).header(헤더).body(응답 본문);

        ===== ResponseEntity VS ResponseDto =====
        1. ResponseEntity
        : HTTP 응답 전체를 구성하는 객체
        - 응답에 상태 코드나 헤더 정보를 추가로 포함해야 할 때 사용

        2. ResponseDto
        : 응답 바디에 들어갈 데이터 구조를 정의하는 역할
        - 클라이언트에게 응답할 데이터의 형태를 정의
            내부에 성공 여부나 메세지 등을 포함 가능
    */
}
