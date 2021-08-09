package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Api;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.dto.api.ApiDTO;
import com.gasstation.managementsystem.model.dto.api.ApiDTOCreate;
import com.gasstation.managementsystem.model.dto.api.ApiDTOUpdate;
import com.gasstation.managementsystem.model.mapper.ApiMapper;
import com.gasstation.managementsystem.repository.ApiRepository;
import com.gasstation.managementsystem.service.ApiService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
    private final ApiRepository apiRepository;
    private final OptionalValidate optionalValidate;

    private HashMap<String, Object> listApiToMap(List<Api> apis) {
        List<ApiDTO> apiDTOS = apis.stream()
                .map(ApiMapper::toApiDTO)
                .sorted((o1, o2) -> {
                    if (o1.getPath().compareToIgnoreCase(o2.getPath()) > 0) {
                        return 1;
                    } else if (o1.getPath().compareToIgnoreCase(o2.getPath()) < 0) {
                        return -1;
                    } else {
                        return o1.getMethod().compareToIgnoreCase(o2.getMethod());
                    }
                })
                .collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", apiDTOS);
        return map;
    }


    @Override
    public HashMap<String, Object> findAll() {
        return listApiToMap(apiRepository.findAll());
    }

    @Override
    public HashMap<String, Object> findAllByUserTypeId(int userTypeId) {
        List<Api> apiList = apiRepository.findAllByUserTypeId(userTypeId);
        if (userTypeId == UserType.OWNER) {
            apiList.add(Api.builder().id(-1).path("/api/v1/shifts").method("GET").build());
            apiList.add(Api.builder().id(-1).path("/api/v1/fuels").method("GET").build());
        }
        return listApiToMap(apiList);
    }

    @Override
    public ApiDTO findById(int id) throws CustomNotFoundException {
        return ApiMapper.toApiDTO(optionalValidate.getApiById(id));
    }

    @Override
    public ApiDTO create(ApiDTOCreate apiDTOCreate) throws CustomNotFoundException, CustomDuplicateFieldException {
        checkDuplicate(Api.PREFIX + apiDTOCreate.getPath(), apiDTOCreate.getMethod());
        Api api = ApiMapper.toApi(apiDTOCreate);
        List<Integer> userTypeIds = apiDTOCreate.getAccessibleUserTypes();
        Set<UserType> userTypeList = new HashSet<>();
        for (Integer id : userTypeIds) {
            userTypeList.add(optionalValidate.getUserTypeById(id));
        }
        api.setUserTypeList(userTypeList);
        api = apiRepository.save(api);
        return ApiMapper.toApiDTO(api);
    }

    @Override
    public ApiDTO update(int id, ApiDTOUpdate apiDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException {
        Api oldApi = optionalValidate.getApiById(id);
        List<Integer> userTypeIds = apiDTOUpdate.getAccessibleUserTypes();
        if (userTypeIds != null) {
            Set<UserType> userTypeList = new HashSet<>();
            for (Integer typeId : userTypeIds) {
                userTypeList.add(optionalValidate.getUserTypeById(typeId));
            }
            oldApi.setUserTypeList(userTypeList);
        }
        oldApi = apiRepository.save(oldApi);
        return ApiMapper.toApiDTO(oldApi);
    }

    private void checkDuplicate(String path, String method) throws CustomDuplicateFieldException {
        if (path != null && method != null) {
            Optional<Api> apiOptional = apiRepository.findByPathAndMethod(path, method);
            if (apiOptional.isPresent()) {
                throw new CustomDuplicateFieldException(CustomError.builder()
                        .code("duplicate").field("(path,method)").message("Duplicate (path,method)")
                        .table("api_tbl in ApiServiceImpl.class").build());
            }

        }
    }

    @Override
    public ApiDTO delete(int id) throws CustomNotFoundException {
        Api api = optionalValidate.getApiById(id);
        apiRepository.delete(api);
        return ApiMapper.toApiDTO(api);
    }

    @Override
    public void saveAll(List<ApiDTOCreate> apiDTOCreateList) {
        List<Api> apiList = apiDTOCreateList.stream().map(ApiMapper::toApi).collect(Collectors.toList());
        apiRepository.saveAll(apiList);
    }

    @Override
    public void deleteAll() {
        apiRepository.deleteAll();
    }
}
