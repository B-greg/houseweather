package com.gregb.houseweather.model

import com.thanglequoc.aqicalculator.AQICalculator
import com.thanglequoc.aqicalculator.AQIResult
import com.thanglequoc.aqicalculator.Pollutant
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.util.*

@Entity
@Table(name = "weather")
class Weather {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weather_generator")
    @SequenceGenerator(name="weather_generator", sequenceName = "weather_id_seq", allocationSize=1)
    val id: Long = 0

    var temperature: Float? = null

    var humidity: Float? = null

    var pm25: String? = null

    var pm10: String? = null

    @CreationTimestamp
    @Column(name = "CreatedDate", updatable=false)
    var createdDate: Date? = null

    fun getAQI(): AQIResult {
        return getAQI25()
    }

    fun getAQI25(): AQIResult {
        val calculator: AQICalculator = AQICalculator.getAQICalculatorInstance()
        return calculator.getAQI(Pollutant.PM25, this.pm25?.toDouble() ?: 0.0)
    }

    fun getAQI10(): AQIResult {
        val calculator: AQICalculator = AQICalculator.getAQICalculatorInstance()
        return calculator.getAQI(Pollutant.PM10, this.pm10?.toDouble() ?: 0.0)
    }
}

