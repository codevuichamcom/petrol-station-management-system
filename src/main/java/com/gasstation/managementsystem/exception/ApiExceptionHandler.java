package com.gasstation.managementsystem.exception;

import com.gasstation.managementsystem.exception.custom.*;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CustomDuplicateFieldException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public Map<String, CustomError> duplicateFieldException(CustomDuplicateFieldException ex) {
        return ex.getErrorHashMap();
    }

    @ExceptionHandler(CustomBadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, CustomError> notFoundIdException(CustomBadRequestException ex) {
        return ex.getErrorHashMap();
    }

    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String, CustomError> notFoundIdException(CustomNotFoundException ex) {
        return ex.getErrorHashMap();
    }

    @ExceptionHandler(CustomForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public Map<String, CustomError> forBiddenException(CustomForbiddenException ex) {
        return ex.getErrorHashMap();
    }

    @ExceptionHandler(CustomUnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Map<String, CustomError> unAuthorizedException(CustomUnauthorizedException ex) {
        return ex.getErrorHashMap();
    }

    @ExceptionHandler(CustomInternalServerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, CustomError> internalServer(CustomInternalServerException ex) {
        return ex.getErrorHashMap();
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage notExistException(Exception ex) {
        return new ErrorMessage(404, "Object is not exist: " + ex.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage duplicateException(Exception ex) {
        return new ErrorMessage(409, "Duplicate key in db: " + ex.getMessage());
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorMessage allException(Exception ex) {
//        return new ErrorMessage(500, "Some thing when wrong " + ex.getMessage()+"\n"+ex.getCause());
//    }


}
