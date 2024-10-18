package org.example.이론;

public class m_api {
    /*
        ===== 1. API =====
        : Application Programming Interface
        : 응용프로그램(Application)이 서로 통신할 수 있도록 해주는 인터페이스
        : 소프트웨어 간의 중재자 역할
        >> 서로 다른 소프트웨어 시스템이 데이터를 주고받거나 기능을 호출하는 방식

        1) API 목적
        : 재사용성 - 이미 작성된 기능을 여러 프로그램에서 재사용 가능
        : 유지보수성 - 코드 변경이 API에 의해 중앙에서 관리
                     , 다른 기능의 영향을 최소화

        2) API의 구성요소
        - 엔드포인트(Endpoint)
            : API가 연결되는 특정 URL 주소
        - 메서드(Method)
            : API가 수행하는 작업, HTTP 메서드를 주로 사용
            : GET, POST, PUT, DELETE

        - Request
            : 클라이언트가 API로 보내는 요청
        - Response
            : 서버가 API 요청에 대한 응답으로 보내는 데이터

        ===== 2. HTTP (API) =====
        : 웹 환경에서 정보를 주고 받기 위한 프로토콜
        : HTTP 라는 통신 규칙으로 소통하는 API

        cf) 웹 개발 시 반드시 HTTP가 필요한 것은 아님
            : IoT 애플리케이션의 API

        cf) 프록시(Proxy)
        : 대리 서버
        : 클라이언트(사용자)와 실제 서버 간의 중재자, 요청과 응답을 전달
        - IP 주소 숨김: 실제 IP 주소를 숨기고, 프록시 서버의 IP를 사용하여 서버와 통신
        - 보안 강화: 서버와 클라이언트 간의 직접 통신 X
        - 접근 제어: 특정 웹 사이터나 데이터에 대한 접근을 차단하거나 제한

        ===== 3. REST API =====
        : REST (Representational State Transfer)
        : HTTP 프로토콜을 기반으로 클라이언트와 서버 간의 상호작용을 규정하는 아키텍처 스타일

        cf) 아키텍처 스타일
            : 네트워크 자원을 정의하고 처리하는 방법 전반

        : "자원"의 "표현"으로 "상태"를 전달하는 아키텍처

        REST의 6가지 원칙
        1) 클라이언트-서버 구조: 클라이언트와 서버가 명확하게 분리
        2) 무상태성: 서버는 클라이언트의 상태 저장 X, 요청은 독립적으로 처리
        3) 캐시 가능성: 응답은 캐시될 수 있어야 함
        4) 계층화 시스템: 클라이언트는 여러 계층의 중개 서버와 통신 가능
        5) 일관된 인터페이스*: API가 일관된 방식으로 설계되어야 함
        6) 코드 온 디멘드: (선택적)

        REST API 설계의 구성
        - URI로 자원을 표현

        cf) URI가 URL을 포함
        - URI는 통합 자원 식별자로 주소에 식별자가 있으면 URI
        - URL은 리소스(자원)의 주소를 나타냄

        자원: URI로 자원을 표현
        행위: 자원에 대한 행위는 HTTP Method로 표현
        표현: 응답 자원의 상태를 JSON, XML 등의 형태로 나타냄

        ===== 4. RESTful API =====
        : URI로 자원을 표현하는데 집중!하고, 자원의 상태(행위)에 대한 정의는
          , HTTP Method로 하는 것이 REST하게 API를 설계하는 규칙

        - URI로 자원(리소스)를 표현
        - 자원에 대한 행위는 HTTP Method(GET, POST, PUT, DELETE)로 표현

        REST API 사용 방법
        1. URL에는 동사를 쓰지 않고, 자원을 표시
        EX) 상품 리소스에서 상품 코드가 1004인 상품의 정보를 가져오는 URL
            : /products/1004 (O)
            :/get-product?product_id=1004 (X)

        createProduct (동사)
        createdProduct (명사)

        2. 동사는 HTTP 메서드 사용 (CRUD - 크루드)
        POST: 만들고 (CREATE)
        GET: 읽고(READ)
        PUT: 업데이트하고(UPDATE)
        DELETE: 삭제하고(DELETE)

        cf) PUT VS PATCH
        1) PUT
        : 전체 리소스 수정, 전체를 대체하는 방식으로 동작
        >> 기존 리소스 전체를 새로운 값으로 덮어쓰는 개념
        >> 리소스 일부만 제공, 제공되지 않은 정보는 null 또는 기본값으로 설정
        >> 멱등성을 가짐 (Idempotent)
            : 여러 번 결과를 보내도 결과가 동일한 경우
            : 연산을 여러 번 적용하더라도 결과가 달라지지 않는 성질

       2) PATCH
       : 리소스의 일부만 수정하는 데 사용
       : 전체 리소스를 보내는 대신 수정할 부분만 전송
       >> 기존 리소스 데이터는 유지, 제공된 부분만 업데이트 됨
       >> 멱등성을 가지지않음
            : 여러 번 보내면 동일한 결과를 보장하지 X

        cf) RESTful API 추가적인 특징
        - /(슬래시)는 계층 관계를 나타냄
            : /api/students/1/lecture/:lecture
        - 밑줄 대신 하이픈을 사용 권장
        - 자원(리소스)의 종류에 따라 단수, 복수를 구분해서 사용을 권장

        HTTP 추가 구조: 헤더(Header), 바디(Body, 본문)
        1) 헤더(Header)
        : 요청이나 응답에 대한 추가 정보
        EX) 메타데이터: 데이터의 타입, 보안 등을 명시
                Content-Type: text/html
                Content-Type: application/json

        2) 바디(Body, 본문)
        : 요청이나 응답의 실제 데이터가 포함
    */
}
