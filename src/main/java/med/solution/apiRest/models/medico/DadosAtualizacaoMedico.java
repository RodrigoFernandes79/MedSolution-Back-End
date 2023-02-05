package med.solution.apiRest.models.medico;

import jakarta.validation.constraints.NotNull;
import med.solution.apiRest.models.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) { }

