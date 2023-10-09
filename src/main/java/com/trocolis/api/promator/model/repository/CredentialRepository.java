package com.trocolis.api.promator.model.repository;

import com.trocolis.api.promator.model.entity.Credential;
import com.trocolis.api.promator.model.entity.id.CredentialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, CredentialId> {

    @Query("FROM Credential WHERE userId = :userId ORDER BY sequence DESC")
    Credential findCredentialsByUserId(UUID userId);
}
