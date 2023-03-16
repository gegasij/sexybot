package com.sexybot.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
@Getter
@Setter
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private TopicType type;

    @Column(name = "messages", columnDefinition = "json")
    @Type(JsonType.class)
    private List<Message> messages;

    class Message {
        private String text;
        private List<MessageAttachment> messageAttachments;
        private int order;
    }
}
