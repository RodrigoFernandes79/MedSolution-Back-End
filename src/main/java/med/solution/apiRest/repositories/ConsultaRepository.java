package med.solution.apiRest.repositories;

import med.solution.apiRest.models.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByMedicoIdAndDataConsulta(Long medicoId, LocalDateTime dataConsulta);

    boolean existsByPacienteIdAndDataConsultaBetween(Long pacienteId, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
