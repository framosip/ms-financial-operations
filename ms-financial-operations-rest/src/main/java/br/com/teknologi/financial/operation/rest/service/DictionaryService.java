package br.com.teknologi.financial.operation.rest.service;

import br.com.teknologi.financial.operation.rest.model.constant.HttpMessagesCodes;
import br.com.teknologi.financial.operation.rest.model.response.ErrorResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final MessageSource messageSource;

    public ErrorResponse getMessage(@NonNull String key) {
        try {
            return new ErrorResponse(key, this.messageSource.getMessage(key, null, LocaleContextHolder.getLocale()));
        }catch (NoSuchMessageException e){
            return getMessage(HttpMessagesCodes.GENERIC_INTERNAL_ERROR_EXCEPTION);
        }
    }

    public ErrorResponse getMessageWithField(@NonNull String key, @NonNull String field) {
        try {
            String description = field
                    .concat(" ")
                    .concat(this.messageSource.getMessage(key, null, LocaleContextHolder.getLocale()));
            return new ErrorResponse(key, null, description);
        }catch (NoSuchMessageException e){
            return getMessage(key);
        }
    }

    public ErrorResponse getMessageWithExceptionErrorCode(@NonNull String key, @NonNull String code) {
        try {
            String description = this.messageSource.getMessage(key, null, LocaleContextHolder.getLocale())
                                .concat(". ")
                                .concat(this.messageSource.getMessage(code, null, LocaleContextHolder.getLocale()));
            return new ErrorResponse(key, null, description);
        }catch (NoSuchMessageException e){
            return getMessage(key);
        }
    }

    public ErrorResponse getMessageWithFieldAndDescription(@NonNull String key, @NonNull String field, @NonNull String description) {
        try {
            String newDescription = this.messageSource.getMessage(key, null, LocaleContextHolder.getLocale())
                    .concat(". ")
                    .concat(field)
                    .concat(" ")
                    .concat(description);
            return new ErrorResponse(key, null, newDescription);
        }catch (NoSuchMessageException e){
            return getMessage(key);
        }
    }
}