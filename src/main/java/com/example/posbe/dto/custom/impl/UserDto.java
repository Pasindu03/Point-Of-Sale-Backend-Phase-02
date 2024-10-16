package com.example.posbe.dto.custom.impl;

import com.example.posbe.dto.custom.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements UserStatus {
    private String userId;
    private String username;
    private String password;
}
