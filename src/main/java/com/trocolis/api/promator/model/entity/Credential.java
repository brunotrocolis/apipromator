package com.trocolis.api.promator.model.entity;

import com.trocolis.api.promator.model.domain.user.CredentialStatusDomain;
import com.trocolis.api.promator.model.entity.id.CredentialId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credentials")
@IdClass(CredentialId.class)
public class Credential {

    public Credential(UUID userId, String password) {
        this.userId = userId;
        this.password = password;
        this.sequence = 1L;
    }

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Id
    private Long sequence;

    private String password;

    @Enumerated(EnumType.STRING)
    private CredentialStatusDomain status = CredentialStatusDomain.ACTIVE;

    private LocalDateTime creationDateTime = LocalDateTime.now();

    private int hash = hashCode();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (userId == null ? 0 : userId.hashCode());
        result = prime * result + (sequence == null ? 0 : sequence.hashCode());
        result = prime * result + (password == null ? 0 : password.hashCode());
        result = prime * result + (status == null ? 0 : status.hashCode());
        result = prime * result + (creationDateTime == null ? 0 : creationDateTime.hashCode());
        return result;
    }
}
