package com.example.posbe.controller;

import com.example.posbe.customStatusCode.SelectedUserErrorStatus;
import com.example.posbe.dto.custom.UserStatus;
import com.example.posbe.dto.custom.impl.UserDto;
import com.example.posbe.exception.DataPersistException;
import com.example.posbe.exception.UserNotFoundException;
import com.example.posbe.service.UserService;
import com.example.posbe.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveUser(@RequestBody UserDto userDto) {
        try {
            userService.saveUser(userDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException d) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserStatus getUserById(@PathVariable("userId") String userId) {
        if (!RegexUtil.isValidUserId(userId)){
            return new SelectedUserErrorStatus(1, "User Not Found");
        }
        return userService.getUserById(userId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getAllUsers() {return userService.getAllUsers();}

    @PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@PathVariable("userId") String userId, @RequestBody UserDto userDto) {

        try {
            if (!RegexUtil.isValidUserId(userId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            userService.updateUser(userId, userDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {
        try {
            if (!RegexUtil.isValidUserId(userId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistException d) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
