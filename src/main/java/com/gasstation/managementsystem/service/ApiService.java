package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.api.ApiDTO;
import com.gasstation.managementsystem.model.dto.api.ApiDTOCreate;
import com.gasstation.managementsystem.model.dto.api.ApiDTOUpdate;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface ApiService {
    HashMap<String, Object> findAll(Pageable pageable);

    HashMap<String, Object> findAll();

    ApiDTO findById(int id) throws CustomNotFoundException;

    ApiDTO create(ApiDTOCreate apiDTOCreate) throws CustomNotFoundException, CustomDuplicateFieldException;

    ApiDTO update(int id, ApiDTOUpdate apiDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException;

    ApiDTO delete(int id) throws CustomNotFoundException;

    ApiDTO getByApi(String api, String method) throws CustomNotFoundException;

    ApiDTO addPermission(int id, int typeId) throws CustomNotFoundException, CustomDuplicateFieldException;

    ApiDTO deletePermission(int id, int typeId) throws CustomNotFoundException;
}
