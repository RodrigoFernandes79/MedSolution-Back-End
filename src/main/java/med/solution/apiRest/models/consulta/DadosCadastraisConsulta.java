package med.solution.apiRest.models.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
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
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataConsulta,
        Especialidade especialidade
        ) {
}
