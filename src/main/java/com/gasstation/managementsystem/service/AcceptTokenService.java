package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.AcceptToken;

public interface AcceptTokenService {

    public AcceptToken save(AcceptToken acceptToken);

    public void deleteByToken(String token);

    public void deleteByAccountId(int accountId);

}
