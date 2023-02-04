package med.solution.apiRest.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.solution.apiRest.records.DadosCadastraisPaciente;

@Table(name="pacientes")
@Entity(name="Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco enderecoCompleto;

    public Paciente(DadosCadastraisPaciente dadosPaciente) {
        this.nome = dadosPaciente.nome();
        this.email = dadosPaciente.email();
        this.cpf = dadosPaciente.cpf();
        this.telefone = dadosPaciente.telefone();
        this.enderecoCompleto = new Endereco(dadosPaciente.enderecoCompleto());
    }
}


