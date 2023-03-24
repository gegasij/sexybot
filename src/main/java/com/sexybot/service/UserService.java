package com.sexybot.service;

import com.sexybot.model.TgUser;
import com.sexybot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<TgUser> getUserByChatId(Long chatId) {
        return userRepository.findByChatId(chatId);
    }

    public TgUser createUser(Long chatId) {
        TgUser build = TgUser.builder()
                .isPayed(false)
                .joinedDateTime(LocalDateTime.now())
                .chatId(chatId)
                .build();
        return userRepository.save(build);
    }
}
