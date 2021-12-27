package com.bcp.reto.retotecnico.security;

import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

@Getter
public enum EnumEndpoints {

    ACTUATOR_ENDPOINT("/actuator/*", HttpMethod.GET),
    LOGIN_SERVER_ENDPOINT("/v1/retotecnico/authenticate", HttpMethod.POST);

    private final PathPattern pattern;
    private final HttpMethod httpMethod;

    EnumEndpoints(String path, HttpMethod httpMethod) {
        this.pattern = new PathPatternParser().parse(path);
        this.httpMethod = httpMethod;
    }
}
