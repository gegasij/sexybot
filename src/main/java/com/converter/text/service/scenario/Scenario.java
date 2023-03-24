package com.converter.text.service.scenario;

import com.sexybot.model.Category;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
@Getter
@Setter
public class Scenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "categories", columnDefinition = "json")
    @Type(JsonType.class)
    private List<Category> categories;

    private Integer scenarioLevel;
    @Column(length = 10000, columnDefinition = "CHARACTER VARYING(10000)")
    private String text;
}
