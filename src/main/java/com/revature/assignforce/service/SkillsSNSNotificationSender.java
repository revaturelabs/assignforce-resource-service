package com.revature.assignforce.service;

import com.revature.assignforce.beans.SkillsNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SkillsSNSNotificationSender implements SkillSNSNotifier {
    @Value("${app.sns.topics.add-skill}")
    private String snsSkillsAddTopic;

    @Value("${app.sns.topics.del-skill}")
    private String snsSkillsDelTopic;

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Autowired
    public SkillsSNSNotificationSender(NotificationMessagingTemplate notificationMessagingTemplate) {
        this.notificationMessagingTemplate = notificationMessagingTemplate;
    }


    @Override
    public void sendDeleteNotification(SkillsNotifier skillsNotifier){
        send(snsSkillsDelTopic, "Delete Skill", skillsNotifier, notificationMessagingTemplate);
    }

    @Override
    public void sendAddNotification(SkillsNotifier skillsNotifier){
        send(snsSkillsAddTopic, "Add Skill", skillsNotifier, notificationMessagingTemplate);
    }
}
