package med.solution.apiRest.records;

import med.solution.apiRest.models.Endereco;
import med.solution.apiRest.models.Paciente;

public record DadosDetalhamentoPaciente(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        Endereco endereco
) {
    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getEnderecoCompleto()
        );
    }
}
