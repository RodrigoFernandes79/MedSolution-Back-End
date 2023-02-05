package med.solution.apiRest.controllers;

import jakarta.validation.Valid;
import med.solution.apiRest.models.usuario.DadosAutenticacaoUsuario;
import med.solution.apiRest.models.usuario.Usuario;
import med.solution.apiRest.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity fazerLogin(@RequestBody @Valid DadosAutenticacaoUsuario dadosUsuario) {
        var token = new UsernamePasswordAuthenticationToken(
                dadosUsuario.login(),
                dadosUsuario.senha());


        var authentication = authenticationManager.authenticate(token);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastrarUsuario(
            @RequestBody @Valid DadosAutenticacaoUsuario dadosUsuario,
            UriComponentsBuilder uriComponentsBuilder) {

        var usuario = new Usuario(dadosUsuario);

        var gerarSenhaBcryprografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(gerarSenhaBcryprografada);
        var obj = usuarioRepository.save(usuario);

        var uri = uriComponentsBuilder
                .path("/login/cadastro").
                buildAndExpand(usuario.getId())
                .toUri();

        return ResponseEntity.created(uri).body(obj.getUsername());
    }

}
