package med.solution.apiRest.models.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacaoUsuario(
        @NotBlank(message = "{login.obrigatorio}")
        String login,
        @NotBlank(message = "{senha.obrigatoria}")
        String senha
) {
}
