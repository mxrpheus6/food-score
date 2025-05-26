package com.mxrpheus.productservice.exception;

import com.mxrpheus.productservice.constants.ApplicationExceptionMessageKeys;
import com.mxrpheus.productservice.dto.ExceptionDto;
import com.mxrpheus.productservice.exception.brand.BrandAlreadyExistsException;
import com.mxrpheus.productservice.exception.brand.BrandNotFoundException;
import com.mxrpheus.productservice.exception.validation.Validation;
import com.mxrpheus.productservice.exception.validation.ValidationResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(BrandNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleNotFoundExceptions(MessageSourceException e) {
        String message = messageSource.getMessage(
                e.getMessageKey(),
                e.getArgs(),
                LocaleContextHolder.getLocale()
        );
        return new ExceptionDto(HttpStatus.NOT_FOUND, message, LocalDateTime.now());
    }

    @ExceptionHandler(BrandAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionDto handleAlreadyExistsExceptions(MessageSourceException e) {
        String message = messageSource.getMessage(
                e.getMessageKey(),
                e.getArgs(),
                LocaleContextHolder.getLocale()
        );
        return new ExceptionDto(HttpStatus.CONFLICT, message, LocalDateTime.now());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationResponse handleConstraintViolationExceptions(ConstraintViolationException e) {
        final List<Validation> validations = e.getConstraintViolations().stream()
                .map(validation -> new Validation(
                        validation.getPropertyPath().toString().replaceFirst(".*\\.", ""),
                        validation.getMessage()))
                .toList();

        return new ValidationResponse(validations);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handleInternalServerErrorExceptions(Exception e) {
        String message = messageSource.getMessage(
                ApplicationExceptionMessageKeys.INTERNAL_SERVER_ERROR_MESSAGE_KEY,
                null,
                LocaleContextHolder.getLocale()
        );
        return new ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR, message, LocalDateTime.now());
    }

}
