package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Api;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.api.ApiDTO;
import com.gasstation.managementsystem.model.dto.api.ApiDTOCreate;
import com.gasstation.managementsystem.model.dto.api.ApiDTOUpdate;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import com.gasstation.managementsystem.model.mapper.ApiMapper;
import com.gasstation.managementsystem.repository.ApiRepository;
import com.gasstation.managementsystem.utils.OptionalValidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApiServiceImplTest {
    @Mock
    private ApiRepository apiRepository;

    @Mock
    private OptionalValidate optionalValidate;

    @InjectMocks
    private ApiServiceImpl apiService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * param o1.getMethod().compareToIgnoreCase(o2.getMethod())
     */
    @Test
    void findAll_UTCID01() {
        List<Api> mockRepository = new ArrayList<>();
        List<ApiDTO> mockResult = new ArrayList<>();
        mockData_UCTID01(mockRepository, mockResult);
        Mockito.when(apiRepository.findAll()).thenReturn(mockRepository);
        List<ApiDTO> listResultService = (List<ApiDTO>) apiService.findAll().get("data");
        for (int i = 0; i < listResultService.size(); i++) {
            ApiDTO o1 = mockResult.get(i);
            ApiDTO o2 = listResultService.get(i);
            assertEquals(o1, o2);
        }
    }

    private void mockData_UCTID01(List<Api> mockRepository, List<ApiDTO> mockResult) {
        Set<UserType> userTypes = new HashSet<>();
        userTypes.add(UserType.builder().id(2).type("OWNER").build());
        for (int i = 1; i <= 10; i++) {
            Api api = Api.builder()
                    .id(i)
                    .method("GET")
                    .path("/api/v1/users")
                    .userTypeList(userTypes).build();
            mockRepository.add(api);
            mockResult.add(ApiMapper.toApiDTO(api));
        }
    }

    /**
     * param o1.getPath().compareToIgnoreCase(o2.getPath()) # 0
     */
    @Test
    void findAll_UTCID02() {
        List<Api> mockRepository = new ArrayList<>();
        List<ApiDTO> mockResult = new ArrayList<>();
        mockData_UCTID02(mockRepository, mockResult);
        Mockito.when(apiRepository.findAll()).thenReturn(mockRepository);
        List<ApiDTO> listResultService = (List<ApiDTO>) apiService.findAll().get("data");
    }

    private void mockData_UCTID02(List<Api> mockRepository, List<ApiDTO> mockResult) {
        Set<UserType> userTypes = new HashSet<>();
        userTypes.add(UserType.builder().id(2).type("OWNER").build());
        for (int i = 1; i <= 10; i++) {
            Api api = Api.builder()
                    .id(i)
                    .method("GET")
                    .path("/api/v1/users" + i)
                    .userTypeList(userTypes).build();
            mockRepository.add(api);
            mockResult.add(ApiMapper.toApiDTO(api));
        }
    }

    @Test
    void findAllByUserTypeId() {
        List<Api> mockRepository = new ArrayList<>();
        List<ApiDTO> mockResult = new ArrayList<>();
        mockData_UCTID03(mockRepository, mockResult);
        Mockito.when(apiRepository.findAllByUserTypeId(2)).thenReturn(mockRepository);
        List<ApiDTO> listResultService = (List<ApiDTO>) apiService.findAllByUserTypeId(2).get("data");
        for (int i = 0; i < mockResult.size(); i++) {
            ApiDTO o1 = mockResult.get(i);
            ApiDTO o2 = listResultService.get(i);
            assertEquals(o1, o2);
        }
    }

    private void mockData_UCTID03(List<Api> mockRepository, List<ApiDTO> mockResult) {
        Set<UserType> userTypes = new HashSet<>();
        userTypes.add(UserType.builder().id(2).type("OWNER").build());
        for (int i = 1; i <= 10; i++) {
            Api api = Api.builder()
                    .id(-1)
                    .method("GET")
                    .path("/api/v1/fuels")
                    .userTypeList(userTypes).build();
            mockRepository.add(api);
            mockResult.add(ApiMapper.toApiDTO(api));
        }
    }

    @Test
    void findById() throws CustomNotFoundException {
        Set<UserType> userTypes = new HashSet<>();
        userTypes.add(UserType.builder().id(2).type("OWNER").build());
        Api mockRepository = Api.builder()
                .id(1)
                .method("GET")
                .path("/api/v1/fuels")
                .userTypeList(userTypes).build();
        ApiDTO mockResult = ApiMapper.toApiDTO(mockRepository);
        Mockito.when(optionalValidate.getApiById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, apiService.findById(1));
    }

    @Test
    void create_UTCID01() throws CustomNotFoundException, CustomDuplicateFieldException {
        Set<UserType> userTypes = new HashSet<>();
        userTypes.add(UserType.builder().id(2).type("OWNER").build());
        UserType userType = UserType.builder().id(2).type("OWNER").build();
        Api mockRepository = Api.builder()
                .id(1)
                .method("GET")
                .path("/api/v1/fuels")
                .userTypeList(userTypes).build();
        ApiDTO mockResult = ApiMapper.toApiDTO(mockRepository);
        List<Integer> userTypeIds = new ArrayList<>();
        userTypeIds.add(1);
        userTypeIds.add(2);
        ApiDTOCreate apiDTOCreate = ApiDTOCreate.builder()
                .method("GET")
                .path("/api/v1/fuels")
                .accessibleUserTypes(userTypeIds)
                .build();
        Mockito.when(optionalValidate.getUserTypeById(2)).thenReturn(userType);
        Mockito.when(apiRepository.save(Mockito.any(Api.class))).thenReturn(mockRepository);
        assertEquals(mockResult, apiService.create(apiDTOCreate));
    }

    /**
     * check duplicate
     */
    @Test
    void create_UTCID02() throws CustomDuplicateFieldException, CustomNotFoundException {
        Set<UserType> userTypes = new HashSet<>();
        userTypes.add(UserType.builder().id(2).type("OWNER").build());
        UserType userType = UserType.builder().id(2).type("OWNER").build();
        Api mockRepository = Api.builder()
                .id(1)
                .method("GET")
                .path("/api/v1/fuels")
                .userTypeList(userTypes).build();
        List<Integer> userTypeIds = new ArrayList<>();
        userTypeIds.add(1);
        userTypeIds.add(2);
        ApiDTOCreate apiDTOCreate = ApiDTOCreate.builder()
                .method("GET")
                .path("/api/v1/fuels")
                .accessibleUserTypes(userTypeIds)
                .build();
        Optional<Api> apiOptional = Optional.of(mockRepository);
        Mockito.when(optionalValidate.getUserTypeById(2)).thenReturn(userType);
        Mockito.when(apiRepository.save(Mockito.any(Api.class))).thenReturn(mockRepository);
        Mockito.when(apiRepository.findByPathAndMethod(
                Api.PREFIX + "/api/v1/fuels", "GET")).thenReturn(apiOptional);
        assertThrows(CustomDuplicateFieldException.class, () -> {
            apiService.create(apiDTOCreate);
        });
    }

    @Test
    void update() throws CustomNotFoundException, CustomDuplicateFieldException {
        Set<UserType> userTypes = new HashSet<>();
        userTypes.add(UserType.builder().id(2).type("OWNER").build());
        UserType userType = UserType.builder().id(2).type("OWNER").build();
        Api mockRepository = Api.builder()
                .id(1)
                .method("GET")
                .path("/api/v1/fuels")
                .userTypeList(userTypes).build();

        List<UserTypeDTO> userTypeList = new ArrayList<>();
        userTypeList.add(UserTypeDTO.builder().id(2).type("OWNER").build());
        userTypeList.add(UserTypeDTO.builder().id(3).type("OWNER").build());

        List<Integer> userTypeIds = new ArrayList<>();
        userTypeIds.add(2);
        userTypeIds.add(3);

        ApiDTO mockResult = ApiMapper.toApiDTO(mockRepository);
        mockResult.setAccessibleUserTypes(userTypeList);

        ApiDTOUpdate apiDTOUpdate = ApiDTOUpdate.builder()
                .accessibleUserTypes(userTypeIds)
                .build();

        Mockito.when(optionalValidate.getApiById(1)).thenReturn(mockRepository);
        Mockito.when(optionalValidate.getUserTypeById(2)).thenReturn(userType);
        Mockito.when(apiRepository.save(Mockito.any(Api.class))).thenReturn(mockRepository);
        assertEquals(mockResult.getId(), apiService.update(1, apiDTOUpdate).getId());
        assertEquals(mockResult.getMethod(), apiService.update(1, apiDTOUpdate).getMethod());
        assertEquals(mockResult.getPath(), apiService.update(1, apiDTOUpdate).getPath());
    }

    @Test
    void delete() throws CustomNotFoundException {
        Set<UserType> userTypes = new HashSet<>();
        userTypes.add(UserType.builder().id(2).type("OWNER").build());
        Api mockRepository = Api.builder()
                .id(1)
                .method("GET")
                .path("/api/v1/fuels")
                .userTypeList(userTypes).build();
        ApiDTO mockResult = ApiMapper.toApiDTO(mockRepository);
        Mockito.when(optionalValidate.getApiById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, apiService.delete(1));
    }

    @Test
    void saveAll() {
        List<Api> mockRepository = new ArrayList<>();
        List<ApiDTO> mockResult = new ArrayList<>();
        List<ApiDTOCreate> apiDTOCreateList = new ArrayList<>();
        mockData_UCTID04(mockRepository, mockResult, apiDTOCreateList);
        Mockito.when(apiRepository.saveAll(mockRepository)).thenReturn(mockRepository);
        apiService.saveAll(apiDTOCreateList);
    }

    private void mockData_UCTID04(List<Api> mockRepository, List<ApiDTO> mockResult,
                                  List<ApiDTOCreate> apiDTOCreateList) {
        Set<UserType> userTypes = new HashSet<>();
        userTypes.add(UserType.builder().id(2).type("OWNER").build());
        for (int i = 1; i <= 10; i++) {
            Api api = Api.builder()
                    .id(i)
                    .method("GET")
                    .path("/api/v1/users")
                    .userTypeList(userTypes).build();
            ApiDTOCreate apiDTOCreate = ApiDTOCreate.builder()
                    .method("GET")
                    .path("/api/v1/users")
                    .build();
            mockRepository.add(api);
            mockResult.add(ApiMapper.toApiDTO(api));
            apiDTOCreateList.add(apiDTOCreate);
        }
    }


    @Test
    void deleteAll() {
        apiService.deleteAll();
    }
}