package com.example.user_service.controller;

import com.example.user_service.dto.entity.UserDto;
import com.example.user_service.dto.request.CreateRequest;
import com.example.user_service.dto.request.UpdateRequest;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.response.ResponseObject;
import com.example.user_service.response.ResponseStatus;
import com.example.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@CrossOrigin
@Slf4j
@RequestMapping(value = "/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepo;

    @PostMapping(path = "/create")
    public ResponseEntity<?> createUser(@RequestBody CreateRequest req) {
        return ResponseEntity.ok(userService.create(req));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> updateUser(@RequestBody UpdateRequest req) {
        log.info("Controller: Update Staff");
        ResponseObject<UserDto> res = this.userService.update(req);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        log.info("Controller: Update Staff");
        userService.delete(id);
        return ResponseEntity.ok(new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL, "Successful!"));
    }

    @GetMapping (path = "/users")
    public ResponseEntity<?> listUsers() {
        return ResponseEntity.ok(userService.getListUser());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> SearchUsers(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.findByUserId(id));
    }
}
