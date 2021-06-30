package br.com.gabriels.praticafreemarket.infra;

import org.springframework.http.HttpStatus;
import org.springframework.validation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestControllerAdvice
public class ValidationErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, List<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, List<String>> erros = new HashMap<>();
        BindingResult bindingResult = ex.getBindingResult();

        handleFieldErrors(bindingResult.getFieldErrors(), erros);
        handleGlobalErrors(bindingResult.getGlobalErrors(), erros);
        return erros;
    }

    private void handleGlobalErrors(List<ObjectError> errosGlobais, Map<String, List<String>> erros) {
        errosGlobais.forEach((erro) -> {
            String fieldName = erro.getObjectName();
            String errorMessage = erro.getDefaultMessage();
            erros.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(errorMessage);
        });
    }

    private void handleFieldErrors(List<FieldError> fieldErrors, Map<String, List<String>> erros) {
        fieldErrors.forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            erros.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(errorMessage);
        });
    }
}
