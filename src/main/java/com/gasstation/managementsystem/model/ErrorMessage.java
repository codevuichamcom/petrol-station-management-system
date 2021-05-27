package com.gasstation.managementsystem.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorMessage {
    private int statusCode;
    private String message;
}
