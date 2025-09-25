package com.broker.market.producer

import com.broker.market.model.MarketData
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

/**
 * MarketDataProducer
 * - Responsible only for sending MarketData events to Kafka.
 * - Does NOT call external APIs anymore (handled in Service layer).
 */
@Service
class MarketDataProducer(
    private val kafkaTemplate: KafkaTemplate<String, MarketData>
) {
    private val logger = LoggerFactory.getLogger(MarketDataProducer::class.java)

    fun sendToKafka(data: MarketData) {
        logger.info("Producing to Kafka: $data")
        kafkaTemplate.send("market-data", data.symbol, data)
    }
}
