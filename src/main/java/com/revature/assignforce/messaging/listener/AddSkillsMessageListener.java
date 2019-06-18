package com.revature.assignforce.messaging.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.beans.SkillMessage;
import com.revature.assignforce.repos.SkillIdRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AddSkillsMessageListener implements SkillsMessageListener {
    private static final String name = "AddSkillMessageListener";
    private static final Logger LOG = LoggerFactory.getLogger(name);
    private SkillIdRepo skillIdRepo;

    @Autowired
    public AddSkillsMessageListener(SkillIdRepo skillIdRepo) {
        this.skillIdRepo = skillIdRepo;
    }

    @Override
    @SqsListener("${app.sqs.queues.add-skill-queue}")
    public void receive(String message) throws Exception{
        LOG.info("Received -- " + message);
        Map<String, String> messageMap = new ObjectMapper().readValue(message, Map.class);
        SkillMessage sm = new ObjectMapper().readValue(messageMap.get("Message"), SkillMessage.class);
        SkillIdHolder s = new SkillIdHolder(sm.getId());
        skillIdRepo.save(s);
    }
}
