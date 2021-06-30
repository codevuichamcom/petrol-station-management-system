package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.Api;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.api.ApiDTO;
import com.gasstation.managementsystem.model.dto.api.ApiDTOCreate;
import com.gasstation.managementsystem.model.dto.api.ApiDTOUpdate;
import com.gasstation.managementsystem.service.ApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = Api.PREFIX)
@CrossOrigin
@Tag(name = "Api", description = "API for api")
@RequiredArgsConstructor
public class ApiController {
    private final ApiService apiService;
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Operation(summary = "View All api")
    @GetMapping("/apis")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        if (pageSize != null) {
            return apiService.findAll(PageRequest.of(pageIndex - 1, pageSize));
        }
        return apiService.findAll();
    }

    @Operation(summary = "Find api by id")
    @GetMapping("/apis/{id}")
    public ApiDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return apiService.findById(id);
    }

    @Operation(summary = "Update api by id")
    @PutMapping("/apis/{id}")
    public ApiDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody ApiDTOUpdate apiDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException {
        return apiService.update(id, apiDTOUpdate);
    }


    @PostMapping("endpoints")
    public void getEndpoints() {
        apiService.deleteAll();
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping
                .getHandlerMethods();
        List<ApiDTOCreate> apiDTOCreateList = new ArrayList<>();
        map.forEach((key, value) -> {
            String all = key.toString();
            String[] parts = all.split("\\s+");
            String method = parts[0].replace("{", "").trim();
            String path = parts[1].replace("[", "").replace("]}", "").replace("/{id}", "").trim();
            String name = value.getMethod().getName();
            name += path.substring(path.lastIndexOf("/") + 1);
            String route = path.substring(path.lastIndexOf("/") + 1);
            if (method.equalsIgnoreCase("GET")) {
                name = "View " + route;
            } else if (method.equalsIgnoreCase("POST")) {
                name = "Create new " + route;
            } else if (method.equalsIgnoreCase("PUT")) {
                name = "Update " + route;
            } else if (method.equalsIgnoreCase("DELETE")) {
                name = "Delete " + route;
            }
            if (!method.equals("")) {
                if (!value.getMethod().getName().equalsIgnoreCase("getOne") && !route.startsWith("api") && !route.startsWith("endpoints")) {
                    apiDTOCreateList.add(ApiDTOCreate.builder().name(name).method(method).path("/" + route).accessibleUserTypes(new ArrayList<>()).build());
                }
            }
        });
        apiService.saveAll(apiDTOCreateList.stream().sorted((o1, o2) -> {
            if (o1.getPath().compareToIgnoreCase(o2.getPath()) > 0) {
                return 1;
            } else if (o1.getPath().compareToIgnoreCase(o2.getPath()) < 0) {
                return -1;
            } else {
                return o1.getMethod().compareToIgnoreCase(o2.getMethod());
            }
        }).collect(Collectors.toList()));
    }
}
