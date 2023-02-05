package med.solution.apiRest.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErroNotFound404() {
        return ResponseEntity.notFound().build();
    }
}
