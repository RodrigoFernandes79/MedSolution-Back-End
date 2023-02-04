package med.solution.apiRest.records;

import med.solution.apiRest.models.Paciente;

public record DadosListagemPaciente(
        String nome,
        String email,
        String cpf
) {

    public DadosListagemPaciente(Paciente paciente) {
        this(
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getCpf()
        );
    }
}
