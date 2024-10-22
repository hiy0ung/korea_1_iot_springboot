package org.example.springbootdeveloper.provider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

// JwtProvider
// : JWT를 생성하고 검증하는 역할

/*
    JwtProvider 클래스
    : JWT 토큰을 생성하고 검증하는 역할
    - HS256 암호화 알고리즘을 사용하여 JWT 서명
    - 비밀키는 환경변수(jwt.secret)
    - JWT 만료 기간은 10시간 지정 - 환경변수(36000000)
*/

@Component
public class JwtProvider {

    // 환경 변수에 지정한 비밀키 값과 만료 시간을 가져옴
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    /*
        JWT 생성 메서드
        @param : 사용자 식별자(JWT의 subject로 설정됨)
        @return : 생성된 JWT 토큰 문자열
    */
    public String generateJwtToken(String userId) {
        // 만료 시간: 현재시간 + 10시간
        Date expirationDate = new Date(new Date().getTime() + jwtExpirationMs);

        String jwt = null; // 토큰을 저장할 변수 선언

        try {
            // 비밀키를 이용하여 HMAC-SHA 키 생성
            // - Keys.hmacShaKeyFor()
            // : 환경설정에 있는 비밀키 문자열을 바이트배열로 전달하며 SecretKey 인스턴스를 생성
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

            // JWT 생성
            // : subject(사용자ID), 발행 시간, 만료시간, 서명 알고리즘 포함
            jwt = Jwts.builder()
                    .setSubject(userId) // JWT의 subject 필드에 사용자 ID 설정
                    .setIssuedAt(new Date()) // 토큰 발행 시간 설정
                    .setExpiration(expirationDate) // 만료 시간 설정
                    .signWith(key, SignatureAlgorithm.HS256) // HMAC-SHA256 알고리즘으로 서명
                    .compact(); // 최종적으로 JWT 문자열로 컴팩트하게 변환

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return jwt;
    }

       /*
        JWT 검증 메서드
        @param : jwt를 검증할 JWT 문자열
        @return : JWT가 유효하다면 userId(사용자 식별자)를 반환, 그렇지 않으면 null 반환
    */
    public String validate(String jwt) {
        String userId = null;

        try {
            // 비밀키를 사용하여 JWT 암호화에 사용할 키 생성
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

            // JWT 파싱 및 검증
            // : 서명 검증 후 payload의 subject(userId)를 추출
            userId = Jwts.parserBuilder()
                    .setSigningKey(key) // 서명 검증에 사용할 비밀키 설정
                    .build()
                    .parseClaimsJws(jwt) // JWT를 파싱하고 JWS(Signature 포함) 검증
                    .getBody() // JWT의 payload(body)를 가져옴
                    .getSubject(); // payload의 subject를 반환
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return userId; // JWT가 유효하다면 userId 반환
    }

    public boolean idValidToken(String token) {
        return true;
    }
}
