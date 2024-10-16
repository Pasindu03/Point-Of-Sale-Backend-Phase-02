package com.example.posbe.service;

import com.example.posbe.dto.custom.ItemStatus;
import com.example.posbe.dto.custom.impl.ItemDto;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDto itemDto);
    ItemStatus getItemById(String itemCode);
    List<ItemDto> getAllItems();
    void updateItem(String itemCode, ItemDto itemDto);
    void deleteItem(String itemCode);
}
