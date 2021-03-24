package com.example.demospringintegration.flow;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;

import javax.jms.ConnectionFactory;
import javax.persistence.EntityManagerFactory;


@Configuration
@Slf4j
@AllArgsConstructor
public class MainFlowConfiguration {

    private final ConnectionFactory jmsConnectionFactory;


    @Bean
    public IntegrationFlow gatewayExtractFlow() {
		return IntegrationFlows
			.from("inputChannel")
			.channel("messageStoreBackedChannel")
			.get();
    }
    @Bean
    public IntegrationFlow mainFlow() {
        // @formatter:off
        return IntegrationFlows
                .from("messageStoreBackedChannel")
                .publishSubscribeChannel(pubSub -> pubSub
                        .subscribe(flow -> flow
                                .handle(message -> {
                                    log.info("Handling message, step 1: {}", message.getPayload());
                                }))
                        .subscribe(flow -> flow
                                .handle(message -> log.info("Handling message, step 2: {}", message.getPayload())))
                        .subscribe(flow -> flow
                                .handle(message -> log.info("Handling message, step 3: {}", message.getPayload())))
                        .subscribe(flow -> flow
                                .handle(Jms.outboundAdapter(jmsConnectionFactory).destination("static-queue-1")))
                ).get();
        // @formatter:on
    }


    @Bean
	public IntegrationFlow jmsExtractFlow(EntityManagerFactory entityManagerFactory) {
		return IntegrationFlows
				.from(Jms.inboundAdapter(jmsConnectionFactory)
						.destination("static-queue-1")
				)
				.transform(m -> m + "and retrieved")
				.handle(message -> System.out.println(message.getPayload() + "123"))
				.get();
	}
}
