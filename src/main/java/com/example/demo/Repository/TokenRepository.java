package com.example.demo.Repository;

import com.example.demo.Model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token save(Token token);
    Optional<Token> findByValueAndDeletedAndExpiryGreaterThan(String token, Boolean deleted, Date expiry);
    Optional<Token> findByValueAndDeleted(String token, Boolean deleted);
}
