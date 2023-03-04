package med.solution.apiRest.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.solution.apiRest.models.consulta.DadosCadastraisConsulta;
import med.solution.apiRest.models.consulta.DadosCancelamentoConsulta;
import med.solution.apiRest.services.AgendaDeConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService agendaDeConsultaService;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(
            @Valid @RequestBody DadosCadastraisConsulta dadosCadastraisConsulta) {
        var consulta = agendaDeConsultaService.agendarConsulta(dadosCadastraisConsulta);

        return ResponseEntity.ok().body(consulta);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancelarConsulta(
            @RequestBody @Valid DadosCancelamentoConsulta dadosCancelamento) {

        agendaDeConsultaService.cancelarConsulta(dadosCancelamento);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
