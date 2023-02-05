package med.solution.apiRest.models.medico;

import med.solution.apiRest.models.endereco.Endereco;
import med.solution.apiRest.models.medico.enums.Especialidade;

public record DadosDetalhamentoMedico(Long id,
                                      String nome,
                                      String email,
                                      String crm,
                                      String telefone,
                                      Especialidade especialidade,
                                      Endereco endereco) {
    public DadosDetalhamentoMedico(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getTelefone(),
                medico.getEspecialidade(),
                medico.getEnderecoCompleto());
    }
}
