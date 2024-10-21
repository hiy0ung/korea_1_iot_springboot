package org.example.springbootdeveloper.entity;

// 회원(User) 엔티티
// id: bigint 기본키, 자동증가 (일련번호)
// email: varchar(255), not null
// password: varchar(255), not null
// created_at: datetime, not null
// updated_at: datetime, not null

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "users") // DB 테이블은 복수, Entity 클래스는 단수로 이름 짓기
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
// UserDetails 인터페이스를 상속받아 인증 객체로 사용
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Builder
    public User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
    }

    // cf) UserDetails 인터페이스
    // : 사용자의 인증 정보를 담고 있는 객체를 정의하는 인터페이스
    // : Spring Security가 사용자의 권한 및 인증 관련 정보를 확인할 때 사용

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자가 가지고 있는 권한(roles) 목록을 반환
        // EX) 일반 사용자, 관리자, 임원 등의 권한이 있을 경우
        //      가지고 있는 권한을 모두 반환
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        // 사용자의 id를 반환(고유한 값: pk | unique)
        return email;
    }

    @Override
    public String getPassword() {
        // 사용자의 패스워드 반환
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 여부 반환
        return true; // 만료되지 않음
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠근 여부 반환
        return true; // 잠금되지 않았음
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 패스워드의 만료 여부를 반환
        return true; // 만료되지 않음
    }

    @Override
    public boolean isEnabled() {
        // 계정 사용 여부 반환
        return true; // 사용 가능
    }
}
