package com.broker.market.consumer

import com.broker.market.model.MarketData
import com.broker.market.service.MarketDataService
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

/**
 * MarketDataConsumer
 * - Listens to "market-data" topic and forwards messages to service layer.
 * - Service layer manages state (latest data) decoupled from REST API.
 */
@Service
class MarketDataConsumer(
    private val service: MarketDataService
) {
    private val logger = LoggerFactory.getLogger(MarketDataConsumer::class.java)

    @KafkaListener(topics = ["market-data"], groupId = "market-data-group")
    fun consume(data: MarketData) {
        try {
            service.update(data)
        } catch (ex: Exception) {
            logger.error("Failed to process data: $data", ex)
        }
    }
}
