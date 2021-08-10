package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import com.gasstation.managementsystem.model.mapper.UserTypeMapper;
import com.gasstation.managementsystem.repository.UserTypeRepository;
import com.gasstation.managementsystem.utils.OptionalValidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTypeServiceImplTest {

    @Mock
    private UserTypeRepository userTypeRepository;

    @Mock
    private OptionalValidate optionalValidate;

    @InjectMocks
    private UserTypeServiceImpl userTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * no param
     */
    @Test
    void findAll() {
        List<UserType> mockRepository = new ArrayList<>();
        List<UserTypeDTO> mockResult = new ArrayList<>();
        mockData(mockRepository, mockResult);

        //giả lập
        Mockito.when(userTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(mockRepository);
        List<UserTypeDTO> listResultService = (List<UserTypeDTO>) userTypeService.findAll().get("data");
        for (int i = 0; i < listResultService.size(); i++) {
            UserTypeDTO o1 = mockResult.get(i);
            UserTypeDTO o2 = listResultService.get(i);
            assertEquals(o1, o2);
        }
    }

    private void mockData(List<UserType> mockRepository, List<UserTypeDTO> mockResult) {
        for (int i = 1; i <= 10; i++) {
            UserType userType = UserType.builder().id(i).type("type" + i).build();
            mockRepository.add(userType);
            mockResult.add(UserTypeMapper.toUserTypeDTO(userType));
        }
    }

    @Test
    void findById() throws CustomNotFoundException {
        UserType mockRepository = UserType.builder().id(1).type("ADMIN").build();
        UserTypeDTO mockResult = UserTypeDTO.builder().id(1).type("ADMIN").build();
        Mockito.when(optionalValidate.getUserTypeById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, userTypeService.findById(1));
    }

    @Test
    void save() {
        UserType mockRepository = UserType.builder().id(1).type("ADMIN").build();
        UserTypeDTO mockResult = UserTypeDTO.builder().id(1).type("ADMIN").build();
        Mockito.when(userTypeRepository.save(Mockito.any(UserType.class))).thenReturn(mockRepository);
        assertEquals(mockResult, userTypeService.save(mockRepository));
    }

    @Test
    void delete() throws CustomNotFoundException {
        UserType mockRepository = UserType.builder().id(1).type("ADMIN").build();
        UserTypeDTO mockResult = UserTypeDTO.builder().id(1).type("ADMIN").build();
        Mockito.when(optionalValidate.getUserTypeById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, userTypeService.delete(1));
    }
}