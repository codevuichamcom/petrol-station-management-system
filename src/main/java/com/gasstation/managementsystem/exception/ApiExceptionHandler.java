package com.gasstation.managementsystem.exception;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(CustomDuplicateFieldException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public Map<String, CustomError> duplicateFieldException(CustomDuplicateFieldException ex, WebRequest request) {
        return ex.getErrorHashMap();
    }

    @ExceptionHandler(CustomBadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, CustomError> notFoundIdException(CustomBadRequestException ex, WebRequest request) {
        return ex.getErrorHashMap();
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage notExistException(Exception ex, WebRequest request) {
        return new ErrorMessage(404, "Object is not exist: " + ex.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage duplicateException(Exception ex, WebRequest request) {
        return new ErrorMessage(409, "Duplicate key in db: " + ex.getMessage());
    }


}
