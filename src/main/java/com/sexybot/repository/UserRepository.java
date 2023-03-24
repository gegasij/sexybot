package com.sexybot.repository;

import com.sexybot.model.TgUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<TgUser, Integer> {

    Optional<TgUser> findByChatId(Long integer);
}
