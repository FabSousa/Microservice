package br.com.fiap.mspedidos.controller.handlers;

import br.com.fiap.mspedidos.dto.errors.ValidationCustomErrorDTO;
import br.com.fiap.mspedidos.service.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity CustomExceptionHandler(ResourceNotFoundException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ValidationCustomErrorDTO errorDTO = new ValidationCustomErrorDTO(Instant.now(),
                status.value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(errorDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationCustomErrorDTO> methodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationCustomErrorDTO errorDTO = new ValidationCustomErrorDTO(Instant.now(), status.value(),
                "Dados Inválidos", request.getRequestURI());
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()){
            errorDTO.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(errorDTO);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ValidationCustomErrorDTO> JsonParseError(HttpMessageNotReadableException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationCustomErrorDTO errorDTO = new ValidationCustomErrorDTO(Instant.now(),
                status.value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(errorDTO);
    }
}
