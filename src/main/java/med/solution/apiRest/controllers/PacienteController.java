package med.solution.apiRest.controllers;


import jakarta.validation.Valid;
import med.solution.apiRest.models.Paciente;
import med.solution.apiRest.records.DadosAtualizacaoPaciente;
import med.solution.apiRest.records.DadosCadastraisPaciente;
import med.solution.apiRest.records.DadosListagemPaciente;
import med.solution.apiRest.repositories.MedicoRepository;
import med.solution.apiRest.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	@Autowired
	private PacienteRepository pacienteRepository;
	@Autowired
	private MedicoRepository medicoRepository;

	@PostMapping
	@Transactional
	public void cadastrarPaciente(@RequestBody @Valid DadosCadastraisPaciente dadosPaciente) {
	pacienteRepository.save(new Paciente(dadosPaciente));
}
	@GetMapping
	public Page<DadosListagemPaciente> listarPacientes(
			@PageableDefault(size=10,sort={"nome"})
			Pageable paginacao) {
		return pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
	}
	@PutMapping
	@Transactional
	public void atualizarPaciente(@RequestBody @Valid DadosAtualizacaoPaciente dadosPaciente) {
		var paciente = pacienteRepository.getReferenceById(dadosPaciente.id());
		paciente.atualizarInformacoesPaciente(dadosPaciente);

	}
	@DeleteMapping("/{id}")
	@Transactional
	public void deletarPaciente(@PathVariable Long id) {
		var paciente = pacienteRepository.getReferenceById(id);
		paciente.desativarPaciente();
	}
}