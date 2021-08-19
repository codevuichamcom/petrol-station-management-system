package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.card.CardDTOCreate;
import com.gasstation.managementsystem.model.dto.card.CardDTOFilter;
import com.gasstation.managementsystem.model.dto.card.CardDTOUpdate;
import com.gasstation.managementsystem.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.UUID;

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
                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                          @RequestParam(name = "accountsPayableFrom", required = false) Double accountsPayableFrom,
                                          @RequestParam(name = "accountsPayableTo", required = false) Double accountsPayableTo,
                                          @RequestParam(name = "availableBalanceFrom", required = false) Double availableBalanceFrom,
                                          @RequestParam(name = "availableBalanceTo", required = false) Double availableBalanceTo,
                                          @RequestParam(name = "statuses", required = false) String[] statuses,
                                          @RequestParam(name = "customerName", required = false) String customerName) {
        CardDTOFilter filter = CardDTOFilter.builder()
                .pageIndex(pageIndex)
                .pageSize(pageSize)
                .accountsPayableFrom(accountsPayableFrom)
                .accountsPayableTo(accountsPayableTo)
                .availableBalanceFrom(availableBalanceFrom)
                .availableBalanceTo(availableBalanceTo)
                .statuses(statuses)
                .customerName(customerName).build();
        return cardService.findAll(filter);
    }

    @Operation(summary = "Find card by id")
    @GetMapping("/cards/{id}")
    public CardDTO getOne(@PathVariable(name = "id") UUID id) throws CustomNotFoundException {
        return cardService.findById(id);
    }

    @Operation(summary = "Create new card")
    @PostMapping("/cards")
    public CardDTO create(@Valid @RequestBody CardDTOCreate cardDTOCreate) throws CustomNotFoundException, CustomDuplicateFieldException {
        return cardService.create(cardDTOCreate);
    }

    @Operation(summary = "Update card by id")
    @PutMapping("/cards/{id}")
    public CardDTO update(@PathVariable(name = "id") UUID id, @Valid @RequestBody CardDTOUpdate cardDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException {
        return cardService.update(id, cardDTOUpdate);
    }

    @Operation(summary = "Delete card by id")
    @DeleteMapping("/cards/{id}")
    public CardDTO delete(@PathVariable(name = "id") UUID id) throws CustomNotFoundException, CustomBadRequestException {
        return cardService.delete(id);
    }
}
