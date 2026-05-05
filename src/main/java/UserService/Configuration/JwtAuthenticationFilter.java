package UserService.Configuration;

import UserService.Services.JWTTokenValidationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")){ // если че Bearer это префикс перед токеном
            // а также Bearer token - это когда access token передается через заголовок аутентификации http, где значение начинается с "Bearer: "
            //он предотвращает CSRF

            String token = authHeader.substring(7); //длина Bearer с пробелом
            try {
                if(JWTTokenValidationService.isTokenValid(token)){
                    String email = JWTTokenValidationService.extractEmail(token);

                    UsernamePasswordAuthenticationToken authentification =
                            new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());// короче
                    //в параметрах первым указываем по чем находим(может быть никнейм к примеру, если у вас есть проверка,
                    // что ники не могут быть одинакове) пароль, но для JWT он не нужен поэтому null и куда его суём
                    SecurityContextHolder.getContext().setAuthentication(authentification);//ура победа пользователь
                    //будет считаться авторизированным
                }
            }catch (Exception e){

            }
        }
        filterChain.doFilter(request, response);
    }
}
