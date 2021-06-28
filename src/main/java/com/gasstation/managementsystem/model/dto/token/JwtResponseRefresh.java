package com.gasstation.managementsystem.model.dto.token;

import com.gasstation.managementsystem.model.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/*
 * This is class is required for creating a response containing the JWT to be returned to the user.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JwtResponseRefresh implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private String accessToken;
    private String refreshToken;
    private UserDTO user;
}
