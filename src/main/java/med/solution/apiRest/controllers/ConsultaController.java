package med.solution.apiRest.controllers;

import jakarta.validation.Valid;
import med.solution.apiRest.models.consulta.DadosCadastraisConsulta;
import med.solution.apiRest.models.consulta.DadosDetalhamentoConsulta;
import med.solution.apiRest.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(
            @Valid @RequestBody DadosCadastraisConsulta dadosCadastraisConsulta) {

        return ResponseEntity.ok().body(new DadosDetalhamentoConsulta(null,null,null,null));
    }
}
