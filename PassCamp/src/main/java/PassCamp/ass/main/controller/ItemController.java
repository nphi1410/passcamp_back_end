/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.controller;

import PassCamp.ass.main.dto.ItemDto;
import PassCamp.ass.main.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AD
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<Page<ItemDto>> getItems(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String sortType) {
        Page<ItemDto> items = itemService.getItemList(page, categoryId, name, sortType);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/info")
    public ResponseEntity<ItemDto> getItemDetails(@RequestParam String itemId) {
        return ResponseEntity.ok(itemService.getItemDetails(itemId));
    }

    @PostMapping("/sell/create")
    public ResponseEntity<String> saveSellItem(@RequestBody ItemDto itemDto) {
        return ResponseEntity.ok(itemService.saveSellItem(itemDto));
    }

    @PostMapping("/sell/remove")  
    public ResponseEntity<String> removeSellItem(@RequestParam String itemId) {
        return ResponseEntity.ok(itemService.removeSellItem(itemId));
    }

    @GetMapping("/sell/items")
    public ResponseEntity<Page<ItemDto>> getItemsBySeller(
            @RequestParam String sellerAccountId,
            @RequestParam boolean sold,
            Pageable pageable) {
        return ResponseEntity.ok(itemService.getItemsBySeller(sellerAccountId, pageable, sold));
    }
}
