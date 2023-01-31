package med.solution.apiRest.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.solution.apiRest.records.DadosCadastraisPaciente;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	
	@PostMapping 
	public void cadastrarPaciente(@RequestBody DadosCadastraisPaciente dadosPaciente) {

}
}