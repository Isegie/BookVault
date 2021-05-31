package security.authentication.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtHelper {

    private String base64SecretJwt;
    private Integer tokenValidityTime;

    @Value("${jwt.base64-secret}")
    public void setBase64SecretJwt(String base64SecretJwt) {
        this.base64SecretJwt = base64SecretJwt;
    }

    @Value("${jwt.tokenValiditySeconds}")
    public void setTokenValidityTime(Integer tokenValidityTime) {
        this.tokenValidityTime = tokenValidityTime;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        if (authorities.contains(new SimpleGrantedAuthority("ADMIN_ROLE"))) {
            claims.put("isAdmin", true);
        }
        if (authorities.contains(new SimpleGrantedAuthority("USER_ROLE"))) {
            claims.put("isUser", true);
        }
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidityTime))
                .signWith(SignatureAlgorithm.HS512, base64SecretJwt).compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(base64SecretJwt).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
        } catch (ExpiredJwtException ex) {
            throw ex;
        }
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(base64SecretJwt).parseClaimsJws(token).getBody().getSubject();
    }


}
