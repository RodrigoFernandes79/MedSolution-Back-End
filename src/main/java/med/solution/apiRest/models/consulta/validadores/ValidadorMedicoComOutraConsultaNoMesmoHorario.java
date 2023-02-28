package med.solution.apiRest.models.consulta.validadores;

import med.solution.apiRest.exceptions.ValidacaoException;
import med.solution.apiRest.models.consulta.DadosCadastraisConsulta;
import med.solution.apiRest.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validacaoConsulta(DadosCadastraisConsulta dadosCadastraisConsulta) {
        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository
                .existsByMedicoIdAndDataConsulta(dadosCadastraisConsulta.medicoId(),
                        dadosCadastraisConsulta.dataConsulta());
        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("O medico já possui outra consulta nesse horario.");
        }
    }
}
