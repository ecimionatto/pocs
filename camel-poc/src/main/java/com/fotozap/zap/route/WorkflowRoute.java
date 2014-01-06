package foo;

import org.springframework.stereotype.Component;

@Component
public class WorkflowRoute {

	public String process(String payload) {
		return "oldworkflows";
	}

}
