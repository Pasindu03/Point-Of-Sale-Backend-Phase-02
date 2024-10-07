package com.example.posbe.service.impl;

import com.example.posbe.dto.custom.impl.ItemDto;
import com.example.posbe.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Override
    public void saveItem(ItemDto itemDto) {

    }

    @Override
    public ItemDto getItemById(String itemCode) {
        return null;
    }

    @Override
    public List<ItemDto> getAllItems() {
        return List.of();
    }

    @Override
    public void updateItem(String itemCode, ItemDto itemDto) {

    }

    @Override
    public void deleteItem(String itemCode) {

    }
}
