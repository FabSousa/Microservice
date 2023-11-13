package br.com.fiap.msprodutos.config;

import br.com.fiap.msprodutos.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getToken(User user){
        try {
            var algoritimo = Algorithm.HMAC256("123456");

            return JWT.create().withIssuer("MS Produtos")
                    .withSubject(user.getEmail())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo);
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token JWT ", exception);
        }
    }
}
