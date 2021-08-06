package com.gasstation.managementsystem.exception.custom;

import com.gasstation.managementsystem.model.CustomError;
import lombok.Getter;
import lombok.Setter;

public class CustomBadRequestException extends BaseCustomException {
    public CustomBadRequestException(CustomError customError) {
        super(customError);
    }
}
