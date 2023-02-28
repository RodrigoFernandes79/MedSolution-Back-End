package med.solution.apiRest.models.consulta.validadores;

import med.solution.apiRest.models.consulta.DadosCadastraisConsulta;
import med.solution.apiRest.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoMesmoDia implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validacaoConsulta(DadosCadastraisConsulta dadosCadastraisConsulta) {
        var primeiroHorario = dadosCadastraisConsulta.dataConsulta().withHour(7);
        var ultimoHorario = dadosCadastraisConsulta.dataConsulta().withHour(18);

        var pacientePossuiOutraConsultaNoMesmoDia = consultaRepository
                .existsByPacienteIdAndDataConsultaBetween(
                        dadosCadastraisConsulta.pacienteId(), primeiroHorario, ultimoHorario);
    }
}
