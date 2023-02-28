package med.solution.apiRest.models.consulta.validadores;

import med.solution.apiRest.exceptions.ValidacaoException;
import med.solution.apiRest.models.consulta.DadosCadastraisConsulta;

import java.time.DayOfWeek;

public class ValidadorHorarioDeFuncionamentoClinica {

    public void validacaoConsulta(DadosCadastraisConsulta dadosCadastraisConsulta) {

        var dataConsulta = dadosCadastraisConsulta.dataConsulta();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoFechamentoDaClinica = dataConsulta.getHour() > 18;
        if (domingo || antesDaAberturaDaClinica || depoisDoFechamentoDaClinica) {
            throw new ValidacaoException("Consulta fora do Horário de Funcionamento da Clínica");
        }
    }
}
