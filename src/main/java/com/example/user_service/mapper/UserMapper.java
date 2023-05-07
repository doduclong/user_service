package com.example.user_service.mapper;

import com.example.user_service.dto.entity.UserDto;
import com.example.user_service.model.User;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto responseUserDtoFromModel(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .address(user.getAddress())
                .birthday(user.getBirthday())
                .build();
    }
}
