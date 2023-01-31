package med.solution.apiRest.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.solution.apiRest.records.DadosCadastraisMedico;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@PostMapping
	public void cadastrarMedico(@RequestBody DadosCadastraisMedico dadosMedico) {
		
		System.out.println(dadosMedico);
	
	}
}
