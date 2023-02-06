package med.solution.apiRest.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.solution.apiRest.models.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${security.jwt.chave-de-assinatura}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {  // método extraido da documentaçao https://github.com/auth0/java-jwt
           
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API MedSolution")
                    .withSubject(usuario.getLogin())
                    .withClaim("id",usuario.getId()) //opcional
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
           throw new RuntimeException("Erro ao gerar Token JWT", exception);
        }
    }

    private Instant dataExpiracao() {

        return LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT) {
        //método para retornar o usuário logado caso o token estiver Válido. https://github.com/auth0/java-jwt
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    // specify an specific claim validations
                    .withIssuer("API MedSolution")
                    // reusable verifier instance
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token Inválido ou expirado.");
        }
    }
}
