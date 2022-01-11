package br.com.teknologi.financial.operation.rest.configuration;

import br.com.teknologi.financial.operation.rest.model.constant.HttpMessagesCodes;
import br.com.teknologi.financial.operation.rest.model.response.ErrorResponse;
import br.com.teknologi.financial.operation.rest.service.DictionaryService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CustomRestControllerAdvice {

    private static final String REST_CONTROLLER_ADVICE = "[Rest Controller Advice]";

    private final DictionaryService dictionaryService;

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public Mono<ResponseEntity<List<ErrorResponse>>> handleWebExchangeBindException(WebExchangeBindException ex){
        log.error(REST_CONTROLLER_ADVICE, ex);

        List<ErrorResponse> errors = ex.getBindingResult().getAllErrors().stream()
                .map(error ->
                        new ErrorResponse(HttpMessagesCodes.FIELD_VALIDATION_ERROR,
                                getErrorField(error.getCodes()),
                                error.getDefaultMessage() != null ? error.getDefaultMessage() : "Error message not found"))
                .collect(Collectors.toList());

        return Mono.just(ResponseEntity
                .status(HttpStatus.PRECONDITION_FAILED)
                .body(errors));
    }

    @ExceptionHandler(ServerWebInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ResponseEntity<ErrorResponse>> handleServerWebInputException(Exception ex) {
        log.error(REST_CONTROLLER_ADVICE, ex);

        Throwable rootException = ex;

        while(rootException.getCause() != null){

            Throwable cause = rootException.getCause();

            if(cause instanceof InvalidFormatException){

                InvalidFormatException exception = (InvalidFormatException) cause;

                return Mono.just(ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(this.dictionaryService.getMensagem(HttpMessagesCodes.REQUEST_WITH_INVALID_VALUE, exception.getPath().get(0).getFieldName())));
            }

            rootException = rootException.getCause();

        }

        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(this.dictionaryService.getMensagem(HttpMessagesCodes.GENERIC_BAD_REQUEST_EXCEPTION)));
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ResponseEntity<ErrorResponse>> handleException(Exception ex){
        log.error(REST_CONTROLLER_ADVICE, ex);

        return Mono.just(ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(this.dictionaryService.getMensagem(HttpMessagesCodes.GENERIC_INTERNAL_ERROR_EXCEPTION)));
    }

    private String getErrorField(String[] codes) {

        if(codes != null && codes.length >= 2){
            String code = codes[1];

            if(code != null){
                String[] fields = code.split("\\.");

                if(fields.length >= 2){
                    return fields[1];
                }
            }

            return null;
        }

        return null;
    }

}
