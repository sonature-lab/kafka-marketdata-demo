package com.broker.market.model

import java.time.Instant

/**
 * MarketData represents a simple snapshot of stock/crypto price
 * sent through Kafka.
 */
data class MarketData(
    val symbol: String,
    val price: Double,
    val high: Double,
    val low: Double,
    val open: Double,
    val previousClose: Double,
    val timestamp: Instant = Instant.now()
)
