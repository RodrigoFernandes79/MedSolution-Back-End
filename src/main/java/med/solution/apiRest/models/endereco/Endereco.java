package med.solution.apiRest.models.endereco;
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
	
	public Endereco(DadosEndereco enderecoCompleto) {
		 
		this.logradouro = enderecoCompleto.logradouro();
		this.numero = enderecoCompleto.numero();
		this.complemento = enderecoCompleto.complemento();
		this.bairro = enderecoCompleto.bairro();
		this.cidade = enderecoCompleto.cidade();
		this.uf = enderecoCompleto.uf();
		this.cep = enderecoCompleto.cep();
		
	}

    public void atualizarInformacoesEndereco(DadosEndereco endereco) {
		if(endereco.logradouro() != null) {
			this.logradouro = endereco.logradouro();
		}
		if(endereco.numero() != null) {
			this.numero = endereco.numero();
		}
		if(endereco.complemento() != null) {
			this.complemento = endereco.complemento();
		}
		if(endereco.bairro() != null) {
			this.bairro = endereco.bairro();
		}
		if(endereco.cidade() != null) {
			this.cidade = endereco.cidade();
		}
		if(endereco.uf() != null) {
			this.uf = endereco.uf();
		}
		if(endereco.cep() != null) {
			this.cep = endereco.cep();
		}
    }
}
