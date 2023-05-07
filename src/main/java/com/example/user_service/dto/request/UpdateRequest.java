package com.example.user_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRequest {
    private UUID userId;
    private String username;
    private String fullName;
    private String email;
    private String birthday;
    private String address;
}
