package med.solution.apiRest.services.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.solution.apiRest.repositories.UsuarioRepository;
import med.solution.apiRest.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component()
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override  //método que intercepta a requisição
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recuperarToken(request);

        if(tokenJWT != null) {
            var usuarioLogado = tokenService.getSubject(tokenJWT);
            var usuario = usuarioRepository.findByLogin(usuarioLogado);

            var autenthication = new UsernamePasswordAuthenticationToken(
                    usuario,
                    null,
                    usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autenthication);
        }

        filterChain.doFilter(request,response); //código para ele seguir com a requisição

    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){

        return authorizationHeader.replace("Bearer",""); //tirar o nome do prefixo Bearer no cabeçalho
        }
        return null;
    }
}
