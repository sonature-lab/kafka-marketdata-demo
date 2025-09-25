package com.broker.market.controller

import com.broker.market.model.MarketData
import com.broker.market.service.MarketDataService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestParam

/**
 * MarketDataController
 * - Exposes REST endpoint to retrieve the latest MarketData.
 */
@RestController
class MarketDataController(
    private val service: MarketDataService
) {
    @GetMapping("/api/market-data")
    fun getMarketData(): MarketData? = service.getLatest()

    @GetMapping("/api/fetch")
    fun fetchSymbol(@RequestParam symbol: String): MarketData {
        return service.fetchAndPublish(symbol)
    }
}
