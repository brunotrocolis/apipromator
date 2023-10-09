package com.trocolis.api.promator.model.entity;

import com.trocolis.api.promator.model.domain.user.UserStatusDomain;
import com.trocolis.api.promator.model.domain.user.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    public User(String name, String email, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @Enumerated(EnumType.STRING)
    private UserStatusDomain status = UserStatusDomain.INACTIVE;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime = LocalDateTime.now();

}
