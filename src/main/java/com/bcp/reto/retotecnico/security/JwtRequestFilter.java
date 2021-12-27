package com.bcp.reto.retotecnico.security;

import com.bcp.reto.retotecnico.exception.UnauthorizedException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


/**
 * The type Jwt request filter.
 */
@Component
public class JwtRequestFilter implements WebFilter {

  private static final String HEADER_AUTO = "Authorization";

  @Autowired
  private TokenProvider tokenProvider;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    if (isExcludedEnpoint(exchange.getRequest())) {
      return chain.filter(exchange);
    }
    return validateTokenJWT(exchange, chain);
  }

  private Mono<Void> validateTokenJWT(ServerWebExchange exchange, WebFilterChain chain) {

    List<String> headerAuth = exchange.getRequest().getHeaders().get(HEADER_AUTO);

    if (headerAuth == null) {
      return Mono.error(new Exception("Not found header"));
    }
    var token = headerAuth.get(0).substring(7);
    if (StringUtils.hasText(token) && this.tokenProvider.validateToken(token)) {
      return chain.filter(exchange);
    } else {
      return Mono.error(new UnauthorizedException("Token incorrect"));
    }
  }

  private boolean isExcludedEnpoint(ServerHttpRequest request) {
    PathContainer path = request.getPath().pathWithinApplication();

    Optional<EnumEndpoints> optionalEndpoint = Arrays.stream(EnumEndpoints.values())
        .filter(endpoint -> endpoint.getPattern().matches(path)
            && endpoint.getHttpMethod() == request.getMethod())
        .findAny();

    return optionalEndpoint.isPresent();
  }


}
