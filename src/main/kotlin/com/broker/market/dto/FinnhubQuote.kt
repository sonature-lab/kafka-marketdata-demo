package com.broker.market.dto

import com.broker.market.model.MarketData
import java.time.Instant

data class FinnhubQuote(
    val c: Double,
    val h: Double,
    val l: Double,
    val o: Double,
    val pc: Double
) {
    fun toMarketData(symbol: String): MarketData =
        MarketData(
            symbol = symbol,
            price = c,
            high = h,
            low = l,
            open = o,
            previousClose = pc,
            timestamp = Instant.now()
        )
}
