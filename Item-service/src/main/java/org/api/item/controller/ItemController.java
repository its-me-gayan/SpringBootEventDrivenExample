package org.api.item.controller;

import org.api.item.service.ItemService;
import org.common.core.dto.common.AbstractResponse;
import org.common.core.dto.itemService.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item/")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @GetMapping("/")
    ResponseEntity<AbstractResponse<List<ItemDto>>> getAllItems()throws Exception{
        AbstractResponse<List<ItemDto>> allItem = itemService.getAllItem();
        return ResponseEntity.status(allItem.getStatus()).body(allItem);
    }

    @GetMapping("/{id}")
    ResponseEntity<AbstractResponse<ItemDto>> getAllItemById(@PathVariable("id") Integer id)throws Exception{
        AbstractResponse<ItemDto> allItem = itemService.getItemById(id);
        return ResponseEntity.status(allItem.getStatus()).body(allItem);
    }
}
