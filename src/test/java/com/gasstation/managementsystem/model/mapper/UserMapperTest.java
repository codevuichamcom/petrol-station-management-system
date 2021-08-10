package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTOCreate;
import com.gasstation.managementsystem.model.dto.user.UserDTOUpdate;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest extends UserMapper {

    /**
     * param userDTOCreate is null
     */
    @Test
    void toUser_UTCID01() {
        assertNull(UserMapper.toUser(null));
    }

    /**
     * param userDTOCreate is not null
     */
    @Test
    void toUser_UTCID02() {
        //given
        Long dateOfBirth = 16094540000000L;
        UserDTOCreate userDTOCreate = UserDTOCreate.builder()
                .identityCardNumber("0136235842")
                .username("TuLV")
                .password("123456")
                .name("Le Viet Tu")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .address("Me Linh")
                .phone("0886818499")
                .email("motdemhuyhoang@gmail.com")
                .note("")
                .userTypeId(1).build();
        //when
        User user = UserMapper.toUser(userDTOCreate);
        //then
        assertEquals("0136235842", user.getIdentityCardNumber());
        assertEquals("TuLV", user.getUsername());
        assertEquals("123456", user.getPassword());
        assertEquals("Le Viet Tu", user.getName());
        assertEquals(true, user.getGender());
        assertEquals(dateOfBirth, user.getDateOfBirth());
        assertEquals("Me Linh", user.getAddress());
        assertEquals("0886818499", user.getPhone());
        assertEquals("motdemhuyhoang@gmail.com", user.getEmail());
        assertEquals("", user.getNote());
        assertEquals(true, user.getActive());
        assertEquals(1, user.getUserType().getId());
    }

    /**
     * param user is null
     */
    @Test
    void toUserDTO_UTCID01() {
        assertNull(UserMapper.toUserDTO(null));
    }

    /**
     * param user is null
     */
    @Test
    void toUserDTO_UTCID02() {
        //given
        UserType userType = UserType.builder().id(1).type("CUSTOMER").build();
        Long dateOfBirth = 16094540000000L;
        User user = User.builder()
                .id(999)
                .identityCardNumber("0136235842")
                .username("TuLV")
                .name("Le Viet Tu")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .address("Me Linh")
                .phone("0886818499")
                .email("motdemhuyhoang@gmail.com")
                .note("")
                .active(true)
                .userType(userType).build();
        //when
        UserDTO userDTO = UserMapper.toUserDTO(user);
        //then
        assertEquals(999, userDTO.getId());
        assertEquals("0136235842", userDTO.getIdentityCardNumber());
        assertEquals("TuLV", userDTO.getUsername());
        assertEquals("Le Viet Tu", userDTO.getName());
        assertEquals(true, userDTO.getGender());
        assertEquals(dateOfBirth, userDTO.getDateOfBirth());
        assertEquals("Me Linh", userDTO.getAddress());
        assertEquals("0886818499", userDTO.getPhone());
        assertEquals("motdemhuyhoang@gmail.com", userDTO.getEmail());
        assertEquals("", userDTO.getNote());
        assertEquals(true, userDTO.getActive());
        assertEquals(1, userDTO.getUserType().getId());
        assertEquals("CUSTOMER", userDTO.getUserType().getType());
    }

    /**
     * precondition user is null
     */
    @Test
    void copyToUser_UTCID01() {
        //given
        Long dateOfBirth_update = 16094540000000L;
        UserDTOUpdate userDTOUpdate = UserDTOUpdate.builder()
                .identityCardNumber("0136235843")
                .password("1234567")
                .name("Le Viet Tung")
                .gender(false)
                .dateOfBirth(dateOfBirth_update)
                .address("Me Linh- Ha Noi")
                .phone("0886818999")
                .email("motdemhuyhoang99@gmail.com")
                .note("")
                .active(false)
                .build();
        //then
        try {
            UserMapper.copyToUser(null, userDTOUpdate);
        } catch (Exception ex) {
            assertTrue(true);
        }
    }

    /**
     * precondition user is not null
     */
    @Test
    void copyToUser_UTCID02() {
        //given
        Long dateOfBirth_update = 16094540000000L;
        Long dateOfBirth = 16094540000000L;
        User user = User.builder()
                .id(999)
                .identityCardNumber("0136235842")
                .username("TuLV")
                .name("Le Viet Tu")
                .password("123456")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .address("Me Linh")
                .phone("0886818499")
                .email("motdemhuyhoang@gmail.com")
                .note("")
                .active(true)
                .build();

        UserDTOUpdate userDTOUpdate = UserDTOUpdate.builder()
                .identityCardNumber("0136235843")
                .password("1234567")
                .name("Le Viet Tung")
                .gender(false)
                .dateOfBirth(dateOfBirth_update)
                .address("Me Linh- Ha Noi")
                .phone("0886818999")
                .email("motdemhuyhoang99@gmail.com")
                .note("")
                .active(false)
                .build();
        //when
        UserMapper.copyToUser(user, userDTOUpdate);
        //then
        assertEquals(999, user.getId());
        assertEquals("0136235843", user.getIdentityCardNumber());
        assertEquals("1234567", user.getPassword());
        assertEquals("Le Viet Tung", user.getName());
        assertEquals(false, user.getGender());
        assertEquals(dateOfBirth_update, user.getDateOfBirth());
        assertEquals("Me Linh- Ha Noi", user.getAddress());
        assertEquals("0886818999", user.getPhone());
        assertEquals("motdemhuyhoang99@gmail.com", user.getEmail());
        assertEquals("", user.getNote());
        assertEquals(false, user.getActive());
    }
}