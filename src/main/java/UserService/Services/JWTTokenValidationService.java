package TicketsBookingPlatform.Services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.github.cdimascio.dotenv.Dotenv;
import java.security.Key;
import java.util.Date;

public class JWTTokenValidationService {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String secretKey = dotenv.get("JWT_SECRET_KEY");
    private static final Key signingKey = Keys.hmacShaKeyFor(secretKey.getBytes());


    public static String extractEmail(String token) { //извлечет Email из этой длинной строки
        return parseToken(token).getSubject();
    }


    public static boolean isTokenExpired(String token) { //Чекает истек ли токен по времени
        Date expiration = parseToken(token).getExpiration();
        return expiration.before(new Date());
    }


    private static Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | MalformedJwtException |
                 SignatureException e) { //Истекший | неправильный | неправильно написанный

            throw new RuntimeException("Invalid JWT token", e);
        }
    }

    // Полная валидация: токен действителен и соответствует указанному email
    public static boolean validateToken(String token, String email) {
        try {
            String tokenEmail = extractEmail(token);
            return tokenEmail.equals(email) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean isTokenValid(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}


//Короче, если интересно, вот так выглядит этот токен:
// eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
// .eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0
// .KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30
// Это всё одна строка!!!