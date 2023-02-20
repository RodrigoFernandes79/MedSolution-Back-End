package med.solution.apiRest.repositories;

import med.solution.apiRest.models.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
