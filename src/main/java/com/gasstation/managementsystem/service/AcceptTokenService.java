package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.AcceptToken;

public interface AcceptTokenService {

    AcceptToken save(AcceptToken acceptToken);

    void deleteByToken(String token);

    void deleteByAccountId(int accountId);

    AcceptToken getAcceptToken(String token, int id);

    AcceptToken findByToken(String token);
}
