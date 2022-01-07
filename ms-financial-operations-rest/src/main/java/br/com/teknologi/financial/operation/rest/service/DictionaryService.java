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

    public ErrorResponse getMensagem(@NonNull String key) {
        try {
            return new ErrorResponse(key, this.messageSource.getMessage(key, null, LocaleContextHolder.getLocale()));
        }catch (NoSuchMessageException e){
            return getMensagem(HttpMessagesCodes.GENERIC_INTERNAL_ERROR_EXCEPTION);
        }
    }

    public ErrorResponse getMensagem(@NonNull String key, @NonNull String field) {
        try {
            return new ErrorResponse(key, field, this.messageSource.getMessage(key, null, LocaleContextHolder.getLocale()));
        }catch (NoSuchMessageException e){
            return getMensagem(key);
        }
    }

}