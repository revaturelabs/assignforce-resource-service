package com.revature.assignforce.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.assignforce.beans.SkillsNotifier;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;

public interface SkillSNSNotifier {
    void sendDeleteNotification(SkillsNotifier skillsNotifier);
    void sendAddNotification(SkillsNotifier skillsNotifier);

    default void send(String topic, String subject, SkillsNotifier skillsNotifier, NotificationMessagingTemplate template) {
        String message = null;
        try {
            message = new ObjectMapper().writeValueAsString(skillsNotifier);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        template.sendNotification(topic, message, subject);
    }
}
