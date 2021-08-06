package com.gasstation.managementsystem.exception.custom;

import com.gasstation.managementsystem.model.CustomError;
import lombok.Getter;
import lombok.Setter;

public class CustomUnauthorizedException extends BaseCustomException {

    public CustomUnauthorizedException(CustomError customError) {
        super(customError);
    }
}
