package com.example.posbe.controller;

import com.example.posbe.customStatusCode.SelectedItemErrorStatus;
import com.example.posbe.dto.custom.ItemStatus;
import com.example.posbe.dto.custom.impl.ItemDto;
import com.example.posbe.exception.DataPersistException;
import com.example.posbe.exception.ItemNotFoundException;
import com.example.posbe.service.ItemService;
import com.example.posbe.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveItem(@RequestBody ItemDto itemDto) {
        try {
            itemService.saveItem(itemDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException d) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{itemCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemStatus getItemByCode(@PathVariable("itemCode") String itemCode) {

        if (!RegexUtil.isValidItemId(itemCode)) {
            return new SelectedItemErrorStatus(1, "Item Not Found");
        }

        return itemService.getItemById(itemCode);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDto> getAllItems() {return itemService.getAllItems();}

    @PutMapping(value = "/{itemCode}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateItem(@PathVariable("itemCode") String itemCode, @RequestBody ItemDto itemDto) {
        try {
            if (!RegexUtil.isValidItemId(itemCode) && itemDto == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.updateItem(itemCode, itemDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ItemNotFoundException i) {
            i.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{itemCode}")
    public ResponseEntity<Void> deleteItem(@PathVariable("itemCode") String itemCode) {
        try {
            if (!RegexUtil.isValidItemId(itemCode)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.deleteItem(itemCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ItemNotFoundException i) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
