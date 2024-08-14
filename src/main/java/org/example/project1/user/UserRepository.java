package org.example.project1.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    Optional<SiteUser> findByEmail(String email);
    Optional<SiteUser> findByUsername(String username); // 사용자 이름으로 사용자 조회
    SiteUser findByToken(String token);
}