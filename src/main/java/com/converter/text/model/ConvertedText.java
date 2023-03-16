package com.converter.text.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import java.util.Map;

@Entity
@SuperBuilder
@NoArgsConstructor
public class ConvertedText {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "data", columnDefinition = "json")
    @Type(JsonType.class)
    private Map<String, Object> data;
}
