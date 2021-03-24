package com.example.demospringintegration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.jms.ConnectionFactory;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@SpringBootApplication
@Slf4j
public class JpaJavaApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(JpaJavaApplication.class)
				.run(args);
	}

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private DataSource dataSource;

	@Qualifier("jmsConnectionFactory")
	@Autowired
	private ConnectionFactory jmsConnectionFactory;

//	@Bean
//	public JpaExecutor jpaExecutor() {
//		JpaExecutor jpaExecutor = new JpaExecutor(this.entityManagerFactory);
//		jpaExecutor.setJpaQuery("from Order");
//		jpaExecutor.setDeleteAfterPoll(true);
//		return jpaExecutor;
//	}
//
//	@Bean
//	@InboundChannelAdapter(channel = "jpaInputChannel",
//			poller = @Poller(fixedDelay = "${poller.interval}"))
//	public MessageSource<?> jpaInbound() {
//		return new JpaPollingChannelAdapter(jpaExecutor());
//	}

//	@Bean
//	public MessageHandler handler() {
//		return message -> {
//			System.out.println("service advice layer: " + message.getPayload());
//		};
//	}
//
//	@Bean
//	public MessageHandler gatewayHandler() {
//		return message -> System.out.println("message has been received from gateway");
//	}
//
//
////	@Bean
////	public IntegrationFlow dbPollerFlow(EntityManagerFactory entityManagerFactory) {
////		return IntegrationFlows
////				.from(Jpa.inboundAdapter(entityManagerFactory)
////								.entityClass(OutboxMessage.class)
////								.jpaQuery("from OutboxMessage")
////								.maxResults(1)
////								.expectSingleResult(true)
////								.deleteAfterPoll(true),
////						c -> c.poller(Pollers.fixedDelay(1, TimeUnit.SECONDS).transactional()))
////				.transform(Object::toString)
////				.channel(messageStoreBackedChannel())
////				.get();
////	}
//
//
//	@Bean
//	public IntegrationFlow gatewayExtractFlow() {
//		return IntegrationFlows
//				.from(inputChannel())
//				.channel(messageStoreBackedChannel())
//				.get();
//	}
//
//
//	@Bean
//	public IntegrationFlow jmsExtractFlow(EntityManagerFactory entityManagerFactory) {
//		return IntegrationFlows
//				.from(Jms.inboundAdapter(jmsConnectionFactory)
//						.destination("static-queue-1")
//				)
//				.transform(m -> m + "and retrieved")
//				.handle(handler())
//				.get();
//	}
//
////	@Bean
////	public IntegrationFlow messageStoreChannelFlow() {
////		return IntegrationFlows
////				.from(messageStoreBackedChannel())
////				.intercept(new StatsInterceptor())
////				.intercept(new StatsInterceptor2())
////				.intercept(new StatsInterceptor())
////				.handle(Jms.outboundAdapter(jmsConnectionFactory)
////						.destination("static-queue-1")
////				)
////				.get();
////	}
//
//
//	@Bean(name = PollerMetadata.DEFAULT_POLLER)
//	public PollerMetadata defaultPoller() {
//		PollerMetadata pollerMetadata = new PollerMetadata();
//		pollerMetadata.setTrigger(new PeriodicTrigger(10));
//		return pollerMetadata;
//	}
//
//	@Bean
//	public JdbcChannelMessageStore jdbcChannelMessageStore() {
//		var jdbcChannelMessageStore = new JdbcChannelMessageStore(dataSource);
//		jdbcChannelMessageStore.setChannelMessageStoreQueryProvider(new PostgresChannelMessageStoreQueryProvider());
//		return jdbcChannelMessageStore;
//	}
//
//
////	@Bean
////	public MessageSource<?> integerMessageSource() {
////		MethodInvokingMessageSource source = new MethodInvokingMessageSource();
////		source.setObject(new AtomicInteger());
////		source.setMethodName("getAndIncrement");
////		return source;
////	}
//
//
//
//	@Bean
//	public IntegrationFlow mainFlow() {
//		// @formatter:off
//		return IntegrationFlows
//				.from(messageStoreBackedChannel())
//				.publishSubscribeChannel(pubSub -> pubSub
//						.subscribe(flow -> flow
//								.handle(message -> log.info("Handling message, step 1: {}", message.getPayload())))
//						.subscribe(flow -> flow
//								.handle(message -> log.info("Handling message, step 2: {}", message.getPayload())))
//						.subscribe(flow -> flow
//								.handle(message -> log.info("Handling message, step 2: {}", message.getPayload())))
//						.subscribe(flow -> flow
//								.handle(Jms.outboundAdapter(jmsConnectionFactory).destination("static-queue-1")))
//				).get();
//		// @formatter:on
//	}
//
//	@Bean
//	public MessageChannel inputChannel() {
//		return new DirectChannel();
//	}
//
//	@Bean(name = "queue.output")
//	public MessageChannel appTypeUpgradeInput() {
//		return new DirectChannel();
//	}
//
//	@Bean
//	public MessageChannel messageStoreBackedChannel() {
//		return new QueueChannel(
//				new MessageGroupQueue(jdbcChannelMessageStore(), "Group_ID")
//		);
//	}

}
