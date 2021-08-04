package com.gasstation.managementsystem.controller;

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
                                          @RequestParam(name = "accountsPayable", required = false) Double accountsPayable,
                                          @RequestParam(name = "availableBalance", required = false) Double availableBalance,
                                          @RequestParam(name = "statuses", required = false) String[] statuses,
                                          @RequestParam(name = "createdDate", required = false) Long createdDate,
                                          @RequestParam(name = "customerName", required = false) String customerName,
                                          @RequestParam(name = "customerPhone", required = false) String customerPhone,
                                          @RequestParam(name = "licensePlate", required = false) String licensePlate) {
        CardDTOFilter filter = CardDTOFilter.builder()
                .pageIndex(pageIndex)
                .pageSize(pageSize)
                .accountsPayable(accountsPayable)
                .availableBalance(availableBalance)
                .statuses(statuses)
                .createdDate(createdDate)
                .customerName(customerName)
                .customerPhone(customerPhone)
                .licensePlate(licensePlate).build();
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
    public CardDTO delete(@PathVariable(name = "id") UUID id) throws CustomNotFoundException {
        return cardService.delete(id);
    }
}
