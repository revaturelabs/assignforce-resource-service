package com.revature.assignforce.messaging.messenger;


import org.springframework.stereotype.Component;


@Component
public class CurriculumMessenger {
	
	private String exchange;
	
	private String routingKey;

	
	public void sendDeletionMessage(int id) {
	}
	
}
