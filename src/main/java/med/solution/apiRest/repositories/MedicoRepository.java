package med.solution.apiRest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import med.solution.apiRest.models.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{

}
