package med.solution.apiRest.models.paciente;

import jakarta.validation.constraints.NotNull;
import med.solution.apiRest.models.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,

        DadosEndereco endereco

) {
}
