package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.model.dto.CardDTO;
import com.gasstation.managementsystem.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Card", description = "API for card")
public class CardController {

    @Autowired
    CardService cardService;

    @Operation(summary = "View All card")
    @GetMapping("/cards")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = "2") Integer pageSize) {
        return cardService.findAll(PageRequest.of(pageIndex - 1, pageSize));
    }

    @Operation(summary = "Find card by id")
    @GetMapping("/cards/{id}")
    public CardDTO getOne(@PathVariable(name = "id") Integer id) {
        return cardService.findById(id);
    }

    @Operation(summary = "Create new card")
    @PostMapping("/cards")
    public CardDTO create(@Valid @RequestBody Card card) {
        return cardService.save(card);
    }

    @Operation(summary = "Update card by id")
    @PutMapping("/cards/{id}")
    public CardDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody Card card) {
        card.setId(id);
        return cardService.save(card);
    }

    @Operation(summary = "Delete card by id")
    @DeleteMapping("/cards/{id}")
    public CardDTO delete(@PathVariable(name = "id") Integer id) {
        return cardService.delete(id);
    }
}
