package med.solution.apiRest.controllers;


import jakarta.validation.Valid;
import med.solution.apiRest.models.Paciente;
import med.solution.apiRest.records.DadosCadastraisPaciente;
import med.solution.apiRest.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	@Autowired
	private PacienteRepository pacienteRepository;
	@PostMapping
	@Transactional
	public void cadastrarPaciente(@RequestBody @Valid DadosCadastraisPaciente dadosPaciente) {
	pacienteRepository.save(new Paciente(dadosPaciente));
}
}