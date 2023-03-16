package com.sexybot.service;

import com.sexybot.model.Topic;
import com.sexybot.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;

    public List<Topic> getTopics() {
        return topicRepository.findAll();
    }

    public Topic saveTopic(Topic topic) {
        return topicRepository.save(topic);
    }
}
