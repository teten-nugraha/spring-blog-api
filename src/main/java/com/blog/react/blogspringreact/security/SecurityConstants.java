package com.blog.react.blogspringreact.security;

public class SecurityConstants {

    public static final String SIGN_UP_URLS     = "/v1/api/users/**";
    public static final String H2_URL           = "/h2-console/**";
    public static final String SECRET           = "SecretKeyToGEnJWT";
    public static final String TOKEN_PREFIX     = "Bearer ";
    public static final String HEADER_STRING    = "Authorization";
    public static final long EXPIRES_TIME       = 3600000;

}
