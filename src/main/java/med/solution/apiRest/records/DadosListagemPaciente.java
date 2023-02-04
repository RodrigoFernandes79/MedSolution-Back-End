package med.solution.apiRest.records;

import jakarta.validation.constraints.NotNull;
import med.solution.apiRest.models.Paciente;

public record DadosListagemPaciente(
       @NotNull
        Long id,
        String nome,
        String email,
        String cpf
) {

    public DadosListagemPaciente(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getCpf()
        );
    }
}
