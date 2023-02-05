package med.solution.apiRest.controllers;

import jakarta.validation.Valid;
import med.solution.apiRest.models.Medico;
import med.solution.apiRest.records.DadosAtualizacaoMedico;
import med.solution.apiRest.records.DadosCadastraisMedico;
import med.solution.apiRest.records.DadosDetalhamentoMedico;
import med.solution.apiRest.records.DadosListagemMedico;
import med.solution.apiRest.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedico(@RequestBody @Valid DadosCadastraisMedico dadosMedico,
                                          UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dadosMedico);
        medRepository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

       return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listarMedico(
            @PageableDefault(size =10,sort = {"nome"} )
            Pageable paginacao) {

        var pageMedico = medRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

        return ResponseEntity.ok().body(pageMedico);

    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizarMedico(@RequestBody @Valid DadosAtualizacaoMedico dadosMedico){

        var medico = medRepository.getReferenceById(dadosMedico.id());
          medico.atualizarInformacoes(dadosMedico);
        return  ResponseEntity.ok().body(new DadosDetalhamentoMedico(medico));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarMedico(@PathVariable Long id) {
        var medico = medRepository.getReferenceById(id);
        medico.desativarMedico();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity detalharMedicoPorId(@PathVariable Long id) {
        var medico = medRepository.getReferenceById(id);

        return ResponseEntity.ok().body(new DadosDetalhamentoMedico(medico));
    }

}
