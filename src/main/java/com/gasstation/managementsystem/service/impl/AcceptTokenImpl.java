package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.AcceptToken;
import com.gasstation.managementsystem.repository.AcceptTokenRepository;
import com.gasstation.managementsystem.service.AcceptTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AcceptTokenImpl implements AcceptTokenService {
    private final AcceptTokenRepository acceptTokenRepository;

    @Override
    public AcceptToken save(AcceptToken acceptToken) {
        return acceptTokenRepository.save(acceptToken);
    }

    @Override
    public void deleteByToken(String token) {
        acceptTokenRepository.deleteAllByToken(token);
    }

    @Override
    public void deleteByAccountId(int accountId) {
        acceptTokenRepository.deleteAllByAccountId(accountId);
    }

    @Override
    public AcceptToken getAcceptToken(String token, int id) {
        return acceptTokenRepository.getAcceptToken(token,id);
    }

    @Override
    public AcceptToken findByToken(String token) {
        return acceptTokenRepository.findByToken(token);
    }
}
