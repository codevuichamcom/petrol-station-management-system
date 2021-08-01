package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.debt.DebtDTO;

import java.util.HashMap;

public interface DebtService {

    HashMap<String, Object> findAll();

    DebtDTO findById(int id) throws CustomNotFoundException;
}
