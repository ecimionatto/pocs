package foo;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SqsRoute extends SpringRouteBuilder {

	@Override
	public void configure() {
		// from(
		// "aws-sqs://xxx?accessKey=XXXX&secretKey=XXX")
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
