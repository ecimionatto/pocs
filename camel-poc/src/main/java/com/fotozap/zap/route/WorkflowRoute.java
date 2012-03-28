package com.fotozap.zap.route;

import org.springframework.stereotype.Component;

@Component
public class WorkflowRoute {

	public String process(String payload) {
		return "oldworkflows";
	}

}
