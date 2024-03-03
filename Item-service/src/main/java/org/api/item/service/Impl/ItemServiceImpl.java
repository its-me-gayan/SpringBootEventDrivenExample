package org.api.item.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import org.api.item.entity.Item;
import org.api.item.repository.ItemRepository;
import org.api.item.service.ItemService;
import org.common.core.dto.common.AbstractResponse;
import org.common.core.dto.itemService.ItemDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private ItemRepository itemRepository;
    public AbstractResponse<List<ItemDto>> getAllItem()throws Exception{
         return AbstractResponse.<List<ItemDto>>builder()
                 .data(itemRepository.findAll().stream().map(item ->
                         modelMapper.map(item, ItemDto.class)
                 ).toList())
                .isSuccess(Boolean.TRUE)
                .message_code("000")
                .message("Item list Success")
                .message_description("Item list success")
                .timestamp(Timestamp.from(Instant.now()))
                .status(HttpStatus.OK)
                .status_code(HttpStatus.OK.value()).build();
    }

    @Override
    public AbstractResponse<ItemDto> getItemById(Integer id) throws Exception {
        Item noItemFound = itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("no item found"));

        return AbstractResponse.<ItemDto>builder()
                .data(modelMapper.map(noItemFound ,ItemDto.class))
                .isSuccess(Boolean.TRUE)
                .message_code("000")
                .message("Item by id Success")
                .message_description("Item by id success")
                .timestamp(Timestamp.from(Instant.now()))
                .status(HttpStatus.OK)
                .status_code(HttpStatus.OK.value()).build();
    }
}
