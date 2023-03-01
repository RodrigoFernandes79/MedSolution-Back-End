package med.solution.apiRest.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErroNotFound404() {

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErroValidacao400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        var erro = errors.stream().map(DadosCampoValidacao::new).toList();

        return ResponseEntity.badRequest().body(erro);
    }

    private record DadosCampoValidacao(String erro, String mensagem) {

        public DadosCampoValidacao(FieldError erro) {

            this(erro.getField(), erro.getDefaultMessage());
        }
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErroValidacoesRegraDeNegocio(ValidacaoException ex) {

        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
