package med.solution.apiRest.models.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.solution.apiRest.models.endereco.DadosEndereco;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastraisPaciente(
		@NotBlank(message = "{nome.obrigatorio}")
		String nome,
		@NotBlank(message = "{email.obrigatorio}")
		@Email(message = "{email.invalido}")
		String email,
		@NotBlank(message = "{telefone.obrigatorio}")
		String telefone,

		@NotBlank(message="{cpf.obrigatorio}")
		@CPF(message = "{cpf.invalido}")
		String  cpf,

		@NotNull(message = "{endereco.obrigatorio}")
		@Valid
		DadosEndereco endereco) {

}
