package com.gregb.houseweather.service

import com.gregb.houseweather.model.Weather
import com.gregb.houseweather.repository.WeatherRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable


import java.util.HashSet

@Service
class WeatherServiceImpl : WeatherService {
    @Autowired
    private lateinit var weatherRepository: WeatherRepository


    override fun save(weather: Weather) {
        weatherRepository.save(weather)
    }

    override fun findById(id: Long): Weather? {
        return weatherRepository.findById(id).orElse(null)
    }

    override fun findAll(): List<Weather> {
        return weatherRepository.findAll()
    }

    override fun findAll(pageable: Pageable): Page<Weather> {
        return  weatherRepository.findAll(pageable)
    }

    override fun findLast(): Weather? {
        return weatherRepository.findFirstByOrderByCreatedDateDesc().orElse(null)
    }

    override fun findLast10(): List<Weather> {
        return weatherRepository.findFirst10ByOrderByCreatedDateDesc()
    }


}
