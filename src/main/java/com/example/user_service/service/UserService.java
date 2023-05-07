package com.example.user_service.service;

import com.example.user_service.dto.entity.UserDto;
import com.example.user_service.dto.request.CreateRequest;
import com.example.user_service.dto.request.UpdateRequest;
import com.example.user_service.response.ResponseObject;

import java.util.List;
import java.util.UUID;

public interface UserService {
    ResponseObject<UserDto> create(CreateRequest req);
    ResponseObject<UserDto> findByUsername(String username);
    ResponseObject<UserDto> update(UpdateRequest req);
    void delete(UUID userId);
    ResponseObject<UserDto> findByUserId(UUID userId);
    ResponseObject<List<UserDto>> getListUser();
}
