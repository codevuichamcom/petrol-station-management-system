package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.api.ApiDTO;
import com.gasstation.managementsystem.model.dto.api.ApiDTOCreate;
import com.gasstation.managementsystem.model.dto.api.ApiDTOUpdate;

import java.util.HashMap;
import java.util.List;

public interface ApiService {

    HashMap<String, Object> findAll();

    HashMap<String, Object> findAllByUserTypeId(int userTypeId);

    ApiDTO findById(int id) throws CustomNotFoundException;

    ApiDTO create(ApiDTOCreate apiDTOCreate) throws CustomNotFoundException, CustomDuplicateFieldException;

    ApiDTO update(int id, ApiDTOUpdate apiDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException;

    ApiDTO delete(int id) throws CustomNotFoundException;

    void saveAll(List<ApiDTOCreate> apiDTOCreateList);

    void deleteAll();
}
