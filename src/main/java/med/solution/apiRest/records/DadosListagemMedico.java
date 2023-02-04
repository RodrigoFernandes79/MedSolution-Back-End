package med.solution.apiRest.records;

import med.solution.apiRest.models.Medico;
import med.solution.apiRest.models.enums.Especialidade;

public record DadosListagemMedico(
        String nome,
        String email,
        String crm,
        Especialidade especialidade

) {
    public DadosListagemMedico(Medico medico) {
        this(
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getEspecialidade()
        );
    }
}
