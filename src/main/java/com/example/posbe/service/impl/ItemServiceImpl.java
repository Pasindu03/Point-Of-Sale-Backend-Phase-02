package com.example.posbe.service.impl;

import com.example.posbe.customStatusCode.SelectedItemErrorStatus;
import com.example.posbe.dao.ItemDao;
import com.example.posbe.dto.custom.ItemStatus;
import com.example.posbe.dto.custom.impl.ItemDto;
import com.example.posbe.entity.impl.Item;
import com.example.posbe.exception.DataPersistException;
import com.example.posbe.exception.ItemNotFoundException;
import com.example.posbe.service.ItemService;
import com.example.posbe.util.AppUtil;
import com.example.posbe.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveItem(ItemDto dto) {
        dto.setItemCode(AppUtil.generateItemId());
        Item itemEntity = mapping.toItemEntity(dto);

        Item savedItem = itemDao.save(itemEntity);
        if (savedItem == null) {
            throw new DataPersistException("Item not saved");
        }
    }

    @Override
    public ItemStatus getItemById(String itemCode) {
        if (itemDao.existsById(itemCode)) {
            Item selectedItem = itemDao.getReferenceById(itemCode);
            return mapping.toItemDto(selectedItem);
        } else {
            return new SelectedItemErrorStatus(2, "Selected Item not found");
        }
    }

    @Override
    public List<ItemDto> getAllItems() {
        List<Item> allItems = itemDao.findAll();
        return mapping.asItemDto(allItems);
    }

    @Override
    public void updateItem(String itemCode, ItemDto itemDto) {
        Optional<Item> byId = itemDao.findById(itemCode);
        if (!byId.isPresent()) {
            throw new ItemNotFoundException("Item not found");
        } else {
            Item itemToUpdate = byId.get();
            itemToUpdate.setDescription(itemDto.getDescription());
            itemToUpdate.setQty(itemDto.getQty());
            itemToUpdate.setPrice(itemDto.getPrice());

            itemDao.save(itemToUpdate);
        }
    }

    @Override
    public void deleteItem(String itemCode) {
        Optional<Item> foundItem = itemDao.findById(itemCode);
        if (!foundItem.isPresent()) {
            throw new ItemNotFoundException("Item not found");
        } else {
            itemDao.deleteById(itemCode);
        }
    }
}
