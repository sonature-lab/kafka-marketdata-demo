package com.broker.market.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * Finnhub API Configuration
 * Reads API key from application.yml or environment variable
 */
@Component
class FinnhubConfig(
    @Value("\${finnhub.api.key}")
    val apiKey: String
)
