package med.solution.apiRest.models.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.solution.apiRest.models.endereco.DadosEndereco;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastraisPaciente(
		@NotBlank
		String   nome,
		@NotBlank
		@Email
		String  email,
		@NotBlank
		String  telefone,
		@NotBlank
		@CPF
		String  cpf,
		@NotNull
		@Valid
        DadosEndereco endereco) {

}
