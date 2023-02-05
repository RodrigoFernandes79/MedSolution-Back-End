package med.solution.apiRest.services.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component()
public class JwtAuthFilter extends OncePerRequestFilter {
    @Override  //método que intercepta a requisição
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {


        filterChain.doFilter(request,response); //código para ele seguir com a requisição

    }
}
