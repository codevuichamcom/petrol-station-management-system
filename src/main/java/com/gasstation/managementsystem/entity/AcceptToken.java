package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;

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
    @Column(unique = true, nullable = false)
    private String token;

    @Id
    @Column(nullable = false)
    private int userId;


}
