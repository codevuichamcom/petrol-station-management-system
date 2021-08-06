package com.gasstation.managementsystem.exception.custom;

import com.gasstation.managementsystem.model.CustomError;

public class CustomNotFoundException extends BaseCustomException {
    public CustomNotFoundException(CustomError customError) {
        super(customError);
    }
}
