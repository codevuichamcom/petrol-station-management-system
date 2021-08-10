package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Api;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.api.ApiDTO;
import com.gasstation.managementsystem.model.dto.api.ApiDTOCreate;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ApiMapperTest {
    /**
     * param api is null
     */
    @Test
    void toApiDTO_UTCID01() {
        assertNull(ApiMapper.toApiDTO(null));

    }

    /**
     * param api is not null
     */
    @Test
    void toApiDTO_UTCID02() {
        //given
        Set<UserType> userTypes = new HashSet<>();
        userTypes.add(UserType.builder().id(1).type("ADMIN").build());
        Api api = Api.builder()
                .id(1)
                .method("GET")
                .path("/api/v1/users")
                .userTypeList(userTypes).build();
        //when
        ApiDTO apiDTO = ApiMapper.toApiDTO(api);
        //then
        assertEquals(1, apiDTO.getId());
        assertEquals("GET", apiDTO.getMethod());
        assertEquals("/users", apiDTO.getPath());
        assertEquals(1, apiDTO.getAccessibleUserTypes().size());
    }

    /**
     * param apiDTOCreate is null
     */
    @Test
    void toApi_UTCID01() {
        assertNull(ApiMapper.toApi(null));
    }

    /**
     * param apiDTOCreate is not null
     */
    @Test
    void toApi_UTCID02() {
        ApiDTOCreate apiDTOCreate = ApiDTOCreate.builder()
                .method("GET").path("/users").build();
        Api api = ApiMapper.toApi(apiDTOCreate);
        assertEquals("GET", api.getMethod());
        assertEquals("/api/v1/users", api.getPath());
    }
}