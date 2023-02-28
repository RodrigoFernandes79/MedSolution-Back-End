package med.solution.apiRest.models.consulta.validadores;

import med.solution.apiRest.exceptions.ValidacaoException;
import med.solution.apiRest.models.consulta.DadosCadastraisConsulta;
import med.solution.apiRest.repositories.MedicoRepository;

public class ValidadorMedicoAtivo {
    private MedicoRepository medicoRepository;

    public void validacaoConsulta(DadosCadastraisConsulta dadosCadastraisConsulta) {
        //escolha do medico opcional
        if (dadosCadastraisConsulta.medicoId() == null) {
            return;
        }
        var medicoEstaAtivo = medicoRepository.findByAtivoById(dadosCadastraisConsulta.medicoId());
        if(!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico inativo.");
        }
    }
}
