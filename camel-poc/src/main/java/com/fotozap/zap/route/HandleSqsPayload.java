package com.fotozap.zap.route;

import org.springframework.stereotype.Component;

@Component
public class HandleSqsPayload {

	public void process(String payload) {
		System.out.println("HandleSqsPayload.process: " + payload);
	}

}
