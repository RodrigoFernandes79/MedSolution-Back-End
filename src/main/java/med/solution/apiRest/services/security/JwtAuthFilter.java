package med.solution.apiRest.services.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.solution.apiRest.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component()
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Override  //método que intercepta a requisição
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recuperarToken(request);

        var usuarioLogado = tokenService.getSubject(tokenJWT);

        filterChain.doFilter(request,response); //código para ele seguir com a requisição

    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader == null){
            throw new RuntimeException("O Token JWT não foi enviado no cabeçalho Authorization.");
        }
        return authorizationHeader.replace("Bearer",""); //tirar o nome do prefixo Bearer no cabeçalho
    }
}
