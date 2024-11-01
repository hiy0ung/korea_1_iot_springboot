package org.example.이론;

public class t_response_constructure {
    // vsc F_Response_Constructure 이랑 똑같음
    /*
    ! ===== 1. Spring Boot의 응답 구조 =====

    ResponseEntity<ResponseDto<실제응답데이터>>

    1) ResponseEntity 구조
      - HttpStatus: 응답의 성공, 실패 상태를 나타냄 (EX. 200 OK, 404 NOT FOUND, 500 INTERNER SERVER ERROR)
      - HttpHeaders: 응답에 포함할 추가 정보 (EX. Content-Type, Authorization 등)
      - Body(본문): 클라이언트에게 전달할 실제 데이터
                    , 객체나 문자열 또는 DTO 등 다양한 데이터 타입 설정이 가능
                    >> ResponseDto 형식의 "구조화된 본문을 전달(웅답 데이터를 감싸는 형태의 응답 구조)"

    2) ResponseDto 구조
      - Result(boolean): 성공, 실패에 대한 boolean 타입의 데이터
      - message(string): 성공, 실패에 대한 구체적인 메세지 전달
      - data(D): 클라이언트에게 전달할 실제 데이터

    ! ===== 2. 프론트엔드의 Axios 응답 처리 =====

    Response.data.data

    1) Response 구조
      : axios가 HTTP 응답을 처리한 후 반환하는 객체
      - response.status: HTTP 상태 코드 (200, 204)
      - response.statusText: 상태 텍스트("OK", "Not Found" 등)
      - response.headers: 응답 헤더 정보
      - response.config: 응답 설정 정보
      - response.data: 서버에서 전송한 응답 데이터

    2) response.data 구조
      : 백엔드의 전체 ResponseDto 객체를 가리킴
      - 실제 데이터를 data 필드로 감싸고 있음
*/
}
