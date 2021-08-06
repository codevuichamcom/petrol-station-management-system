package com.gasstation.managementsystem.exception.custom;

import com.gasstation.managementsystem.model.CustomError;
import lombok.Getter;
import lombok.Setter;

public class CustomInternalServerException extends BaseCustomException {

    public CustomInternalServerException(CustomError customError) {
        super(customError);
    }
}
