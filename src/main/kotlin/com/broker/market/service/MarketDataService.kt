package com.broker.market.service

import com.broker.market.model.MarketData
import com.broker.market.producer.MarketDataProducer
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.concurrent.atomic.AtomicReference
import com.broker.market.config.FinnhubConfig
import com.broker.market.dto.FinnhubQuote

/**
 * MarketDataService
 * - Holds the latest MarketData in memory.
 * - Provides methods for both Producer (fetch + publish) and Consumer (update).
 */
@Service
class MarketDataService (
    private val producer: MarketDataProducer,
    private val restTemplate: RestTemplate,
    private val config: FinnhubConfig   // ✅ 여기에 주입
){
    // AtomicReference ensures thread-safety when multiple threads update/read
    private val latestData = AtomicReference<MarketData?>()

    /**
     * Called by Consumer after receiving Kafka message
     */
    fun update(data: MarketData) {
        latestData.set(data)
    }

    /**
     * Called by Controller to expose latest data
     */
    fun getLatest(): MarketData? = latestData.get()

    fun fetchAndPublish(symbol: String): MarketData {
        val url = "https://finnhub.io/api/v1/quote?symbol=$symbol&token=${config.apiKey}"

        val response = restTemplate.getForObject(url, FinnhubQuote::class.java)
            ?: throw RuntimeException("Failed to fetch Finnhub data")

        val data = response.toMarketData(symbol)
        producer.sendToKafka(data)
        latestData.set(data)
        return data
    }

}
