package org.api.item.service;

import org.common.core.dto.common.AbstractResponse;
import org.common.core.dto.itemService.ItemDto;

import java.util.List;

public interface ItemService {
    AbstractResponse<List<ItemDto>> getAllItem() throws Exception;
    AbstractResponse<ItemDto> getItemById(Integer id) throws Exception;
}