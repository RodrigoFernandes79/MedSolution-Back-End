package med.solution.apiRest.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
	

	private String logradouro;
	private String numero; 
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;

}
