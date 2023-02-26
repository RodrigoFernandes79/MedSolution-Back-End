package med.solution.apiRest.models.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
        Long id,
        Long pacienteId,
        Long medicoId,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataConsulta
) {
    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(
                consulta.getId(),
                consulta.getPaciente().getId(),
                consulta.getMedico().getId(),
                consulta.getDataConsulta()
        );
    }
}
