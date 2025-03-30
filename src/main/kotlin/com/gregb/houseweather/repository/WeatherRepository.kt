package com.gregb.houseweather.repository

import com.gregb.houseweather.model.Weather
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface WeatherRepository : JpaRepository<Weather, Long> {
    fun findFirstByOrderByCreatedDateDesc(): Optional<Weather>;

    fun findFirst10ByOrderByCreatedDateDesc(): List<Weather>;

    fun findByCreatedDateBetween(from: Date, to: Date): List<Weather>;

}
