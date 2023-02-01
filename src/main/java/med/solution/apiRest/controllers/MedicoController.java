package med.solution.apiRest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.solution.apiRest.models.Medico;
import med.solution.apiRest.records.DadosCadastraisMedico;
import med.solution.apiRest.repositories.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository medRepository;
	
	@PostMapping
	public void cadastrarMedico(@RequestBody DadosCadastraisMedico dadosMedico) {
		
		medRepository.save(new Medico(dadosMedico));
	
	}
}
