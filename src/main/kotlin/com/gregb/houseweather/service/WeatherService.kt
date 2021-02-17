package com.gregb.houseweather.service

import com.gregb.houseweather.model.Weather
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

interface WeatherService {
    fun save(weather: Weather)

    fun findById(id: Long): Weather?

    fun findAll(): List<Weather>

    fun findAll(pageable: Pageable): Page<Weather>

    fun findByDate(startDate: Date, endDate: Date): List<Weather>

    fun findLast(): Weather?

    fun findLast10(): List<Weather>
}
