package com.sexybot.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long chatId;
    private String details;
    private LocalDateTime joinedDateTime;
}
