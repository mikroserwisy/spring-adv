package pl.training.shop.commons.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Log
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionDto> onException(Exception exception, Locale locale) {
        log.warning(exception.getMessage());
        return createResponse(exception, INTERNAL_SERVER_ERROR, locale);
    }

    protected ResponseEntity<ExceptionDto> createResponse(Exception exception, HttpStatus status, Locale locale) {
        var key = exception.getClass().getSimpleName();
        String description;
        try {
            description = messageSource.getMessage(key, new Object[] {}, locale);
        } catch (NoSuchMessageException ex) {
            description = key;
        }
        return ResponseEntity.status(status).body(new ExceptionDto(description));
    }

}
