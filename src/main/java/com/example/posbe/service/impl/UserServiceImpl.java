package com.example.posbe.service.impl;

import com.example.posbe.dto.custom.impl.UserDto;
import com.example.posbe.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void saveUser(UserDto userDto) {

    }

    @Override
    public UserDto getUserById(String userId) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return List.of();
    }

    @Override
    public void updateUser(String userId, UserDto userDto) {

    }

    @Override
    public void deleteUser(String userId) {

    }
}
