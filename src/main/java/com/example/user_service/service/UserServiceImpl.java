package com.example.user_service.service;

import com.example.user_service.dto.entity.UserDto;
import com.example.user_service.dto.request.CreateRequest;
import com.example.user_service.dto.request.UpdateRequest;
import com.example.user_service.exception.UserException;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.mapper.UserMapper;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.response.ResponseObject;
import com.example.user_service.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public ResponseObject<UserDto> create(CreateRequest req) {
        ResponseObject<UserDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        try {
            User user = User.builder()
                    .email(req.getEmail())
                    .username(req.getUsername())
                    .fullName(req.getFullName())
                    .address(req.getAddress())
                    .birthday(req.getBirthday())
                    .build();
            user = this.userRepository.saveAndFlush(user);
            res.setData(UserMapper.responseUserDtoFromModel(user));
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }
        return res;
    }

    @Override
    public ResponseObject<UserDto> findByUsername(String username) {
        return null;
    }

    @Override
    public ResponseObject<UserDto> update(UpdateRequest req) {
        ResponseObject<UserDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        User user = this.userRepository.findById(req.getUserId())
                .orElseThrow(() -> new UserNotFoundException(req.getUserId()));
        user.setEmail(req.getEmail());
        user.setUsername(req.getUsername());
        user.setFullName(req.getFullName());
        user.setAddress(user.getAddress());
        user.setBirthday(req.getBirthday());
        user = this.userRepository.saveAndFlush(user);
        res.setData(UserMapper.responseUserDtoFromModel(user));
        return res;
    }

    @Override
    public void delete(UUID userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        this.userRepository.delete(user);
    }

    @Override
    public ResponseObject<UserDto> findByUserId(UUID userId) {
        ResponseObject<UserDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        res.setData(UserMapper.responseUserDtoFromModel(user));
        return res;
    }

    @Override
    public ResponseObject<List<UserDto>> getListUser() {
        ResponseObject<List<UserDto>> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        List<User> listGroup = this.userRepository.findAll();
        res.setData(listGroup.stream().map(UserMapper::responseUserDtoFromModel).collect(Collectors.toList()));
        return res;
    }
}
