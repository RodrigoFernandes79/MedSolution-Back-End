package med.solution.apiRest.models.consulta.validadores;

import med.solution.apiRest.exceptions.ValidacaoException;
import med.solution.apiRest.models.consulta.DadosCadastraisConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorTrintaMinutosAntecedencia implements ValidadorAgendamentoDeConsulta {

    public void validacaoConsulta(DadosCadastraisConsulta dadosCadastraisConsulta) {

        var dataConsulta = dadosCadastraisConsulta.dataConsulta();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();
        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta só pode ser marcada com 30 minutos de Antecedência");

        }
    }
}
