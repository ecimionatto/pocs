package com.fotozap.zap.route;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SqsRoute extends SpringRouteBuilder {

	@Override
	public void configure() {
		// from(
		// "aws-sqs://uploadedmedia?accessKey=AKIAJ2AQ6Q4I3K5VC67Q&secretKey=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")
		// .to("bean:workflowRoute").choice()
		// .when(body().contains("oldworkflow"))
		// .to("bean:handleSqsPayload").otherwise()
		// .to("bean:handleSqsPayloads");

		from("bean:workflowRoute").choice()
				.when(body().isEqualTo("oldworkflow"))
				.to("bean:handleSqsPayload").otherwise()
				.to("bean:handleSqsPayloads");

	}
}
