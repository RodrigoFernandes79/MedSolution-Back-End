package med.solution.apiRest.models.consulta.validadores;

import med.solution.apiRest.models.consulta.DadosCadastraisConsulta;
import med.solution.apiRest.repositories.ConsultaRepository;

public class ValidadorPacienteSemOutraConsultaNoMesmoDia {
    private ConsultaRepository consultaRepository;

    public void validacaoConsulta(DadosCadastraisConsulta dadosCadastraisConsulta) {
        var primeiroHorario = dadosCadastraisConsulta.dataConsulta().withHour(7);
        var ultimoHorario = dadosCadastraisConsulta.dataConsulta().withHour(18);

        var pacientePossuiOutraConsultaNoMesmoDia = consultaRepository
                .existsByPacienteIdAndDataConsultaBetween(
                        dadosCadastraisConsulta.pacienteId(), primeiroHorario, ultimoHorario);
    }
}
