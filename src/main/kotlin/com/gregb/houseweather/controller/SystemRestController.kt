package com.gregb.houseweather.controller

import com.gregb.houseweather.model.Weather
import com.gregb.houseweather.service.SystemService
import com.gregb.houseweather.service.WeatherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/system")
class SystemRestController {

    @Autowired
    private lateinit var systemService: SystemService

    @GetMapping("/cpuTemp")
    fun getCpuTemp(): Int {
        return systemService.getCPUTemp()
    }
}