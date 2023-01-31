package med.solution.apiRest.records;

public record DadosCadastraisPaciente(
		String   nome,
		String  email,
		String  telefone,
		String  cpf,
		Endereco   endereçoCompleto) {

}
