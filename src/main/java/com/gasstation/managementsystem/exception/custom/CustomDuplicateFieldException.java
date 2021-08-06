package com.gasstation.managementsystem.exception.custom;

import com.gasstation.managementsystem.model.CustomError;
import lombok.Getter;
import lombok.Setter;

public class CustomDuplicateFieldException extends BaseCustomException {

    public CustomDuplicateFieldException(CustomError customError) {
        super(customError);
    }
}
