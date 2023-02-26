package med.solution.apiRest.models.consulta;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
        @NotNull
        Long consultaId,
        @NotNull
        MotivoCancelamento motivo
) {
}
