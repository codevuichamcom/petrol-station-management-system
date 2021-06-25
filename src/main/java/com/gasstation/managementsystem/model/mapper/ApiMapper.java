package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Api;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.api.ApiDTO;
import com.gasstation.managementsystem.model.dto.api.ApiDTOCreate;
import com.gasstation.managementsystem.model.dto.api.ApiDTOUpdate;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ApiMapper {

    public static ApiDTO toApiDTO(Api api) {
        if (api == null) return null;
        Set<UserType> userTypes = api.getUserTypeList();
        List<UserTypeDTO> userTypeDTOList = userTypes.stream()
                .map(UserTypeMapper::toUserTypeDTO)
                .collect(Collectors.toList());

        return ApiDTO.builder()
                .id(api.getId())
                .name(api.getName())
                .method(api.getMethod())
                .api(api.getApi())
                .userTypeList(userTypeDTOList).build();
    }

    public static Api toApi(ApiDTOCreate apiDTOCreate) {
        if (apiDTOCreate == null) return null;
        return Api.builder()
                .name(apiDTOCreate.getName())
                .method(apiDTOCreate.getMethod())
                .api(apiDTOCreate.getApi())
                .build();
    }

    public static void copyNonNullToApi(Api api, ApiDTOUpdate apiDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(api, apiDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
