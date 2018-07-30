package io.umehara.lunchfinderandroid

import java.math.BigDecimal

data class Geolocation(
        val lat: BigDecimal = BigDecimal.valueOf(0L),
        val long: BigDecimal = BigDecimal.valueOf(0L)
)