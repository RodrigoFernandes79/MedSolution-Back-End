package med.solution.apiRest.repositories;

import med.solution.apiRest.models.medico.Medico;
import med.solution.apiRest.models.medico.enums.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);


    /*selecione da tabela de medicos onde ativo = true e a especialidade for igual a que foi passada
    como parâmetro e que não esteja(fazendo uma subconsulta: selecionando um medico que tenha consulta na data
    passada como parâmetro), ordenando medico aleatoriamente escolhendo limite de 1 medico*/
    @Query("""
            select m from Medico m
            where m.ativo = 1
            and m.especialidade = :especialidade
            and
            m.id not in(
                    select c.medico.id from Consulta c
                    where
                    c.dataConsulta = :dataConsulta
            )
            order by rand()
            limit 1
            """)
    Medico escolherMedicoAleatorioNaLivreData(Especialidade especialidade, LocalDateTime dataConsulta);
}
