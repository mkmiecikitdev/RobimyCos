package com.fraki.robimycos.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

/**
 * Created by bambo on 10.10.2017.
 */
public class TokenAuthenticationService {

    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse res, String email) {
        String JWT = Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    static Authentication getAuthentication(HttpServletRequest request) {
        String userEmail = getUserLoginFromHeader(request);
        return userEmail != null ?
                new UsernamePasswordAuthenticationToken(userEmail, null, emptyList()) :
                null;
    }

    public static String getUserLoginFromHeader(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        return getUserLoginFromToken(token);
    }

    public static String getUserLoginFromHeader(HttpServletResponse response) {
        String token = response.getHeader(HEADER_STRING);
        return getUserLoginFromToken(token);
    }

    private static String getUserLoginFromToken(String token) {
        if (token != null) {
            String mail = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            return mail;
        }

        return null;
    }

}
