package com.example.user_service.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "tbl_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "UserId", nullable = false)
    private UUID userId;

    @Column(name = "Username", length = 30, nullable = false, unique = true)
    private String username;

    @Column(name = "FullName", columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String fullName;

    @Column(name = "Email", length = 40, nullable = false, unique = true)
    private String email;

    @Column(name = "Birthday", length = 40)
    private String birthday;

    @Column(name = "Address", length = 40, nullable = false)
    private String address;
}
