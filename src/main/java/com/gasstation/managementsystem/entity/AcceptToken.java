package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(AcceptTokenPrimaryKey.class)
@Table(name = "accept_token_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AcceptToken {
    @Id
    private String token;

    @Id
    private int accountId;


}
