package med.solution.apiRest.controllers;

import jakarta.validation.Valid;
import med.solution.apiRest.models.Medico;
import med.solution.apiRest.records.DadosCadastraisMedico;
import med.solution.apiRest.records.DadosListagemMedico;
import med.solution.apiRest.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medRepository;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid DadosCadastraisMedico dadosMedico) {

        medRepository.save(new Medico(dadosMedico));

    }

    @GetMapping
    public Page<DadosListagemMedico> listarMedico(
            @PageableDefault(size =10,sort = {"nome"} )
            Pageable paginacao) {

        return medRepository.findAll(paginacao).map(DadosListagemMedico::new);

    }

}
