package med.solution.apiRest.models.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.solution.apiRest.models.endereco.DadosEndereco;
import med.solution.apiRest.models.medico.enums.Especialidade;

public record DadosCadastraisMedico (
        @NotBlank //nao pode ser vazio e nulo (campos string)
        String nome,
        @Email
        @NotBlank
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotBlank
        String telefone,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        DadosEndereco endereco
) { }
