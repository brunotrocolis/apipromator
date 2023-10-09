package com.trocolis.api.promator.model.repository;

import com.trocolis.api.promator.model.domain.user.UserStatusDomain;
import com.trocolis.api.promator.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);

}
