package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Api;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.api.ApiDTO;
import com.gasstation.managementsystem.model.dto.api.ApiDTOCreate;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ApiMapper {

    public static ApiDTO toApiDTO(Api api) {
        if (api == null) return null;
        Set<UserType> userTypes = api.getUserTypeList();
        List<UserTypeDTO> userTypeDTOList = null;
        if (userTypes != null) {
            userTypeDTOList = userTypes.stream()
                    .map(UserTypeMapper::toUserTypeDTO)
                    .collect(Collectors.toList());
        }
        String path = api.getPath();
        path = path.replaceAll(Api.PREFIX, "");
        return ApiDTO.builder()
                .id(api.getId())
                .method(api.getMethod())
                .path(path)
                .accessibleUserTypes(userTypeDTOList).build();
    }

    public static Api toApi(ApiDTOCreate apiDTOCreate) {
        if (apiDTOCreate == null) return null;
        return Api.builder()
                .method(apiDTOCreate.getMethod())
                .path(Api.PREFIX + apiDTOCreate.getPath())
                .build();
    }
}
