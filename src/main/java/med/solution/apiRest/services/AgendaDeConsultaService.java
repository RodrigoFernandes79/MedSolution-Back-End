package med.solution.apiRest.services;

import med.solution.apiRest.exceptions.ValidacaoException;
import med.solution.apiRest.models.consulta.Consulta;
import med.solution.apiRest.models.consulta.DadosCadastraisConsulta;
import med.solution.apiRest.models.medico.Medico;
import med.solution.apiRest.models.paciente.Paciente;
import med.solution.apiRest.repositories.ConsultaRepository;
import med.solution.apiRest.repositories.MedicoRepository;
import med.solution.apiRest.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    public void agendarConsulta(DadosCadastraisConsulta dadosCadastraisConsulta) {
        Optional<Paciente> paciente = pacienteRepository.findById(dadosCadastraisConsulta.pacienteId());
        if (paciente.isEmpty()) {
            throw new ValidacaoException("Paciente não cadastrado");
        }
        Medico medico = escolherMedicoDisponivel(dadosCadastraisConsulta);
        if (medico == null && dadosCadastraisConsulta.medicoId() != null) {
            throw new ValidacaoException("Médico não cadastrado");
        }
        var consulta = new Consulta(null, paciente.get(), medico, dadosCadastraisConsulta.dataConsulta());
        consultaRepository.save(consulta);
    }

    private Medico escolherMedicoDisponivel(DadosCadastraisConsulta dadosCadastraisConsulta) {

        if (dadosCadastraisConsulta.medicoId() != null) {
            return medicoRepository.findById(dadosCadastraisConsulta.medicoId()).get();
        }

        if (dadosCadastraisConsulta.especialidade() == null) {
            throw new ValidacaoException("É obrigatório informar a especialidade quando não for escolhido nenhum médico.");
        }
        return medicoRepository.escolherMedicoAleatorioNaLivreData(
                dadosCadastraisConsulta.especialidade(), dadosCadastraisConsulta.dataConsulta());
    }


}