package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserTypeMapperTest extends UserTypeMapper {
    /**
     * param userType is null
     */
    @Test
    void toUserTypeDTO_UTCID01() {
        assertNull(UserTypeMapper.toUserTypeDTO(null));
    }

    /**
     * param userType is not null
     */
    @Test
    void toUserTypeDTO_UTCID02() {
        //given
        UserType userType = UserType.builder().id(1).type("CUSTOMER").build();
        //when
        UserTypeDTO userTypeDTO = UserTypeMapper.toUserTypeDTO(userType);
        //then
        assertEquals(1, userTypeDTO.getId());
        assertEquals("CUSTOMER", userTypeDTO.getType());
    }
}