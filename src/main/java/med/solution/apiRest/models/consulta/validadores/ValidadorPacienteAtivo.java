package med.solution.apiRest.models.consulta.validadores;

import med.solution.apiRest.exceptions.ValidacaoException;
import med.solution.apiRest.models.consulta.DadosCadastraisConsulta;
import med.solution.apiRest.repositories.PacienteRepository;

public class ValidadorPacienteAtivo {
    private PacienteRepository pacienteRepository;

    public void validacaoConsulta(DadosCadastraisConsulta dadosCadastraisConsulta) {
        //escolha do medico opcional
        if (dadosCadastraisConsulta.pacienteId() == null) {
            return;
        }
        var pacienteEstaAtivo = pacienteRepository.findByAtivoById(dadosCadastraisConsulta.pacienteId());
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente inativo.");
        }
    }
}
