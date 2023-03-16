package com.sexybot.controller;

import com.sexybot.model.Topic;
import com.sexybot.service.TopicService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @GetMapping
    public List<Topic> getTopics() {
        return topicService.getTopics();
    }
    @PostMapping
    public Topic saveTopic(@RequestBody Topic topic) {
        return topicService.saveTopic(topic);
    }
}
