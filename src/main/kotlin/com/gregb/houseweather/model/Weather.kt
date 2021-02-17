package com.gregb.houseweather.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.thanglequoc.aqicalculator.AQICalculator
import com.thanglequoc.aqicalculator.AQIResult
import com.thanglequoc.aqicalculator.Pollutant
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.Generated
import org.hibernate.annotations.GenerationTime
import org.springframework.format.annotation.DateTimeFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import java.time.ZoneId

import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.TimeZone







@Entity
@Table(name = "weather")
class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long = 0

    var temperature: Float? = null

    var humidity: Float? = null

    var pm25: String? = null

    var pm10: String? = null

    @CreationTimestamp
    @Column(name = "CreatedDate", updatable=false)
    var createdDate: Date? = null

    fun getAQI(): AQIResult {
        val calculator: AQICalculator = AQICalculator.getAQICalculatorInstance()
        return calculator.getAQI(Pollutant.PM25, this.pm25?.toDouble() ?: 0.0)
    }
}

