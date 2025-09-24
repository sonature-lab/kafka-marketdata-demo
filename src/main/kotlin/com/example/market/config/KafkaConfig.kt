package com.example.market.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaConfig {

    /**
     * market-data topic will be created automatically
     * partitions = 3 → parallel processing of messages
     * replicationFactor = 1 → Only 1 broker is used in local
     */
    @Bean
    fun marketDataTopic(): NewTopic {
        return NewTopic("market-data", 3, 1.toShort())
    }
}
