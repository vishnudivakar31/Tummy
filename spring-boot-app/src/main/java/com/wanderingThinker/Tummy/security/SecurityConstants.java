package com.wanderingThinker.Tummy.security;

public class SecurityConstants {
    public static final String SECRET = "Tummy30User07JWT2019";
    public static final long EXPIRATION_TIME = 43200000; // 12 Hrs
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
}
