package com.jwt.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private static final String CLAIMS_SUBJECT = "sub";
    private static final String CLAIMS_CREATED = "iat";
    private static final String CLAIMS_EXPIRATION = "exp";
    private static final String SECRET_KEY = "yourfsdfvwef334239823848$2 c2389r38r yc2389rh9chfchf9fhw9ehfcewhfewhfw9efhey";

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIMS_SUBJECT, userDetails.getUsername());
        claims.put(CLAIMS_CREATED, new Date());
        claims.put(CLAIMS_EXPIRATION, new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)); // token expiration time

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}




// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import org.springframework.stereotype.Component;

// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;

// @Component
// public class JwtTokenProvider {

//     private final String SECRET_KEY = "YourSecretKeyHere"; // Replace with your secret key
//     private final long EXPIRATION_TIME = 864_000_000; // 10 days in milliseconds

//     // Generate a JWT token
//     public String generateToken(String username) {
//         Map<String, Object> claims = new HashMap<>();
//         return createToken(claims, username);
//     }

//     // Create the token
//     private String createToken(Map<String, Object> claims, String subject) {
//         Date now = new Date();
//         Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

//         return Jwts.builder()
//                 .setClaims(claims)
//                 .setSubject(subject)
//                 .setIssuedAt(now)
//                 .setExpiration(expirationDate)
//                 .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//                 .compact();
//     }

//     // Validate a token
//     public boolean validateToken(String token) {
//         try {
//             Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
//             return true;
//         } catch (Exception e) {
//             // Token validation failed
//             return false;
//         }
//     }

//     // Extract the username from a token
//     public String getUsernameFromToken(String token) {
//         Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//         return claims.getSubject();
//     }
// }

