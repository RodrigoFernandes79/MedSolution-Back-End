package med.solution.apiRest.models.paciente;

import med.solution.apiRest.models.endereco.Endereco;
import med.solution.apiRest.models.paciente.Paciente;

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
