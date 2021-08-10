package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTOCreate;
import com.gasstation.managementsystem.model.dto.user.UserDTOUpdate;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import com.gasstation.managementsystem.model.mapper.UserMapper;
import com.gasstation.managementsystem.repository.UserRepository;
import com.gasstation.managementsystem.repository.UserTypeRepository;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.OptionalValidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserTypeRepository userTypeRepository;

    @Mock
    private PasswordEncoder bcryptEncoder;

    @Mock
    private OptionalValidate optionalValidate;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * param Sort
     */
    @Test
    void findAll() {
        List<User> mockRepository = new ArrayList<>();
        List<UserDTO> mockResult = new ArrayList<>();
        mockData(mockRepository, mockResult);

        //giả lập
        Mockito.when(userRepository.findAll()).thenReturn(mockRepository);

        List<UserDTO> listResultService = (List<UserDTO>) userService.findAll().get("data");
        for (int i = 0; i < listResultService.size(); i++) {
            UserDTO o1 = mockResult.get(i);
            UserDTO o2 = listResultService.get(i);
            assertEquals(o1, o2);
        }
    }

    private void mockData(List<User> mockRepository, List<UserDTO> mockResult) {
        Long dateOfBirth = 16094340000000L;
        for (int i = 1; i <= 10; i++) {
            User user = User.builder().
                    id(i).username("user_" + i)
                    .gender(false).active(true)
                    .userType(UserType.builder().id(3).type("CUSTOMER").build())
                    .password("abcd")
                    .dateOfBirth(dateOfBirth)
                    .build();
            mockRepository.add(user);
            mockResult.add(UserMapper.toUserDTO(user));
        }
    }

    @Test
    void findById() throws CustomNotFoundException {
        Long dateOfBirth = 16094340000000L;
        User mockRepository = User.builder().id(1).username("user_" + 1).dateOfBirth(dateOfBirth).gender(false).active(false)
                .userType(UserType.builder().id(3).type("CUSTOMER").build()).build();
        UserDTO mockResult = UserDTO.builder().id(1).username("user_" + 1).dateOfBirth(dateOfBirth).gender(false).active(false)
                .userType(UserTypeDTO.builder().id(3).type("CUSTOMER").build()).build();
        Mockito.when(optionalValidate.getUserById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, userService.findById(1));
    }

    /**
     * param UserType is not ADMIN
     */
    @Test
    void create_UTCID01() throws CustomNotFoundException, CustomBadRequestException, CustomDuplicateFieldException {
        Long dateOfBirth = 16094340000000L;
        User mockRepository = User.builder().id(1)
                .username("user_" + 1)
                .dateOfBirth(dateOfBirth)
                .gender(false).active(false)
                .userType(UserType.builder().id(3).type("CUSTOMER").build())
                .password("abcd")
                .build();
        UserDTOCreate mockRepositoryCreate = UserDTOCreate.builder().username("user_" + 1).dateOfBirth(dateOfBirth).gender(false)
                .userTypeId(3)
                .password("123456")
                .build();
        UserDTO mockResult = UserDTO.builder().id(1).username("user_" + 1)
                .dateOfBirth(dateOfBirth)
                .gender(false).active(false)
                .userType(UserTypeDTO.builder().id(3).type("CUSTOMER").build())
                .build();
        Mockito.when(bcryptEncoder.encode(Mockito.anyString())).thenReturn("abcd");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(mockRepository);
        assertEquals(mockResult, userService.create(mockRepositoryCreate));
    }

    /**
     * param UserType is ADMIN
     */
    @Test
    void create_UTCID02() throws CustomNotFoundException, CustomBadRequestException, CustomDuplicateFieldException {
        Long dateOfBirth = 16094340000000L;
        User mockRepository = User.builder().id(1)
                .username("user_" + 1)
                .dateOfBirth(dateOfBirth)
                .gender(false).active(false)
                .userType(UserType.builder().id(1).type("ADMIN").build())
                .password("abcd")
                .build();
        UserDTOCreate mockRepositoryCreate = UserDTOCreate.builder().username("user_" + 1).dateOfBirth(dateOfBirth).gender(false)
                .userTypeId(1)
                .password("123456")
                .build();
        Mockito.when(bcryptEncoder.encode(Mockito.anyString())).thenReturn("abcd");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(mockRepository);
        assertThrows(CustomBadRequestException.class, () -> {
            userService.create(mockRepositoryCreate);
        });
    }

    /**
     * checkDuplicateField username
     */
    @Test
    void create_UTCID03() throws CustomDuplicateFieldException {
        Long dateOfBirth = 16094340000000L;
        User user = User.builder().id(1)
                .identityCardNumber("0136235943")
                .username("user_1")
                .dateOfBirth(dateOfBirth)
                .gender(false).active(false)
                .userType(UserType.builder().id(2).type("OWNER").build())
                .password("abcd")
                .email("motdemhuyhoang99@gmail.com")
                .phone("0886818939")
                .build();
        UserDTOCreate userDTOCreate = UserDTOCreate.builder().
                username("user_" + 1)
                .identityCardNumber("0136235943")
                .dateOfBirth(dateOfBirth)
                .gender(false)
                .userTypeId(1)
                .password("123456")
                .email("motdemhuyhoang99@gmail.com")
                .userTypeId(2)
                .phone("0886818939")
                .build();

        Mockito.when(bcryptEncoder.encode(Mockito.anyString())).thenReturn("abcd");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        Mockito.when(userRepository.findByUsername("user_1")).thenReturn(user);
        Mockito.when(userRepository.findByIdentityCardNumber("0136235943")).thenReturn(user);
        Mockito.when(userRepository.findByEmail("motdemhuyhoang99@gmail.com")).thenReturn(user);
        Mockito.when(userRepository.findByPhone("0886818939")).thenReturn(user);

        assertThrows(CustomDuplicateFieldException.class, () -> {
            userService.create(userDTOCreate);
        });
    }

    /**
     * checkDuplicateField  identityCardNumber
     */
    @Test
    void create_UTCID04() throws CustomDuplicateFieldException {
        Long dateOfBirth = 16094340000000L;
        User user = User.builder().id(1)
                .identityCardNumber("0136235943")
                .username("user_1")
                .dateOfBirth(dateOfBirth)
                .gender(false).active(false)
                .userType(UserType.builder().id(2).type("OWNER").build())
                .password("abcd")
                .build();
        UserDTOCreate userDTOCreate = UserDTOCreate.builder().
                username("user_2")
                .identityCardNumber("0136235943")
                .dateOfBirth(dateOfBirth)
                .gender(false)
                .userTypeId(1)
                .password("123456")
                .userTypeId(2)
                .build();

        Mockito.when(bcryptEncoder.encode(Mockito.anyString())).thenReturn("abcd");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(userRepository.findByIdentityCardNumber("0136235943")).thenReturn(user);

        assertThrows(CustomDuplicateFieldException.class, () -> {
            userService.create(userDTOCreate);
        });
    }

    /**
     * checkDuplicateField phone
     */
    @Test
    void create_UTCID05() throws CustomDuplicateFieldException {
        Long dateOfBirth = 16094340000000L;
        User user = User.builder().id(1)
                .identityCardNumber("0136235943")
                .username("user_1")
                .dateOfBirth(dateOfBirth)
                .gender(false).active(false)
                .userType(UserType.builder().id(2).type("OWNER").build())
                .password("abcd")
                .email("motdemhuyhoang99@gmail.com")
                .phone("0886818939")
                .build();
        UserDTOCreate userDTOCreate = UserDTOCreate.builder().
                username("user_2")
                .identityCardNumber("0136235743")
                .dateOfBirth(dateOfBirth)
                .gender(false)
                .userTypeId(1)
                .password("123456")
                .email("motdemhuyhoang91@gmail.com")
                .userTypeId(2)
                .phone("0886818939")
                .build();

        Mockito.when(bcryptEncoder.encode(Mockito.anyString())).thenReturn("abcd");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(userRepository.findByPhone("0886818939")).thenReturn(user);

        assertThrows(CustomDuplicateFieldException.class, () -> {
            userService.create(userDTOCreate);
        });
    }

    /**
     * checkDuplicateField email
     */
    @Test
    void create_UTCID06() throws CustomDuplicateFieldException {
        Long dateOfBirth = 16094340000000L;
        User user = User.builder().id(1)
                .identityCardNumber("0136235943")
                .username("user_1")
                .dateOfBirth(dateOfBirth)
                .gender(false).active(false)
                .userType(UserType.builder().id(2).type("OWNER").build())
                .password("abcd")
                .email("motdemhuyhoang99@gmail.com")
                .phone("0886818939")
                .build();
        UserDTOCreate userDTOCreate = UserDTOCreate.builder().
                username("user_2")
                .identityCardNumber("0136235443")
                .dateOfBirth(dateOfBirth)
                .gender(false)
                .userTypeId(1)
                .password("123456")
                .email("motdemhuyhoang99@gmail.com")
                .userTypeId(2)
                .phone("088684418939")
                .build();

        Mockito.when(bcryptEncoder.encode(Mockito.anyString())).thenReturn("abcd");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(userRepository.findByEmail("motdemhuyhoang99@gmail.com")).thenReturn(user);
        assertThrows(CustomDuplicateFieldException.class, () -> {
            userService.create(userDTOCreate);
        });
    }


    /**
     * param UserType is ADMIN , email is null
     */
    @Test
    void update_UTCID01() throws CustomNotFoundException, CustomBadRequestException, CustomDuplicateFieldException {
        //given
        Long dateOfBirth = 16094340000000L;
        Long dateOfBirth_update = 16094540000000L;
        User mockRepository = User.builder().id(1)
                .username("user_" + 1)
                .identityCardNumber("0136235943")
                .password("123456")
                .name("Le Viet Tu")
                .gender(false)
                .dateOfBirth(dateOfBirth)
                .address("Me Linh")
                .phone("0886818939")
                .email("motdemhuyhoang@gmail.com")
                .note("")
                .active(false)
                .userType(UserType.builder().id(1).type("ADMIN").build())
                .build();

        UserDTOUpdate mockRepositoryUpdate = UserDTOUpdate.builder()
                .identityCardNumber("0136235843")
                .password("1234567")
                .name("Le Viet Tung")
                .gender(false)
                .dateOfBirth(dateOfBirth_update)
                .address("Me Linh- Ha Noi")
                .phone("0886818999")
                .note("")
                .active(false)
                .userTypeId(1)
                .build();

        Mockito.when(bcryptEncoder.encode(Mockito.anyString())).thenReturn("1234567");
        Mockito.when(bcryptEncoder.encode(Mockito.anyString())).thenReturn("123456");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(mockRepository);
        Mockito.when(optionalValidate.getUserById(1)).thenReturn(mockRepository);

        assertThrows(CustomBadRequestException.class, () -> {
            userService.update(1, mockRepositoryUpdate);
        });
    }

    /**
     * param UserType not ADMIN , email is not null
     */
    @Test
    void update_UTCID02() throws CustomNotFoundException, CustomBadRequestException, CustomDuplicateFieldException {
        //given
        Long dateOfBirth = 16094340000000L;
        Long dateOfBirth_update = 16094540000000L;
        UserType userType = UserType.builder().id(2).type("OWNER").build();
//        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(2).type("OWNER").build();
        User mockRepository = User.builder().id(1)
                .username("user_" + 1)
                .identityCardNumber("0136235943")
                .password("123456")
                .name("Le Viet Tu")
                .gender(false)
                .dateOfBirth(dateOfBirth)
                .address("Me Linh")
                .phone("0886818939")
                .email("motdemhuyhoang99@gmail.com")
                .note("")
                .userType(UserType.builder().id(2).type("OWNER").build())
                .active(false)
                .build();

        UserDTOUpdate mockRepositoryUpdate = UserDTOUpdate.builder()
                .identityCardNumber("0136235943")
                .password("1234567")
                .name("Le Viet Tung")
                .gender(false)
                .dateOfBirth(dateOfBirth_update)
                .email("motdemhuyhoang99@gmail.com")
                .address("Me Linh- Ha Noi")
                .phone("0886818939")
                .userTypeId(2)
                .note("")
                .active(false)
                .build();

        UserDTO mockResult = UserDTO.builder().id(1)
                .username("user_" + 1)
                .identityCardNumber("0136235943")
                .name("Le Viet Tung")
                .gender(false)
                .dateOfBirth(dateOfBirth_update)
                .email("motdemhuyhoang99@gmail.com")
                .address("Me Linh- Ha Noi")
                .phone("0886818939")
                .note("")
                .userType(UserTypeDTO.builder().id(2).type("OWNER").build())
                .active(false)
                .build();
        Mockito.when(bcryptEncoder.encode(Mockito.anyString())).thenReturn("1234567");
        Mockito.when(bcryptEncoder.encode(Mockito.anyString())).thenReturn("123456");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(mockRepository);

        Mockito.when(userTypeRepository.save(Mockito.any(UserType.class))).thenReturn(userType);
        Mockito.when(optionalValidate.getUserTypeById(2)).thenReturn(userType);

        Mockito.when(optionalValidate.getUserById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, userService.update(1, mockRepositoryUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        Long dateOfBirth = 16094340000000L;
        User mockRepository = User.builder().id(1)
                .username("user_" + 1)
                .identityCardNumber("0136235943")
                .password("123456")
                .name("Le Viet Tu")
                .gender(false)
                .dateOfBirth(dateOfBirth)
                .address("Me Linh")
                .phone("0886818939")
                .email("motdemhuyhoang@gmail.com")
                .note("")
                .active(false)
                .build();

        UserDTO mockResult = UserDTO.builder().id(1).username("user_" + 1)
                .username("user_" + 1)
                .identityCardNumber("0136235943")
                .name("Le Viet Tu")
                .gender(false)
                .dateOfBirth(dateOfBirth)
                .address("Me Linh")
                .phone("0886818939")
                .email("motdemhuyhoang@gmail.com")
                .note("")
                .active(false)
                .build();
        Mockito.when(optionalValidate.getUserById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, userService.delete(1));
    }

    @Test
    void findByUserName() throws CustomNotFoundException {
        Long dateOfBirth = 16094340000000L;

        User mockRepository = User.builder().id(1).username("user_1").dateOfBirth(dateOfBirth).gender(false).active(false)
                .build();
        UserDTO mockResult = UserDTO.builder().id(1).username("user_1").dateOfBirth(dateOfBirth).gender(false).active(false)
                .build();
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(mockRepository);
        Mockito.when(userRepository.findByUsername("user_1")).thenReturn(mockRepository);
        assertEquals(mockResult, userService.findByUserName("user_1"));
    }

    @Test
    void findByUserTypeId() throws CustomNotFoundException {
        UserType userType = UserType.builder().id(2).type("OWNER").build();
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(2).type("OWNER").build();
        Long dateOfBirth = 16094340000000L;

        User mockRepository = User.builder().id(1)
                .userType(userType)
                .username("user_1").dateOfBirth(dateOfBirth).gender(false).active(false)
                .build();
        UserDTO mockResult = UserDTO.builder().id(1)
                .userType(userTypeDTO)
                .username("user_1").dateOfBirth(dateOfBirth).gender(false).active(false)
                .build();

        List<User> userslList = new ArrayList<>();
        userslList.add(mockRepository);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(mockRepository);
        Mockito.when(userRepository.findByUserTypeId(2)).thenReturn(userslList);

        List<UserDTO> userDTOList = (List<UserDTO>) userService.findByUserTypeId(2).get("data");
        assertEquals(mockResult, userDTOList.get(0));
    }
}