package com.twlee.bank.common.ui.resolver;

import com.twlee.bank.common.annotation.AuthenticateAccess;
import com.twlee.bank.common.application.JwtTokenProvider;
import com.twlee.bank.common.application.dto.AnonymousMember;
import com.twlee.bank.common.application.dto.AuthMember;
import com.twlee.bank.common.exception.AuthenticationException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthenticateArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticateArgumentResolver(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticateAccess.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        AuthenticateAccess authenticateAccess = parameter.getParameterAnnotation(AuthenticateAccess.class);
        if (authenticateAccess != null && !authenticateAccess.required()) {
            return new AnonymousMember("anonymous@tinybank.com");
        }
        String authorization = webRequest.getHeader("Authorization");
        if (!"bearer".equalsIgnoreCase(authorization.split(" ")[0])) {
            throw new AuthenticationException();
        }
        String token = authorization.split(" ")[1];
        if (jwtTokenProvider.validateToken(token)) {
            return new AuthMember(jwtTokenProvider.getPrincipal(token));
        }
        throw new AuthenticationException();
    }
}
