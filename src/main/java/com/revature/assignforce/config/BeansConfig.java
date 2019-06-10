package com.revature.assignforce.config;

import com.amazonaws.services.sns.AmazonSNS;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.assignforce.service.CurriculumService;
import com.revature.assignforce.service.CurriculumServiceImpl;
import com.revature.assignforce.service.TrainerService;
import com.revature.assignforce.service.TrainerServiceImpl;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;

import java.util.Collections;

@Configuration
public class BeansConfig {
    @Bean
    public CurriculumService curriculumService() {
        return new CurriculumServiceImpl();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Bean
    public NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNS amazonSNS) {
        return new NotificationMessagingTemplate(amazonSNS);
    }

    @Bean
    public TrainerService trainerService() {
        return new TrainerServiceImpl();
    }

    @Bean
    public QueueMessageHandlerFactory queueMessageHandlerFactory() {
        QueueMessageHandlerFactory factory = new QueueMessageHandlerFactory();
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();

        //set strict content type match to false
        messageConverter.setStrictContentTypeMatch(false);
        factory.setArgumentResolvers(Collections.<HandlerMethodArgumentResolver>singletonList(new PayloadArgumentResolver(messageConverter)));
        return factory;
    }
}
