package dev.lanfr0.jwtauth.util;

public class Constants {
    public static final String  TOKEN_ENCRYPTION_KEY            = "556B58703273357538782F413F4428472B4B6250655368566D59713374367739";
    public static final Long 	TOKEN_EXPIRATION_TIME           = 1000L * 60 * 60; // 60 minuti
    public static final String  TOKEN_PREFIX                    = "Bearer ";
}