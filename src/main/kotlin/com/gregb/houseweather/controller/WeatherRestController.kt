package com.gregb.houseweather.controller

import com.gregb.houseweather.model.Weather
import com.gregb.houseweather.restModel.WeatherBody
import com.gregb.houseweather.service.WeatherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.math.RoundingMode.valueOf
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import java.time.format.DateTimeFormatter




@RestController
@RequestMapping("/api")
class WeatherRestController {

    @Autowired
    private lateinit var weatherService: WeatherService

    @GetMapping("/weather")
    fun getAll(@PageableDefault(size = 10, page = 0) pageable: Pageable): List<Weather> {
        return weatherService.findAll(pageable).toList()
    }


    @PostMapping("/weather")
    fun postWeather(@RequestBody weather: WeatherBody) {
        weatherService.save(Weather().apply {
            humidity = weather.humidity
            temperature = weather.temperature
            pm25 = weather.pm25
            pm10 = weather.pm10
        })
    }

    @GetMapping("/graph")
    fun weatherGraph(
        @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  startDate: Date,
        @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") endDate: Date
    ): List<Weather> {
        return weatherService.findByDate(startDate, endDate)
    }
}