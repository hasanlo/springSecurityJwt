package com.hasanlo.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security
        .authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    static final String ROLE_LIST = "roleList";

    private TokenAuthenticationService() {
    }

    static void addAuthentication(HttpServletResponse res, String username, List<String> roleList) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(ROLE_LIST, roleList);
        String jwt = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);
    }

    protected static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {

            Claims body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();


            String user = body.getSubject();
            List<String> roleList = (List<String>) body.get(ROLE_LIST);
            List<GrantedAuthority> authorityList = new ArrayList<>();
            roleList.forEach(o -> authorityList.add(new SimpleGrantedAuthority(o)));
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, authorityList) :
                    null;
        }
        return null;
    }

}
