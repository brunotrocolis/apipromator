package com.trocolis.api.promator.model.repository;

import com.trocolis.api.promator.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
