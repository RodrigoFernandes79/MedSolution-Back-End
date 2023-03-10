package med.solution.apiRest.services;

import med.solution.apiRest.exceptions.ValidacaoException;
import med.solution.apiRest.models.consulta.Consulta;
import med.solution.apiRest.models.consulta.DadosCadastraisConsulta;
import med.solution.apiRest.models.consulta.DadosCancelamentoConsulta;
import med.solution.apiRest.models.consulta.DadosDetalhamentoConsulta;
import med.solution.apiRest.models.consulta.validadores.ValidadorAgendamentoDeConsulta;
import med.solution.apiRest.models.medico.Medico;
import med.solution.apiRest.models.paciente.Paciente;
import med.solution.apiRest.repositories.ConsultaRepository;
import med.solution.apiRest.repositories.MedicoRepository;
import med.solution.apiRest.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public DadosDetalhamentoConsulta agendarConsulta(DadosCadastraisConsulta dadosCadastraisConsulta) {
        Optional<Paciente> paciente = pacienteRepository.findById(dadosCadastraisConsulta.pacienteId());
        if (paciente.isEmpty()) {
            throw new ValidacaoException("Paciente não cadastrado");
        }
        if (dadosCadastraisConsulta.medicoId() != null &&
                !medicoRepository.existsById(dadosCadastraisConsulta.medicoId())) {
            throw new ValidacaoException("Médico não cadastrado");
        }

        Medico medico = escolherMedicoDisponivel(dadosCadastraisConsulta);

        if (medico == null) {
            throw new ValidacaoException("Não existe medico disponível nessa data.");
        }
        validadores.forEach(v -> v.validacaoConsulta(dadosCadastraisConsulta));

        var consulta = new Consulta(null, paciente.get(), medico, dadosCadastraisConsulta.dataConsulta(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedicoDisponivel(DadosCadastraisConsulta dadosCadastraisConsulta) {

        if (dadosCadastraisConsulta.medicoId() != null) {
            return medicoRepository.getReferenceById(dadosCadastraisConsulta.medicoId());
        }

        if (dadosCadastraisConsulta.especialidade() == null) {
            throw new ValidacaoException("É obrigatório informar a especialidade quando não for escolhido nenhum médico.");
        }
        return medicoRepository.escolherMedicoAleatorioNaLivreData(
                dadosCadastraisConsulta.especialidade(), dadosCadastraisConsulta.dataConsulta());
    }


    public void cancelarConsulta(DadosCancelamentoConsulta dadosCancelamento) {
        var consulta = consultaRepository.findById(dadosCancelamento.consultaId());
        if (consulta.isEmpty()) {
            throw new ValidacaoException("Consulta não existe no Banco de dados.");
        }
        var dataConsulta = consulta.get().getDataConsulta();
        var dataCancelamento = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(dataCancelamento, dataConsulta).toHours();
        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta deve ser cancelada em no mínimo 24 horas.");
        }

        consulta.get().cancelar(dadosCancelamento.motivo());
    }
}
