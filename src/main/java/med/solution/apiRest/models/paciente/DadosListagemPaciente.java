package med.solution.apiRest.models.paciente;

import jakarta.validation.constraints.NotNull;
import med.solution.apiRest.models.paciente.Paciente;

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
