package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.card.CardDTOCreate;
import com.gasstation.managementsystem.model.dto.card.CardDTOUpdate;
import com.gasstation.managementsystem.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Card", description = "API for card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @Operation(summary = "View All card")
    @GetMapping("/cards")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        if (pageSize != null) {
            return cardService.findAll(PageRequest.of(pageIndex - 1, pageSize, Sort.by(Sort.Direction.ASC, "id")));
        }
        return cardService.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Operation(summary = "Find card by id")
    @GetMapping("/cards/{id}")
    public CardDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return cardService.findById(id);
    }

    @Operation(summary = "Create new card")
    @PostMapping("/cards")
    public CardDTO create(@Valid @RequestBody CardDTOCreate cardDTOCreate) throws CustomNotFoundException, CustomDuplicateFieldException {
        return cardService.create(cardDTOCreate);
    }

    @Operation(summary = "Update card by id")
    @PutMapping("/cards/{id}")
    public CardDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody CardDTOUpdate cardDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException {
        return cardService.update(id, cardDTOUpdate);
    }

    @Operation(summary = "Delete card by id")
    @DeleteMapping("/cards/{id}")
    public CardDTO delete(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return cardService.delete(id);
    }
}
