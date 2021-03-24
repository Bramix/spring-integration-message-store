package com.example.demospringintegration.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.jdbc.store.JdbcChannelMessageStore;
import org.springframework.integration.jdbc.store.channel.PostgresChannelMessageStoreQueryProvider;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.integration.store.MessageGroupQueue;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.support.PeriodicTrigger;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class ChannelConfiguration {

    private final DataSource dataSource;
    @Bean
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean(name = "queue.output")
    public MessageChannel appTypeUpgradeInput() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel messageStoreBackedChannel() {
        return new QueueChannel(
                new MessageGroupQueue(jdbcChannelMessageStore(), "Group_ID")
        );
    }
    @Bean
    public JdbcChannelMessageStore jdbcChannelMessageStore() {
        var jdbcChannelMessageStore = new JdbcChannelMessageStore(dataSource);
        jdbcChannelMessageStore.setChannelMessageStoreQueryProvider(new PostgresChannelMessageStoreQueryProvider());
        return jdbcChannelMessageStore;
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata defaultPoller() {
        PollerMetadata pollerMetadata = new PollerMetadata();
        pollerMetadata.setTrigger(new PeriodicTrigger(10));
        return pollerMetadata;
    }
}
