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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                .collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", apiDTOS);
        return map;
    }

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<Api> apis = apiRepository.findAll(pageable);
        HashMap<String, Object> map = listApiToMap(apis.getContent());
        map.put("totalElement", apis.getTotalElements());
        map.put("totalPage", apis.getTotalPages());
        return map;
    }

    @Override
    public HashMap<String, Object> findAll() {
        return listApiToMap(apiRepository.findAll());
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
        ApiMapper.copyNonNullToApi(oldApi, apiDTOUpdate);
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
    public ApiDTO getByApi(String path, String method) throws CustomNotFoundException {
        return ApiMapper.toApiDTO(optionalValidate.getUrlByApiAndMethod(path, method));
    }

    @Override
    public ApiDTO addPermission(int id, int typeId) throws CustomNotFoundException, CustomDuplicateFieldException {
        Api api = optionalValidate.getApiById(id);
        Set<UserType> permissions = api.getUserTypeList();
        if (permissions == null) {
            permissions = new HashSet<>();
        }
        if (permissions.stream().anyMatch(userType -> userType.getId() == typeId)) {
            throw new CustomDuplicateFieldException(CustomError.builder()
                    .code("not.permission").field("type_id").message("Type id already has permission")
                    .table("error in ApiServiceImpl.class").build());
        }
        permissions.add(optionalValidate.getUserTypeById(typeId));
        api.setUserTypeList(permissions);
        api = apiRepository.save(api);
        return ApiMapper.toApiDTO(api);
    }

    @Override
    public ApiDTO deletePermission(int id, int typeId) throws CustomNotFoundException {
        Api api = optionalValidate.getApiById(id);
        Set<UserType> permissions = api.getUserTypeList();
        if (permissions == null) {
            permissions = new HashSet<>();
        }
        for (UserType userType : permissions) {
            if (userType.getId() == typeId) {
                permissions.remove(userType);
                break;
            }
        }
        api.setUserTypeList(permissions);
        api = apiRepository.save(api);
        return ApiMapper.toApiDTO(api);
    }
}
