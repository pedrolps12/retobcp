package com.bcp.reto.retotecnico.security;

import com.bcp.reto.retotecnico.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * The type Token provider.
 */
@Component
public class TokenProvider {

  @Value("${application.jwt.secret}")
  private String secret;

  @Value("${application.jwt.expiration}")
  private String timeExpiration;


  private Key key;

  /**
   * Init.
   */
  @PostConstruct
  public void init() {
    this.key = Keys.hmacShaKeyFor(secret.getBytes());
  }

  /**
   * Generate token string.
   *
   * @param user the user
   * @return the string
   */
  public String generateToken(User user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("role", user.getAuthorities());

    return Jwts.builder()
        .setClaims(claims)
        .setSubject(user.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(
            new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration) * 1000))
        .signWith(key).compact();
  }

  /**
   * Validate token boolean.
   *
   * @param token the token
   * @return the boolean
   */
  public boolean validateToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build()
          .parseClaimsJws(token);
      return !claims.getBody().getExpiration().before(new Date());
    } catch (JwtException | IllegalArgumentException ignored) {
      return false;
    }
  }


}
