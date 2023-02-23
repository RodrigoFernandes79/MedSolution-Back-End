package med.solution.apiRest.models.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import med.solution.apiRest.models.medico.Medico;
import med.solution.apiRest.models.paciente.Paciente;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
        Long id,
        Paciente paciente,
        Medico medico,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataConsulta
) {
    public DadosDetalhamentoConsulta(Long id, Paciente paciente, Medico medico, LocalDateTime dataConsulta) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.dataConsulta = dataConsulta;
    }

}
