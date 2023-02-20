package med.solution.apiRest.models.paciente;

import jakarta.persistence.*;
import lombok.*;
import med.solution.apiRest.models.endereco.Endereco;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    private Boolean ativo;
    @Embedded
    private Endereco enderecoCompleto;

    public Paciente(DadosCadastraisPaciente dadosPaciente) {
        this.nome = dadosPaciente.nome();
        this.email = dadosPaciente.email();
        this.cpf = dadosPaciente.cpf();
        this.telefone = dadosPaciente.telefone();
        this.ativo = true;
        this.enderecoCompleto = new Endereco(dadosPaciente.endereco());
    }

    public void atualizarInformacoesPaciente(DadosAtualizacaoPaciente dadosPaciente) {
        if (dadosPaciente.nome() != null) {
            this.nome = dadosPaciente.nome();
        }
        if (dadosPaciente.telefone() != null) {
            this.telefone = dadosPaciente.telefone();
        }
        if (dadosPaciente.endereco() != null) {
            this.enderecoCompleto.atualizarInformacoesEndereco(dadosPaciente.endereco());
        }
    }

    public void desativarPaciente() {

        this.ativo = false;
    }
}


