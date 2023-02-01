package med.solution.apiRest.records;

import med.solution.apiRest.models.enums.Especialidade;

public record DadosCadastraisMedico (	
		String nome,
		String email,
		String telefone,
		String crm,
		Especialidade  especialidade,
		DadosEndereco enderecoCompleto) {

}
