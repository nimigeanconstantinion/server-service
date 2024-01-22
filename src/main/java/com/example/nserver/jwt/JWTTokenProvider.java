package com.example.nserver.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.nserver.utils.Utile.*;


@Component
public class JWTTokenProvider {
    @Value("${token.jwt.secretKey}")
    private String secretKey;

    private String[] getClaimsFromUser(UserDetails user) {

        List<String> authorities=user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        for(GrantedAuthority authority:user.getAuthorities()){
                authorities.add(authority.getAuthority());
        }
        return authorities.toArray(new String[0]);

    }

    public String generateJWTToken(UserDetails user) {
        String[] claims=getClaimsFromUser(user);
        return JWT.create().withIssuer(MY_CODE).withAudience(ADMINISTRATION)
                .withIssuedAt(new Date()).withSubject(user.getUsername())
                .withArrayClaim(AUTHORITIES,claims).withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(secretKey.getBytes(StandardCharsets.UTF_8)));

    }

    private JWTVerifier getJWTVerifier(String token) {
        JWTVerifier jwtVerifier;
        try{
            Algorithm  algorithm=Algorithm.HMAC512(secretKey);
            jwtVerifier=JWT.require(algorithm).withIssuer(MY_CODE).build();
        }catch (JWTVerificationException e){
            throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
        }
        return jwtVerifier;
    }


    private String[] getClaimsFromToken(String token) {
        JWTVerifier verifier=getJWTVerifier(token);
        return verifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
    }

    public List<GrantedAuthority> getAuthorities(String token) {
            String[] claims=getClaimsFromToken(token);
            return Arrays.stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expiration = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    public boolean isTokenValid(String username,String token) {

        System.out.println("User :"+username);
        System.out.println("token ="+token);
        JWTVerifier verifier = getJWTVerifier(token);
        System.out.println("este expirat? "+isTokenExpired(verifier,token));

        return StringUtils.isNotEmpty(username) && !isTokenExpired(verifier, token) ;
    }

    public String getSubject(String token) {
        JWTVerifier verifier = getJWTVerifier(token);
        return verifier.verify(token).getSubject();
    }

    public Authentication getAuthentication(String username, Collection<GrantedAuthority> authorities, HttpServletRequest request){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username,null,authorities);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }

}
