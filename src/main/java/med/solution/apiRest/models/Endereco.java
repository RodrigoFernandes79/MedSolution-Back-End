package med.solution.apiRest.models;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.solution.apiRest.records.DadosEndereco;

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
	
	public Endereco(DadosEndereco enderecoCompleto) {
		 
		this.logradouro = enderecoCompleto.logradouro();
		this.numero = enderecoCompleto.numero();
		this.complemento = enderecoCompleto.complemento();
		this.bairro = enderecoCompleto.bairro();
		this.cidade = enderecoCompleto.cidade();
		this.uf = enderecoCompleto.uf();
		this.cep = enderecoCompleto.cep();
		
	}

}
