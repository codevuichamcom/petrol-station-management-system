package com.gasstation.managementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AcceptTokenPrimaryKey implements Serializable {
    private String token;
    private int userId;
}
