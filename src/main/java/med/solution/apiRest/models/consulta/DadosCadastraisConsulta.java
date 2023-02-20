package med.solution.apiRest.models.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.solution.apiRest.models.medico.enums.Especialidade;

import java.time.LocalDateTime;

public record DadosCadastraisConsulta(
        @NotNull
        Long pacienteId,
        Long medicoId,
        @NotNull
        @Future
        LocalDateTime dataConsulta,
        Especialidade especialidade
        ) {
}
