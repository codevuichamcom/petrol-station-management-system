package com.gasstation.managementsystem.exception.custom;

import com.gasstation.managementsystem.model.CustomError;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public abstract class BaseCustomException extends Exception {
    protected Map<String, CustomError> errorHashMap;

    public BaseCustomException(CustomError customError) {
        errorHashMap = new HashMap<>();
        errorHashMap.put("error", customError);
    }

}
