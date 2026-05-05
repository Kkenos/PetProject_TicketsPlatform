package TicketsBookingPlatform.Services;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTTokenCreateService {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String secretKey = dotenv.get("JWT_SECRET_KEY");
    private static final long time = Long.parseLong(dotenv.get("JWT_EXPIRATION"));

    public static String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
